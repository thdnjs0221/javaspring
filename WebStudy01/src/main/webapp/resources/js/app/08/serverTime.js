/**
 * 
 */
$(function(){
	console.log(this.body.dataset.contextPath); // jsp에서 쓰는 contextPath가져오기
	const CPATH = this.body.dataset.contextPath
	let fnSuccesses = {
		json: function(resp){
			serverTimeArea.innerHTML = resp.now;
		},
		html: function(resp){ //html의 이름의 function
			serverTimeArea.innerHTML=resp;	
		}
}
let settings = {
			url:`${CPATH}/08/serverTime`,
			error: function(jqxhr,status,error){
						console.log("jqxhr:",jqxhr);
						console.log("status:",status);
						console.log("error:",error);
					}
	};

	
	setTimeout(() => {
		console.log("5초 뒤에 한번 실행하고 종료.")
	}, 5000);
	setInterval(() => {
		settings.dataType = $("[name=dataType]:checked").val();
		settings.success = fnSuccesses[settings.dataType]; //value값
		$.ajax(settings);
	}, 1000);
	
});