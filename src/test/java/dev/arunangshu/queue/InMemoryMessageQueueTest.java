package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InMemoryMessageQueueTest {

    private InMemoryMessageQueue queue;
    private Topic topic;
    private Consumer consumer;

    @BeforeEach
    public void setup() {
        topic = Mockito.mock(Topic.class);
        TopicFactory topicFactory = Mockito.mock(TopicFactory.class);
        when(topicFactory.createTopic(anyString())).thenReturn(topic);
        queue = new InMemoryMessageQueue(topicFactory);
        consumer = Mockito.mock(Consumer.class);
    }

    @Test
    public void testCreateTopic() {
        String topicName = "testTopic";
        queue.createTopic(topicName);
        assertThrows(IllegalArgumentException.class, () -> queue.createTopic(topicName));
    }

    @Test
    public void testDeleteTopic() {
        String topicName = "testTopic";
        queue.createTopic(topicName);
        queue.deleteTopic(topicName);
        assertThrows(IllegalArgumentException.class, () -> queue.deleteTopic(topicName));
    }

    @Test
    public void testPublishMessage() {
        String topicName = "testTopic";
        String message = "testMessage";
        queue.createTopic(topicName);
        queue.publishMessage(topicName, message);
        verify(topic, times(1)).publishMessage(message);
    }

    @Test
    public void testSubscribeToTopic() {
        String topicName = "testTopic";
        queue.createTopic(topicName);
        queue.subscribeToTopic(topicName, consumer);
        verify(topic, times(1)).addConsumer(consumer);
    }

    @Test
    public void testUnsubscribeFromTopic() {
        String topicName = "testTopic";
        queue.createTopic(topicName);
        queue.unsubscribeFromTopic(topicName, consumer);
        verify(topic, times(1)).removeConsumer(consumer);
    }

    @Test
    public void testShutdown() {
        String topicName = "testTopic";
        queue.createTopic(topicName);
        queue.shutdown();
        verify(topic, times(1)).shutdown();
    }
}
