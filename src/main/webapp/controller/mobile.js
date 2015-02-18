var initialize = function() {

	$("#app-next-btn").off("click").click(function() {
		 //alert("left");
		mobile_util.moveAppSliderToLeft();
	});
	$("#app-prev-btn").off("click").click(function() {
		 //alert("right");
		mobile_util.moveAppSliderToRight();
	});
	
	$("#release-next-btn").off("click").click(function() {
		// alert("left");
		mobile_util.moveReleaseSliderToLeft();
	});
	$("#release-prev-btn").off("click").click(function() {
		// alert("right");
		mobile_util.moveReleaseSliderToRight();
	});
	
	$("div[class*=app]").off("click").click(function() {
		$("div.app.selected-app").removeClass("selected-app");
		$(this).addClass("selected-app");
	});
	
	$("div[class=release]").off("click").click(function() {
		$("div.release.selected-release").removeClass("selected-release");
		$(this).addClass("selected-release");
	});
	$("#notification").click(function(){
		$("#notify-select-icon").toggle();
	});
};