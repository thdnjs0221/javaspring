package kr.or.ddit.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 업로드 파일 하나의 해당하는 객체타입.
 * 
 *
 */
public interface MultipartFile {
	public byte[] getBytes() throws IOException;  //이진데이터를 바이트배열로..
	
	public String getContentType();

	public InputStream getInputStream() throws IOException;
	
	public String getName();  
	
	public String getOriginalFilename();
	
	public long getSize();

	public boolean isEmpty();

	public void transferTo(File dest) throws IOException;
	
	


}
