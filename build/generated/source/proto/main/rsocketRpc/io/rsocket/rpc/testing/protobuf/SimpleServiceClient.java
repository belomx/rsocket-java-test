package io.rsocket.rpc.testing.protobuf;

@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: SimpleService.proto")
@io.rsocket.rpc.annotations.internal.Generated(
    type = io.rsocket.rpc.annotations.internal.ResourceType.CLIENT,
    idlClass = SimpleService.class)
public final class SimpleServiceClient implements SimpleService {
  private final io.rsocket.RSocket rSocket;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>, ? extends org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>> requestReply;
  private final java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>, ? extends org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>> streamingRequestAndResponse;
  private final java.util.function.Function<java.util.Map<String, String>, java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>, ? extends org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>>> requestReplyTrace;
  private final java.util.function.Function<java.util.Map<String, String>, java.util.function.Function<? super org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>, ? extends org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleResponse>>> streamingRequestAndResponseTrace;

  public SimpleServiceClient(io.rsocket.RSocket rSocket) {
    this.rSocket = rSocket;
    this.requestReply = java.util.function.Function.identity();
    this.streamingRequestAndResponse = java.util.function.Function.identity();
    this.requestReplyTrace = io.rsocket.rpc.tracing.Tracing.trace();
    this.streamingRequestAndResponseTrace = io.rsocket.rpc.tracing.Tracing.trace();
  }

  public SimpleServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.rSocket = rSocket;
    this.requestReply = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", SimpleService.SERVICE, "method", SimpleService.METHOD_REQUEST_REPLY);
    this.streamingRequestAndResponse = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", SimpleService.SERVICE, "method", SimpleService.METHOD_STREAMING_REQUEST_AND_RESPONSE);
    this.requestReplyTrace = io.rsocket.rpc.tracing.Tracing.trace();
    this.streamingRequestAndResponseTrace = io.rsocket.rpc.tracing.Tracing.trace();
  }


  public SimpleServiceClient(io.rsocket.RSocket rSocket, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.requestReply = java.util.function.Function.identity();
    this.streamingRequestAndResponse = java.util.function.Function.identity();
    this.requestReplyTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, SimpleService.METHOD_REQUEST_REPLY, io.rsocket.rpc.tracing.Tag.of("rsocket.service", SimpleService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
    this.streamingRequestAndResponseTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, SimpleService.METHOD_STREAMING_REQUEST_AND_RESPONSE, io.rsocket.rpc.tracing.Tag.of("rsocket.service", SimpleService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
  }


  public SimpleServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry, io.opentracing.Tracer tracer) {
    this.rSocket = rSocket;
    this.requestReply = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", SimpleService.SERVICE, "method", SimpleService.METHOD_REQUEST_REPLY);
    this.streamingRequestAndResponse = io.rsocket.rpc.metrics.Metrics.timed(registry, "rsocket.client", "service", SimpleService.SERVICE, "method", SimpleService.METHOD_STREAMING_REQUEST_AND_RESPONSE);
    this.requestReplyTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, SimpleService.METHOD_REQUEST_REPLY, io.rsocket.rpc.tracing.Tag.of("rsocket.service", SimpleService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
    this.streamingRequestAndResponseTrace = io.rsocket.rpc.tracing.Tracing.trace(tracer, SimpleService.METHOD_STREAMING_REQUEST_AND_RESPONSE, io.rsocket.rpc.tracing.Tag.of("rsocket.service", SimpleService.SERVICE), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.role", "client"), io.rsocket.rpc.tracing.Tag.of("rsocket.rpc.version", ""));
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public reactor.core.publisher.Mono<io.rsocket.rpc.testing.protobuf.SimpleResponse> requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message) {
    return requestReply(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public reactor.core.publisher.Mono<io.rsocket.rpc.testing.protobuf.SimpleResponse> requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message, io.netty.buffer.ByteBuf metadata) {
  java.util.Map<String, String> map = new java.util.HashMap<>();
    return reactor.core.publisher.Mono.defer(new java.util.function.Supplier<reactor.core.publisher.Mono<io.rsocket.Payload>>() {
      @java.lang.Override
      public reactor.core.publisher.Mono<io.rsocket.Payload> get() {
        final io.netty.buffer.ByteBuf data = serialize(message);
        final io.netty.buffer.ByteBuf tracing = io.rsocket.rpc.tracing.Tracing.mapToByteBuf(io.netty.buffer.ByteBufAllocator.DEFAULT, map);
        final io.netty.buffer.ByteBuf metadataBuf = io.rsocket.rpc.frames.Metadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, SimpleService.SERVICE, SimpleService.METHOD_REQUEST_REPLY, tracing, metadata);
        tracing.release();
        metadata.release();
        return rSocket.requestResponse(io.rsocket.util.ByteBufPayload.create(data, metadataBuf));
      }
    }).map(deserializer(io.rsocket.rpc.testing.protobuf.SimpleResponse.parser())).transform(requestReply).transform(requestReplyTrace.apply(map));
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public reactor.core.publisher.Flux<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages) {
    return streamingRequestAndResponse(messages, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public reactor.core.publisher.Flux<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages, io.netty.buffer.ByteBuf metadata) {
  java.util.Map<String, String> map = new java.util.HashMap<>();
    return rSocket.requestChannel(reactor.core.publisher.Flux.from(messages).map(
      new java.util.function.Function<com.google.protobuf.MessageLite, io.rsocket.Payload>() {
        private final java.util.concurrent.atomic.AtomicBoolean once = new java.util.concurrent.atomic.AtomicBoolean(false);

        @java.lang.Override
        public io.rsocket.Payload apply(com.google.protobuf.MessageLite message) {
          io.netty.buffer.ByteBuf data = serialize(message);
          if (once.compareAndSet(false, true)) {
            final io.netty.buffer.ByteBuf metadataBuf = io.rsocket.rpc.frames.Metadata.encode(io.netty.buffer.ByteBufAllocator.DEFAULT, SimpleService.SERVICE, SimpleService.METHOD_STREAMING_REQUEST_AND_RESPONSE, metadata);
            return io.rsocket.util.ByteBufPayload.create(data, metadataBuf);
          } else {
            return io.rsocket.util.ByteBufPayload.create(data);
          }
        }
      })).map(deserializer(io.rsocket.rpc.testing.protobuf.SimpleResponse.parser())).transform(streamingRequestAndResponse).transform(streamingRequestAndResponseTrace.apply(map));
  }

  private static io.netty.buffer.ByteBuf serialize(final com.google.protobuf.MessageLite message) {
    int length = message.getSerializedSize();
    io.netty.buffer.ByteBuf byteBuf = io.netty.buffer.ByteBufAllocator.DEFAULT.buffer(length);
    try {
      message.writeTo(com.google.protobuf.CodedOutputStream.newInstance(byteBuf.internalNioBuffer(0, length)));
      byteBuf.writerIndex(length);
      return byteBuf;
    } catch (Throwable t) {
      byteBuf.release();
      throw new RuntimeException(t);
    }
  }

  private static <T> java.util.function.Function<io.rsocket.Payload, T> deserializer(final com.google.protobuf.Parser<T> parser) {
    return new java.util.function.Function<io.rsocket.Payload, T>() {
      @java.lang.Override
      public T apply(io.rsocket.Payload payload) {
        try {
          com.google.protobuf.CodedInputStream is = com.google.protobuf.CodedInputStream.newInstance(payload.getData());
          return parser.parseFrom(is);
        } catch (Throwable t) {
          throw new RuntimeException(t);
        } finally {
          payload.release();
        }
      }
    };
  }
}
