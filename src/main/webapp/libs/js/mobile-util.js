$(document).ready(function() {
	mobile_util.intitializeWindow();
	initialize();
	$(window).resize(function() {
		mobile_util.intitializeWindow();
		initialize();
	});

});
var mobile_util = {
	headerHeight : 40,
	footerHeight : 25,
	appWidth : 90,
	relaseWidth : 70,

	/**
	 * This function initializes the window pane.
	 */
	intitializeWindow : function() {

		this.setHeaderHeight();
		this.setFooterHeight();
		this.setContentHeight();
		this.initializeAppSlider();
		this.initializeReleaseSlider();

	},
	/**
	 * This function sets the header height.
	 */

	setHeaderHeight : function() {
		$("#header").css("height", this.headerHeight + "px");
	},

	/**
	 * This function sets the footer height.
	 */

	setFooterHeight : function() {
		$("#footer").css("height", this.footerHeight + "px");
	},

	/**
	 * This function sets the height of the content area using the following
	 * formula. contentHeight=windowHeight-headerHeight-footerHeight-extraSpace
	 */
	setContentHeight : function() {
		var windowHeight = $(window).innerHeight();
		var contentHeight = windowHeight - this.headerHeight
				- this.footerHeight - 8;
		var topMargin = this.headerHeight + 2;
		$("#content").css("height", contentHeight + "px").css("top",
				topMargin + "px");
		$("div#content").css("height", (contentHeight) + "px");
		$("div#build-info").css("height", (contentHeight-170) + "px");
	},

	/**
	 * This function initializes the app slider.
	 */

	initializeAppSlider : function() {
		// alert('window resize');
		$("#app-slider").css("left", "20px");
		$(".app").css("width", this.appWidth + "px");
		var appList = $("#app-slider").find(".app");
		var appMargin = this.appWidth + 1;
		$.each(appList, function(i) {
			var app = appList[i];
			$(app).css("left", ((i * appMargin) + 22) + "px");

		});
	},

	/**
	 * This function initializes the release slider.
	 */
	initializeReleaseSlider : function() {
		$("#release-slider").css("left", "20px");
		$(".release").css("width", this.relaseWidth + "px");
		var releaseList = $("#release-slider").find(".release");
		var releaseMargin = this.relaseWidth + 1;
		$.each(releaseList, function(i) {
			var relase = releaseList[i];
			$(relase).css("left", ((i * releaseMargin) + 22) + "px");

		});
	},

	/**
	 * This function moves the app slider to left.
	 */

	moveAppSliderToLeft : function() {
		//alert("hi");
		var newPosition = this.getNumber($("#app-slider").css("left"))
				- this.appWidth - 1;
		// alert(newPosition);
		$("#app-slider").css("left", newPosition + "px");
	},
	
	/**
	 * This function moves the release slider to left.
	 */

	moveReleaseSliderToLeft : function() {
		// alert(this.getNumber($("#app-slider").css("left")));
		var newPosition = this.getNumber($("#release-slider").css("left"))
				- this.appWidth - 1;
		// alert(newPosition);
		$("#release-slider").css("left", newPosition + "px");
	},

	/**
	 * This function moves the app slider to left.
	 */
	moveAppSliderToRight : function() {
		// alert("right f");
		var newPosition = this.getNumber($("#app-slider").css("left"))
				+ this.appWidth + 1;
		// alert(this.getNumber($("#app-slider").css("left")));
		// alert(newPosition);
		$("#app-slider").css("left", newPosition + "px");
	},
	
	/**
	 * This function moves the release slider to left.
	 */
	moveReleaseSliderToRight : function() {
		// alert("right f");
		var newPosition = this.getNumber($("#release-slider").css("left"))
				+ this.appWidth + 1;
		// alert(this.getNumber($("#app-slider").css("left")));
		// alert(newPosition);
		$("#release-slider").css("left", newPosition + "px");
	},

	/**
	 * This function extracts the number part from css values (ignores \'px\').
	 */

	getNumber : function(val) {
		return parseInt(val.split("px")[0]);
	}
};