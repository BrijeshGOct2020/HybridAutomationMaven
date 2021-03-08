package listener;

import java.util.List;

public interface TestLogger {

	List<String> getMessages();

	void addMessage(String message);

}