package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryMessageQueue implements MessageQueue {

    private final ConcurrentMap<String, Topic> topics;
    private final TopicFactory topicFactory;

    public InMemoryMessageQueue(TopicFactory topicFactory) {
        this.topics = new ConcurrentHashMap<>();
        this.topicFactory = topicFactory;
    }

    @Override
    public void createTopic(String name) {
        Topic existingTopic = topics.putIfAbsent(name, topicFactory.createTopic(name));

        if (existingTopic != null) {
            throw new IllegalArgumentException("Topic already exists");
        }
    }

    @Override
    public synchronized void deleteTopic(String name) {
        if (!topics.containsKey(name)) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        topics.remove(name);
    }

    @Override
    public synchronized void publishMessage(String topicName, String message) {
        if (!topics.containsKey(topicName)) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        topics.get(topicName).publishMessage(message);
    }

    @Override
    public synchronized void subscribeToTopic(String topicName, Consumer consumer) {
        if (!topics.containsKey(topicName)) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        topics.get(topicName).addConsumer(consumer);
    }

    @Override
    public synchronized void unsubscribeFromTopic(String topicName, Consumer consumer) {
        if (!topics.containsKey(topicName)) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        topics.get(topicName).removeConsumer(consumer);
    }

    @Override
    public void shutdown() {
        for (Topic topic : topics.values()) {
            topic.shutdown();
        }
    }
}
