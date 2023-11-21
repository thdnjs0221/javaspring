/**
 * 
 */
$("a[data-role=playBtn]").on("click", function(event){
	event.preventDefault();
	let url = this.href;
	$.get(url)
	 .done(function(){
	 	$("a[data-role=playBtn]").toggleClass("disabled");
	 });
	return false;
});
$("a.nav-link").filter((idx, aTag)=>aTag.href?.endsWith(location.pathname))
			   .addClass("active")
			   .removeClass("text-white");