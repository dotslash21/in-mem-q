package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;

public interface MessageQueue {

    /**
     * Create a new topic
     *
     * @param name Name of the topic
     */
    void createTopic(final String name);

    /**
     * Delete a topic
     *
     * @param name Name of the topic
     */
    void deleteTopic(final String name);

    /**
     * Publish a message to a topic
     *
     * @param topicName Name of the topic
     * @param message   Message to be published
     */
    void publishMessage(final String topicName, final String message);

    /**
     * Subscribe to a topic
     *
     * @param topicName Name of the topic
     * @param consumer Consumer to the topic
     */
    void subscribeToTopic(final String topicName, final Consumer consumer);

    /**
     * Unsubscribe from a topic
     *
     * @param topicName Name of the topic
     * @param consumer Consumer to be unsubscribed
     */
    void unsubscribeFromTopic(String topicName, Consumer consumer);

    /**
     * Shutdown the message queue
     */
    void shutdown();
}
