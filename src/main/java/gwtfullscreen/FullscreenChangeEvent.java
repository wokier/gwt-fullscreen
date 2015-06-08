package gwtfullscreen;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Event used to notify the changes. <br>
 * It is fired if it comes from the application, or from the browser, with menu or shortcut.
 * 
 * @see FullscreenChangeEventBindery
 * @author wokier
 */
public class FullscreenChangeEvent extends GwtEvent<FullscreenChangeEvent.Handler> {

	public static final Type<FullscreenChangeEvent.Handler> TYPE = new Type<FullscreenChangeEvent.Handler>();

	public interface Handler extends EventHandler {
		void onFullscreenChange(FullscreenChangeEvent event);
	}

	private boolean fullscreen;

	public FullscreenChangeEvent(boolean fullscreen) {
		super();
		this.fullscreen = fullscreen;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onFullscreenChange(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	public String toString() {
		return super.toString() + " fullscreen=" + fullscreen;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof FullscreenChangeEvent) && (fullscreen == ((FullscreenChangeEvent) obj).fullscreen);
	}

	@Override
	public int hashCode() {
		return fullscreen ? 1 : 0;
	}
}