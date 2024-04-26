package dev.arunangshu.producer;

import dev.arunangshu.queue.MessageQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ProducerImplTest {

    private MessageQueue messageQueue;
    private ProducerImpl producer;

    @BeforeEach
    public void setup() {
        messageQueue = Mockito.mock(MessageQueue.class);
        producer = new ProducerImpl(messageQueue);
    }

    @Test
    public void testPublishMessage() {
        String topicName = "topic1";
        String message = "Message 1";
        producer.publishMessage(topicName, message);

        verify(messageQueue).publishMessage(topicName, message);
    }
}
