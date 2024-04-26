package dev.arunangshu.queue;

public interface TopicFactory {

    /**
     * Create a new topic
     *
     * @param name name of the topic
     * @return the created topic
     */
    Topic createTopic(String name);
}
