package dev.arunangshu;

import dev.arunangshu.consumer.Consumer;
import dev.arunangshu.consumer.ConsumerImpl;
import dev.arunangshu.producer.Producer;
import dev.arunangshu.producer.ProducerImpl;
import dev.arunangshu.queue.InMemoryMessageQueue;
import dev.arunangshu.queue.InMemoryTopicFactory;
import dev.arunangshu.queue.MessageQueue;

public class Main {

    public static void main(String[] args) {
        MessageQueue mq = new InMemoryMessageQueue(new InMemoryTopicFactory());

        mq.createTopic("topic1");
        mq.createTopic("topic2");

        Producer producer1 = new ProducerImpl(mq);
        Producer producer2 = new ProducerImpl(mq);

        Consumer consumer1 = new ConsumerImpl("consumer1", mq);
        Consumer consumer2 = new ConsumerImpl("consumer2", mq);
        Consumer consumer3 = new ConsumerImpl("consumer3", mq);
        Consumer consumer4 = new ConsumerImpl("consumer4", mq);
        Consumer consumer5 = new ConsumerImpl("consumer5", mq);

        consumer1.subscribe("topic1");
        consumer2.subscribe("topic1");
        consumer3.subscribe("topic1");
        consumer4.subscribe("topic1");
        consumer5.subscribe("topic1");
        consumer1.subscribe("topic2");
        consumer3.subscribe("topic2");
        consumer4.subscribe("topic2");

        producer1.publishMessage("topic1", "Message 1");
        producer1.publishMessage("topic1", "Message 2");
        producer2.publishMessage("topic1", "Message 3");
        producer1.publishMessage("topic2", "Message 4");
        producer2.publishMessage("topic2", "Message 5");

        // Wait for 5 seconds before shutting down the message queue
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mq.shutdown();
    }
}