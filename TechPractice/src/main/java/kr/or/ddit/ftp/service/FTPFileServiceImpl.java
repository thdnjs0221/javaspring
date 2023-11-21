package kr.or.ddit.ftp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.pool2.ObjectPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FTPFileServiceImpl implements FTPFileService {
	@Inject
	@Named("ftpClientPool")
	private ObjectPool<FTPClient> ftpClientPool;
	
	@Value("#{appInfo.tempFolder}")
	private File tempFolder;
	
	private static final String PREFIX = "FTP_";
	
	@PostConstruct
	public void init() {
		if(!tempFolder.exists())
			tempFolder.mkdirs();
	}
	
	@Override
	public List<FTPFile> traversing(String path) throws Exception {
		if(path==null||path.isEmpty()) throw new IllegalArgumentException("path 가 비어있음.");
		
		FTPClient ftpClient = ftpClientPool.borrowObject();
		try {
			FTPFile file = ftpClient.mlistFile(path);
			
			if(file==null || !file.isValid() || file.isSymbolicLink() || file.isUnknown()) {
				throw new IOException(String.format("%s 는 탐색 대상이 아님.", path));
				
			}else if(file.isDirectory()) {
				FTPFile[] listFiles = ftpClient.mlistDir(path);
				Stream.of(listFiles)
						.forEach(f->log.info("수신 파일 : {}", f));
				return Arrays.asList(listFiles);
			}else{
				return Collections.singletonList(file);
			}
		}finally {
			ftpClientPool.returnObject(ftpClient);
		}
	}

	@Override
	public boolean sendFile(String path, MultipartFile file) throws Exception {
		if(path==null) throw new IllegalArgumentException("path 가 널임.");
		if(file==null || file.isEmpty())  throw new IllegalArgumentException("empty 파일임.");
		
		FTPClient ftpClient = ftpClientPool.borrowObject();
		try {
			
			FTPFile ftpFile = ftpClient.mlistFile(path);
			
			if(ftpFile == null || !ftpFile.isDirectory())
				throw new IOException(String.format("%s 에 저장할 수 없음.", path));
			else
				ftpClient.changeWorkingDirectory(path);
			
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.storeFile(file.getOriginalFilename(), file.getInputStream());
		}finally {
			ftpClient.changeWorkingDirectory("/");
			ftpClientPool.returnObject(ftpClient);
		}
	}

	@Override
	public File receiveFile(String path) throws Exception {
		if(path==null) throw new IllegalArgumentException("path 가 널임.");
		
		FTPClient ftpClient = ftpClientPool.borrowObject();
		try {
			
			FTPFile ftpFile = ftpClient.mlistFile(path);
			
			if(ftpFile==null) {
				throw new IOException(String.format("%s 파일이 없음.", path));
			}else {
				File tempDir = Files.createTempDirectory(tempFolder.toPath(), PREFIX).toFile();
				return saveToTempFolder(path, ftpFile, ftpClient, tempDir);
			}
			
		}finally {
			ftpClientPool.returnObject(ftpClient);
		}
	}
	
	@Override
	public File receiveFiles(String[] paths) throws Exception {
		
		FTPClient ftpClient = ftpClientPool.borrowObject();
		try {
			File tempDir = Files.createTempDirectory(tempFolder.toPath(), PREFIX).toFile();
			
			for(String path : paths) {
				FTPFile ftpFile = ftpClient.mlistFile(path);
				
				if(ftpFile==null) {
					throw new IOException(String.format("%s 파일이 없음.", path));
				}else {
					saveToTempFolder(path, ftpFile, ftpClient, tempDir);
				}
			}
			
			return tempDir;
		}finally {
			ftpClientPool.returnObject(ftpClient);
		}
	}
	
	
	/**
	 * 재귀 호출 형태로 단건 파일을 임시 폴더에 저장하거나 특정 디렉토리 계층구조상의 전체 파일 구조를 임시 폴더에 저장함.
	 * @param path 저장할 대상
	 * @param ftpFile FTP 서버상의 저장 대상에 대한 meta data
	 * @param ftpClient
	 * @param parent 저장할 폴더.
	 * @return
	 * @throws Exception
	 */
	private File saveToTempFolder(String path, FTPFile ftpFile, FTPClient ftpClient, File parent) throws Exception{
		File newFile = new File(parent, ftpFile.getName());
		newFile.getParentFile().mkdirs();
		
		if(ftpFile.isDirectory()){
			newFile.mkdirs();
			FTPFile[] files = ftpClient.mlistDir(path);
			
			for(FTPFile curFile : files) {
				String curPath = String.format("%s/%s", path, curFile.getName());
				saveToTempFolder(curPath, curFile, ftpClient, newFile);
			}
			
		}else if(ftpFile.isFile()) {
			if(newFile.createNewFile()) {
				try(
					FileOutputStream fos = new FileOutputStream(newFile);	
				){
					ftpClient.retrieveFile(path, fos);			
				}
			}
		}else {
			return null;
		}
		return newFile;
	}

}
