package dev.arunangshu.consumer;

import dev.arunangshu.queue.MessageQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ConsumerImplTest {

    private MessageQueue messageQueue;
    private ConsumerImpl consumer;

    @BeforeEach
    public void setup() {
        messageQueue = Mockito.mock(MessageQueue.class);
        consumer = new ConsumerImpl("consumer1", messageQueue);
    }

    @Test
    public void testSubscribe() {
        String topicName = "topic1";
        consumer.subscribe(topicName);

        verify(messageQueue).subscribeToTopic(topicName, consumer);
    }

    @Test
    public void testConsume() {
        String message = "Message 1";
        consumer.consume(message);

        // As the consume method only prints the message, there's no behavior to verify.
        // If it had any interaction with other objects or any state change, we could verify that.
    }
}
