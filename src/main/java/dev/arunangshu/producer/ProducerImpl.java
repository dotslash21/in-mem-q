package dev.arunangshu.producer;

import dev.arunangshu.queue.MessageQueue;

public class ProducerImpl implements Producer {

    private final MessageQueue messageQueue;

    public ProducerImpl(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void publishMessage(final String topicName, final String message) {
        messageQueue.publishMessage(topicName, message);
    }
}
