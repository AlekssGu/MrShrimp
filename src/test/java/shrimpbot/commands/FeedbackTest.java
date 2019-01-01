package shrimpbot.commands;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.api.objects.Message;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackTest {

	@Mock
	Message receivedMessage;

	@InjectMocks
	private Feedback feedback;

	@Test
	public void process() {
		feedback.process(receivedMessage);
	}
}