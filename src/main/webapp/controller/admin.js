var initialize = function() {
	// alert('init');
	$(".menu>li>a").on("click", function(e) {
		// alert('hi');
		$(this).parent().find(".sub-menu").slideToggle();
		$(this).toggleClass("selected-menu");
	});

	$(".sub-menu>li>a").on("click", function(e) {
		$("a.selected-item").removeClass("selected-item");
		$(this).addClass("selected-item");

	});

	$("#notify-select").click(function() {
		$("#notify-select-icon").toggle();
		$("#notify-msg").toggle();
	});

	$(".menu-item").click(function() {
		admin_menu_controller.handleMenuSelection(this);
	});

	$(".app-menu-item").click(
			function() {
				$("label.app-menu-item.selected-app-menu-item").removeClass(
						"selected-app-menu-item");
				$(this).addClass("selected-app-menu-item");
				$("#app-setting>div#app-detail").hide();
				$("#app-setting>div.app-" + $(this).prop("id")).show();
			});
};

var admin_menu_controller = {
	handleMenuSelection : function(el) {
		$(".right-panel>.panel-content>div").hide();
		$(".right-panel>.panel-heading").text($(el).prop("title"));
		$(".right-panel>.panel-content>div#" + $(el).attr("for")).show();
	}
};