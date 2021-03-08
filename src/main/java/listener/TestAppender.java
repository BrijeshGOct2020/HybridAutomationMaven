package listener;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class TestAppender extends AppenderSkeleton {

	private TestLogger logger;

	public TestAppender() {
	}

	public TestAppender(Layout layout, TestLogger logger) {
		setLayout(layout);
		this.logger = logger;
	}

	@Override
	protected void append(LoggingEvent event) {
		if (layout == null) {
			errorHandler.error("No layout for appender " + name, null, ErrorCode.MISSING_LAYOUT);
		} else {
			String message = layout.format(event);
			logger.addMessage(message);
		}
	}

	@Override
	public void close() {
		// no-op
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

}