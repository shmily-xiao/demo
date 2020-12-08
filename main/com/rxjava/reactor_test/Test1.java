package com.rxjava.reactor_test;

import com.rxjava.myPublisher.MyListPublisher;
import com.rxjava.myPublisher.MyPublisher;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

//
//import org.reactivestreams.Processor;
//import org.reactivestreams.Subscription;
//import reactor.Environment;
//import reactor.core.processor.RingBufferProcessor;
//import reactor.rx.Promise;
//import reactor.rx.Stream;
//import reactor.rx.Streams;
//import reactor.rx.action.support.DefaultSubscriber;
//import reactor.rx.broadcast.Broadcaster;
//
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.TimeUnit;
//
///**
// * need reactor 2.X
// */
public class Test1 {
    public static void main(String[] args) {
//        Processor<String, String> processor = RingBufferProcessor.create();
//
//        Stream<String> str1 = Streams.just("Hello ");
//        Stream<String> str2 = Streams.just("world ");
//        Stream<String> str3 = Streams.wrap(processor);
//
//        Streams.concat(str1, str2, str3)
//            .reduce((pre, next) -> pre + next)
//            .consume(s -> System.out.printf("%s greeting = %s%n", Thread.currentThread(), s));
//
//        processor.onNext("!");
//
//        processor.onComplete();
//
//        Environment env = Environment.initialize();
//        //Streams
//        //    .range(1, 100)
//        //    .switchMap(number -> Streams.range(1, number).subscribeOn(Environment.workDispatcher()) ) //1
//        //    .consume(
//        //        System.out::println, //2
//        //        Throwable::printStackTrace,
//        //        avoid -> System.out.println("--complete--")
//        //    );
//
//        Promise<List<Long>> result = Streams
//            .range(1, 100)
//            .subscribeOn(Environment.workDispatcher())
//            .toList(); //1
//
//        try {
//            System.out.println(result.await()); //2
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        result.onSuccess(System.out::println); //3
//
//        long timeout = 100;
//        final int batchsize = 4;
//        CountDownLatch latch = new CountDownLatch(1);
//
//        final Broadcaster<Integer> streamBatcher = Broadcaster.<Integer>create(env);
//        streamBatcher
//            .buffer(batchsize, timeout, TimeUnit.MILLISECONDS)
//            .consume(i -> latch.countDown());
//
//        streamBatcher.onNext(12);
//        streamBatcher.onNext(123);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        streamBatcher.onNext(42);
//        streamBatcher.onNext(666);
//
//        try {
//            latch.await(20, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        Streams.range(1,100)
//            .zipWith(Streams.generate(System::currentTimeMillis), tuple -> tuple)
//            .observe(tuple -> {
//                System.out.println(tuple.getT1());
//                System.out.println(tuple.getT2());
//            })
//            .consume(tuple -> System.out.println("numbers: " + tuple.getT1() + " time: "+tuple.getT2()),
//                Throwable::printStackTrace,
//                avoid -> System.out.println(" --- complete -- ")
//            );
//
//
//
//
//
//
        System.out.println("---------------20200720----------------");
        List<Integer> list = new ArrayList<>(3);
        List<List<Integer>> lists = new ArrayList<>(3);

        Flux.range(0,4)
            .map(list::add)
            .subscribe();

        Flux.range(0,3)
            .map(item -> lists.add(list))
            .subscribe();



        //MyListSubscriber subscriberA = new MyListSubscriber();
        MyListPublisher publisher = new MyListPublisher();
        SampleSubscriber sampleSubscriber = new SampleSubscriber<List<Integer>>();
        Scheduler myThread = Schedulers.newParallel("myThread", 4);
       Flux
            .from(publisher)
            //.from((Publisher<List<Integer>>)s -> {
            //    System.out.println( "1st " + Thread.currentThread().getName());
            //    for (List<Integer> listItem:lists){
            //        //System.out.println(listItem);
            //        s.onNext(listItem);
            //    }
            //    //lists.forEach(s::onNext);
            //})
            //.publishOn(myThread)
            .log()
            //.flatMap(sublist -> {
            //    System.out.println( "2st " + Thread.currentThread().getName());
            //    return Flux.from((Publisher<Integer>) s -> {
            //        System.out.println( "3st " + Thread.currentThread().getName());
            //        sublist.forEach(s::onNext);
            //    }).log();
            //})
            .log()
            .doOnNext(item -> {
                System.out.println("4st " + Thread.currentThread().getName());
            })
            .doOnError(item -> {
                System.out.println("error ");
                item.printStackTrace();
            })
            //.count()
            .collectList()
            .doOnSuccess(item -> {
                System.out.println("doOnSuccess");
                System.out.println(item);
            })
            //.delaySubscription(Duration.ofMillis(100))
            //.subscribe(sampleSubscriber);
            .subscribe(System.out::println, item -> item.printStackTrace(), ()->{ })
            .dispose();

        //.subscribe(subscriberA);

        try {
            Thread.sleep(1000);
        }catch (Exception e){}

        //
        //Flux.range(0,3)
        //    .log()
        //    .flatMap(i -> (Publisher<Integer>) s -> s.onNext(i))
        //    .subscribe(System.out::println);


        System.out.println("-------------------20200720-2---------------");
        //Flux.fromIterable(lists)
        //    .log()
        //    //.doOnNext(item -> {
        //    //    System.out.println(Thread.currentThread().getName());
        //    //    System.out.println(item);
        //    //})
        //    //.flatMap(sublist -> {
        //    //            System.out.println(Thread.currentThread().getName());
        //    //            System.out.println("sublist");
        //    //            return Flux.from((Publisher<Integer>) s -> {
        //    //                sublist.forEach(s::onNext);
        //    //            }).log();
        //    //        })
        //    .flatMap(sublist -> {
        //        System.out.println(Thread.currentThread().getName());
        //        System.out.println("sublist");
        //
        //        return Flux.fromIterable(sublist).log();
        //    })
        //    .log()
        //    .doOnNext(item -> {
        //        System.out.println(Thread.currentThread().getName());
        //        System.out.println(item);
        //    })
        //    .collectList()
        //    .doOnSuccess(item -> {
        //        System.out.println("doOnSuccess");
        //        System.out.println(item);
        //    })
        //    //.delaySubscription(Duration.ofMillis(10))
        //    .subscribe(System.out::println);

    }

}
