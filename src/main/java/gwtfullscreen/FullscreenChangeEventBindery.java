package gwtfullscreen;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.Event;
import com.google.web.bindery.event.shared.EventBus;

/**
 * This bindery MUST be called in order to use <code>FullscreenChangeEvent</code><br>
 * <a href="http://hacks.mozilla.org/2012/01/using-the-fullscreen-api-in-web-browsers/">Mozilla Hacks</a> <br>
 * <a href="https://github.com/robnyman/robnyman.github.com/blob/master/fullscreen/js/base.js">Javascript Example</a> <br>
 * <a hef="http://msdn.microsoft.com/en-us/library/ie/dn265028%28v=vs.85%29.aspx">MSDN</a>
 * 
 * @see FullscreenChangeEvent
 * @author wokier
 */
public class FullscreenChangeEventBindery {

	private FullscreenChangeEventBindery() {
	}

	public static void initFullscreenChangeEvent(final EventBus eventBus) {
		if (GWT.isClient()) {
			final GQuery document = $(Document.get());
			document.bind("fullscreenchange", new Function() {
				@Override
				public boolean f(Event e) {
					eventBus.fireEvent(new FullscreenChangeEvent((Boolean) document.prop("fullscreen")));
					return true;
				}
			}).bind("mozfullscreenchange", new Function() {
				@Override
				public boolean f(Event e) {
					eventBus.fireEvent(new FullscreenChangeEvent((Boolean) document.prop("mozFullScreen")));
					return true;
				}
			}).bind("webkitfullscreenchange", new Function() {
				@Override
				public boolean f(Event e) {
					eventBus.fireEvent(new FullscreenChangeEvent((Boolean) document.prop("webkitIsFullScreen")));
					return true;
				}
			}).bind("MSFullscreenChange", new Function() {
				@Override
				public boolean f(Event e) {
					eventBus.fireEvent(new FullscreenChangeEvent(document.prop("msFullscreenElement") != null));
					return true;
				}
			});
		}
	}

	public static void unbindFullscreenChangeEvent() {
		if (GWT.isClient()) {
			$(Document.get()).unbind("fullscreenchange").unbind("mozfullscreenchange").unbind("webkitfullscreenchange").unbind("MSFullscreenChange");
		}
	}

}