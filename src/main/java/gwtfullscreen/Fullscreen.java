package gwtfullscreen;

import static com.google.gwt.query.client.GQuery.$;

import java.util.Arrays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.query.client.GQuery;

/**
 * Fullscreen commands<br>
 * <a href="http://hacks.mozilla.org/2012/01/using-the-fullscreen-api-in-web-browsers/">Mozilla Hacks</a> <br>
 * <a href="https://github.com/robnyman/robnyman.github.com/blob/master/fullscreen/js/base.js">Javascript Example</a> <br>
 * <a hef="http://msdn.microsoft.com/en-us/library/ie/dn265028%28v=vs.85%29.aspx">MSDN</a>
 * 
 * @author wokier
 */
public class Fullscreen {

	private Fullscreen() {
	}

	public static native void requestFullscreen()
	/*-{
		var docElement = $doc.documentElement;
		if (docElement.requestFullscreen) {
			docElement.requestFullscreen();
		} else if (docElement.msRequestFullscreen) {
			docElement.msRequestFullscreen();
		} else if (docElement.mozRequestFullScreen) {
			docElement.mozRequestFullScreen();
		} else if (docElement.webkitRequestFullScreen) {
			docElement.webkitRequestFullScreen();
		}
	}-*/;

	public static void requestFullscreen(boolean input) {
		if (input) {
			requestFullscreenWithInput();
		}
		requestFullscreen();
	}

	public static native void requestFullscreenWithInput()
	/*-{
		var docElement = $doc.documentElement;
		if (docElement.webkitRequestFullScreen) {
			docElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
		} else if (docElement.requestFullscreen) {
			docElement.requestFullscreen();
		} else if (docElement.msRequestFullscreen) {
			docElement.msRequestFullscreen();
		} else if (docElement.mozRequestFullScreen) {
			docElement.mozRequestFullScreen();
		}
	}-*/;

	public static native void exitFullscreen()
	/*-{
		if ($doc.exitFullscreen) {
			$doc.exitFullscreen();
		} else if ($doc.msExitFullscreen) {
			$doc.msExitFullscreen();
		} else if ($doc.mozCancelFullScreen) {
			$doc.mozCancelFullScreen();
		} else if ($doc.webkitCancelFullScreen) {
			$doc.webkitCancelFullScreen();
		}
	}-*/;

	public static boolean isFullscreen() {
		GQuery document = $(Document.get());
		return GWT.isClient()
				&& Arrays.asList((Boolean) document.prop("fullscreen"), (Boolean) document.prop("mozFullScreen"), (Boolean) document.prop("webkitIsFullScreen"),
						document.prop("msFullscreenElement") != null).contains(Boolean.TRUE);
	}

}