$(document).ready(function() {
	desktop_util.intitializeWindow();
	initialize();
});
var desktop_util = {

	headerHeight : 50,/* Header height in pixel */
	footerHeight : 25,/* Footer height in pixel */
	panelGap : 15,/* Gap between left and right panel in pixel */
	leftPanelWidth : 0,

	/**
	 * This function initializes the window pane.
	 */
	intitializeWindow : function() {

		this.setHeaderHeight();
		this.setFooterHeight();
		this.setContentHeight();
		this.setPanelWidth();
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
				- this.footerHeight - 13;
		var topMargin = this.headerHeight + 5;
		$("#content").css("height", contentHeight + "px").css("top",
				topMargin + "px");
		$("div[class=panel-content]").css("height", (contentHeight - 50) + "px");
	},

	/**
	 * This function sets the width of left (30%) and right(70%) panel based on
	 * screen width.
	 */
	setPanelWidth : function() {
		var windowWidth = $(window).innerWidth();
		var leftPanelWidth = Math.floor((windowWidth * 20) / 100);
		var rightPanelWidth = windowWidth - leftPanelWidth - this.panelGap;
		this.leftPanelWidth = leftPanelWidth;

		$("div.left-panel").css("width", leftPanelWidth + "px");
		$("div.right-panel").css("width", rightPanelWidth + "px");
		/*$("#nav").css("width", (leftPanelWidth - 5) + "px");*/
	},

};