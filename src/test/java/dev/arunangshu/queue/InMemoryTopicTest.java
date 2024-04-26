package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InMemoryTopicTest {

    private InMemoryTopic inMemoryTopic;
    private Consumer mockConsumer;

    @BeforeEach
    public void setup() {
        inMemoryTopic = new InMemoryTopic("testTopic");
        mockConsumer = Mockito.mock(Consumer.class);
    }

    @Test
    public void testGetName() {
        assertEquals("testTopic", inMemoryTopic.getName());
    }

    @Test
    public void testPublishMessage() {
        inMemoryTopic.addConsumer(mockConsumer);
        inMemoryTopic.publishMessage("testMessage");

        // Wait for the message to be consumed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mockConsumer, times(1)).consume("testMessage");
    }

    @Test
    public void testAddConsumer() {
        inMemoryTopic.addConsumer(mockConsumer);
        inMemoryTopic.publishMessage("testMessage");

        // Wait for the message to be consumed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mockConsumer, times(1)).consume("testMessage");
    }

    @Test
    public void testRemoveConsumer() {
        inMemoryTopic.addConsumer(mockConsumer);
        inMemoryTopic.removeConsumer(mockConsumer);
        inMemoryTopic.publishMessage("testMessage");

        // Wait for the message to be consumed
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(mockConsumer, times(0)).consume("testMessage");
    }
}
