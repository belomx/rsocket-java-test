package io.rsocket.rpc.testing.protobuf;

@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler (version 0.2.12)",
    comments = "Source: SimpleService.proto")
@io.rsocket.rpc.annotations.internal.Generated(
    type = io.rsocket.rpc.annotations.internal.ResourceType.CLIENT,
    idlClass = BlockingSimpleService.class)
public final class BlockingSimpleServiceClient implements BlockingSimpleService {
  private final io.rsocket.rpc.testing.protobuf.SimpleServiceClient delegate;

  public BlockingSimpleServiceClient(io.rsocket.RSocket rSocket) {
    this.delegate = new io.rsocket.rpc.testing.protobuf.SimpleServiceClient(rSocket);
  }

  public BlockingSimpleServiceClient(io.rsocket.RSocket rSocket, io.micrometer.core.instrument.MeterRegistry registry) {
    this.delegate = new io.rsocket.rpc.testing.protobuf.SimpleServiceClient(rSocket, registry);
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public io.rsocket.rpc.testing.protobuf.SimpleResponse requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message) {
    return requestReply(message, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public io.rsocket.rpc.testing.protobuf.SimpleResponse requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message, io.netty.buffer.ByteBuf metadata) {
    return delegate.requestReply(message, metadata).block();
  }

  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public  io.rsocket.rpc.BlockingIterable<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(Iterable<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages) {
    return streamingRequestAndResponse(messages, io.netty.buffer.Unpooled.EMPTY_BUFFER);
  }

  @java.lang.Override
  @io.rsocket.rpc.annotations.internal.GeneratedMethod(returnTypeClass = io.rsocket.rpc.testing.protobuf.SimpleResponse.class)
  public  io.rsocket.rpc.BlockingIterable<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(Iterable<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages, io.netty.buffer.ByteBuf metadata) {
    reactor.core.publisher.Flux stream = delegate.streamingRequestAndResponse(reactor.core.publisher.Flux.defer(() -> reactor.core.publisher.Flux.fromIterable(messages)), metadata);
    return new  io.rsocket.rpc.BlockingIterable<>(stream, reactor.util.concurrent.Queues.SMALL_BUFFER_SIZE, reactor.util.concurrent.Queues.small());
  }

}

