package util;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import listener.TestAppender;
import listener.TestLogger;


/**
 * The AutoLog class will be used for logging during the automated tests. It
 * uses log4j to perform the logging. It does not need to be initialized before
 * using. As soon as you try to get the logger it will initialize it if
 * necessary.
 * 
 * @author Brijesh Gajera
 */
public class AutoLog {
	private static final String LOG_DIRECTORY = "AutoLog";
	private static final Level LEVEL = Level.ALL;
	public static final String LOG_PATTERN = "%d{ISO8601} %-20C %-5p - %m%n"; // date, class name, level, message, new
																				// line
	private static final InheritableThreadLocal<Logger> tlsLogger = new InheritableThreadLocal<>();

	private static String logDirectory;
	public static String logFilename;
	
	public static final String FILEAPPNAME="autoLogAppender";

	private AutoLog() {
	}

	public static void init(Class<?> testClass, TestLogger testLogger) {

		// initialize log4j Logger
		tlsLogger.set(Logger.getLogger(testClass));
		tlsLogger.get().setLevel(LEVEL);

		// create layout
		PatternLayout layout = new PatternLayout(LOG_PATTERN);

		// add logging to console
		tlsLogger.get().addAppender(new ConsoleAppender(layout));

		// add test logging
		if (testLogger != null) {
			tlsLogger.get().addAppender(new TestAppender(layout, testLogger));
		}

		// create logging directory
		logDirectory = "./src/test/resources/" + LOG_DIRECTORY;
		File fileLogDir = new File(logDirectory);
		if (fileLogDir.mkdir() || fileLogDir.exists()) {// if directory already exists, mkdir() will return false so
														// check exists() for that case
			// generate log file name from class name
			String className = testClass.getName();
			logFilename = logDirectory + File.separator + className + ".txt";

			// add logging to file
			try {
				FileAppender autoAppender = new FileAppender(layout, logFilename, true);
				autoAppender.setName(FILEAPPNAME);
				tlsLogger.get().addAppender(autoAppender);
			} catch (IOException e) {
				tlsLogger.get().error("Could not create logging file.", e);
			}
		} else{
			tlsLogger.get().error("Could not create logging directory.");
		}
	}

	/**
	 * Get the automation logger.
	 * 
	 * @return the logger
	 */
	public static Logger getLogger() {
		return tlsLogger.get();
	}

	/**
	 * Get the directory the log file will be written to. Useful if testing a
	 * component that generates its own log files, such as import tool.
	 * 
	 * @return the log directory
	 */
	public static String getLogDirectory() {
		return logDirectory;
	}

}