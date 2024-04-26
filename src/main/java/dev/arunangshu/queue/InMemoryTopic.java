package dev.arunangshu.queue;

import dev.arunangshu.consumer.Consumer;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class InMemoryTopic implements Topic {

    private final String name;
    private final BlockingQueue<String> queue;
    private final ConcurrentLinkedQueue<Consumer> consumers;

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public InMemoryTopic(String name) {
        this.name = name;
        this.queue = new LinkedBlockingQueue<>();
        this.consumers = new ConcurrentLinkedQueue<>();

        executorService.submit(() -> {
            while (!isShutdown.get()) {
                String message = queue.poll();

                if (message == null) {
                    continue;
                }

                for (Consumer consumer : consumers) {
                    executorService.submit(() -> consumer.consume(message));
                }

                // Wait for 1 second before processing the next message
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void publishMessage(String message) {
        queue.add(message);
    }

    @Override
    public void addConsumer(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void removeConsumer(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public void shutdown() {
        isShutdown.set(true);
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Executor service did not terminate");
            }
        } catch (InterruptedException ie) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryTopic topic = (InMemoryTopic) o;
        return Objects.equals(name, topic.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
