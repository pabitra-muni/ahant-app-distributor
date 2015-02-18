var initialize = function() {

	$(".menu>li>a").on("click", function(e) {

		$(this).parent().find(".sub-menu").slideToggle();

		$(this).toggleClass("selected-menu");
	});

	$(".sub-menu>li>a").on("click", function(e) {
		// alert("bye");
		$(this).parent().find(".inner-menu").slideToggle();
		$(this).toggleClass("selected-sub-menu");

	});

	$(".inner-menu>li>a").on("click", function(e) {
		$("a.selected-release").removeClass("selected-release");
		$(this).addClass("selected-release");

	});

	$("#notification").click(function() {
		$("#notify-select-icon").toggle();
	});
};