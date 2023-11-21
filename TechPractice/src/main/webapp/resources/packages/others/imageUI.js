/**
 * 이미지 프리뷰 함수
 */
function readImage(image){
	let reader = new FileReader();
	let img = document.createElement("img");
	reader.onloadend=(e)=>{
		img.src = e.target.result;
	}
	reader.readAsDataURL(image);
	return img;
}

/**
 * 라벨 생성 함수
 */
function makeLabel(txt){
	let pTag = document.createElement("p");
	pTag.innerText = txt;
	return pTag;
}

window.addEventListener("DOMContentLoaded", function(){
	sendBtn.addEventListener("click", function(){
		// 이미지 원본 프리뷰
		resultArea.replaceChildren();
		resultArea.append(makeLabel("원본"));
		resultArea.append( readImage(uploadForm.targetFile.files[0]) );
		
		let formData = new FormData(uploadForm);
		fetch(uploadForm.action, {
			method : "post"
			, headers : {
				"Accept" : "image/*"
			}
			, body : formData
		}).then((resp)=>resp.blob())
		.then((blob)=>{
			// 크롭된 이미지 썸네일 뷰
			resultArea.prepend(document.createElement("hr"));
			resultArea.prepend(readImage(blob));
			resultArea.prepend(makeLabel("썸네일"));
		});
	});
});