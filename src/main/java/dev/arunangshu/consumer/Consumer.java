package dev.arunangshu.consumer;

public interface Consumer {

    /**
     * Get the consumer id
     *
     * @return the consumer id
     */
    String getConsumerId();

    /**
     * Subscribe to a topic
     *
     * @param topicName the topic name
     */
    void subscribe(String topicName);

    /**
     * Consume a message
     *
     * @param message the message
     */
    void consume(String message);
}
