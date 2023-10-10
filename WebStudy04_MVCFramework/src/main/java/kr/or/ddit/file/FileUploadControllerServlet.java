package kr.or.ddit.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.file.utils.MultipartFile;
import kr.or.ddit.filter.utils.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.ViewResolverComposite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/15/fileUpload.do")
@MultipartConfig
public class FileUploadControllerServlet extends HttpServlet {

	private String imagesUrl = "/resources/images"; // 논리주소

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
//		req.getParameter("uploadFile");
		// 서블릿 3.xx -> Part api쓰기 / 2.xx -> FileItem api쓰기
		
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile uploadFile = 
					((StandardMultipartHttpServletRequest) req).getFile("uploadFile");
		
		
		//Part uploadFile = req.getPart("uploadFile");	
		
		//[서블릿 종속성 없애기 ] (서블릿 2.xx) part -> MultipartFile로 바꾸면 2.xx,3.xx  사용가능
		// 필터로 StandardMultipartFile(어댑터)로 맵핑 (WR (어댑터))
		
		
		if(!uploadFile.getContentType().startsWith("image/")) {
			//업로드가 된 이진데이터가 파일데이터가 맞는지 확인
			resp.sendError(400);
			return;
		}
		
		
		log.info("uploader : {}", uploader);
		log.info("업로드 파일 part: {}", uploadFile);
		String realPath = req.getServletContext().getRealPath(imagesUrl); // 폴더의 논리를 물리로 바꾸기

		File imageFolder = new File(realPath); // 저장 할 폴더 위치

		String filename = uploadFile.getOriginalFilename(); // 저장 할 파일 이름
		File saveFile = new File(imageFolder, filename);

		try (
				InputStream is = uploadFile.getInputStream();
				) {
			uploadFile.transferTo(saveFile);
			String fileUrl = imagesUrl + "/" + saveFile.getName();

			HttpSession session = req.getSession();
			session.setAttribute("uploader", uploader);
			session.setAttribute("fileUrl", fileUrl);
		}

		}
		String viewName = "redirect:/15/fileUploadForm.jsp";
		new ViewResolverComposite().resolveView(viewName, req, resp);
	}

}
