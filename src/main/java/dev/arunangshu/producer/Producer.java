package dev.arunangshu.producer;

public interface Producer {

    /**
     * Publishes a message to a topic.
     *
     * @param topicName the name of the topic
     * @param message the message to publish
     */
    void publishMessage(String topicName, String message);
}
