package com.example.kafkaexample.ksqlConfig;

import io.confluent.ksql.rest.entity.InsertAck;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class AcksSubscriber implements Subscriber<InsertAck> {
    private Subscription subscription;

    public AcksSubscriber() {}

    @Override
    public synchronized void onSubscribe(Subscription subscription) {
        System.out.println("Subscriber is subscribed.");
        this.subscription = subscription;

        // Request the first ack
        subscription.request(1);
    }

    @Override
    public synchronized void onNext(InsertAck ack) {
        System.out.println("Received an ack for insert number: " + ack.seq);

        // Request the next ack
        subscription.request(1);
    }

    @Override
    public synchronized void onError(Throwable t) {
        System.out.println("Received an error: " + t);
    }

    @Override
    public synchronized void onComplete() {
        System.out.println("Inserts stream has been closed.");
    }
}
