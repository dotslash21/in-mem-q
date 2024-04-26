package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;

public interface Topic {

    /**
     * Get the name of the topic
     *
     * @return Name of the topic
     */
    String getName();

    /**
     * Publish a message to the topic
     *
     * @param message Message to be published
     */
    void publishMessage(String message);

    /**
     * Add a consumer to the topic
     *
     * @param consumer Consumer to be added
     */
    void addConsumer(Consumer consumer);

    /**
     * Remove a consumer from the topic
     *
     * @param consumer Consumer to be removed
     */
    void removeConsumer(Consumer consumer);

    /**
     * Shutdown the topic
     */
    void shutdown();
}
