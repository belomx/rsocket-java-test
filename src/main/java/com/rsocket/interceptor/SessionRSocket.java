package com.rsocket.interceptor;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public final class SessionRSocket implements RSocket {

  private final RSocket delegate;
  private AtomicInteger counterReq;

  public SessionRSocket(RSocket delegate) {
    this.delegate = Objects.requireNonNull(delegate, "delegate must not be null");
    counterReq = new AtomicInteger (0);
  }

  @Override
  public void dispose() {
    delegate.dispose();
  }

  @Override
  public Mono<Void> fireAndForget(Payload payload) {
    return delegate.fireAndForget(payload).doFinally((signalType -> {}));
  }

  @Override
  public Mono<Void> metadataPush(Payload payload) {
    return delegate.metadataPush(payload).doFinally(signalType -> {});
  }

  @Override
  public Mono<Void> onClose() {
    return delegate.onClose();
  }

  @Override
  public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
    return delegate.requestChannel(payloads)
    		.doOnEach(signalType -> {
    			System.out.println("Número de chamadas = "+counterReq.getAndIncrement());
    		})
    		.doOnNext(signalType -> {
    			System.out.println("Número de chamadas = "+counterReq.getAndIncrement());
    		})
    		.doFinally(signalType -> {System.out.println("Número de chamadas = "+counterReq.get());});
  }

  @Override
  public Mono<Payload> requestResponse(Payload payload) {
    return Mono.defer(
        () -> {
          return delegate
              .requestResponse(payload)
              .doFinally(signalType -> {});
        });
  }

  @Override
  public Flux<Payload> requestStream(Payload payload) {
    return delegate.requestStream(payload).doFinally(signalType -> {});
  }
  
}
