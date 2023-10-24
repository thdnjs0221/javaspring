package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//command object 로 활용하기 (한번에 받아주기)
// @RequestParam(required =  false) String uploader
//, @RequestParam(required =  true) MultipartFile uploadFile


@Data
public class FileUploadVO {
	private String uploader;
	private MultipartFile uploadFile;
	
	private String saveName; //uploadFile를 가지고 놀기위해 만들어진 프로퍼티

	private String fileUrl;
	
	public void setUploadFile(MultipartFile uploadFile) {
		if(uploadFile!=null && !uploadFile.isEmpty()) {
			this.uploadFile = uploadFile;
			saveName = UUID.randomUUID().toString();
		}
	}
	
	//저장할 위치만 파라미터만 제대로 받으면  여기vo에서 저장 가능 
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		if(uploadFile!=null)
			uploadFile.transferTo(new File(saveFolder, saveName));
	} 
	
	public void makeFileUrl(String folderUrl) {
		if(uploadFile!=null)
			this.fileUrl = folderUrl + "/" + saveName;
	}
}










