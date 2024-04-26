package dev.arunangshu.consumer;

import dev.arunangshu.queue.MessageQueue;

public class ConsumerImpl implements Consumer {

        private final String consumerId;
        private final MessageQueue messageQueue;

        public ConsumerImpl(String consumerId, MessageQueue messageQueue) {
            this.consumerId = consumerId;
            this.messageQueue = messageQueue;
        }

        @Override
        public String getConsumerId() {
            return consumerId;
        }

        @Override
        public void subscribe(final String topicName) {
            messageQueue.subscribeToTopic(topicName, this);
        }

        @Override
        public void consume(final String message) {
            System.out.println(consumerId + " received  " + message);
        }
}
