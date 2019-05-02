package io.rsocket.rpc.testing.protobuf;

/**
 */
@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler",
    comments = "Source: SimpleService.proto")
public interface SimpleService {
  String SERVICE = "io.rsocket.rpc.testing.SimpleService";
  String METHOD_REQUEST_REPLY = "RequestReply";
  String METHOD_STREAMING_REQUEST_AND_RESPONSE = "StreamingRequestAndResponse";

  /**
   * <pre>
   * Request / Response
   * </pre>
   */
  reactor.core.publisher.Mono<io.rsocket.rpc.testing.protobuf.SimpleResponse> requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message, io.netty.buffer.ByteBuf metadata);

  /**
   * <pre>
   * Streaming Request / Streaming Response
   * </pre>
   */
  reactor.core.publisher.Flux<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(org.reactivestreams.Publisher<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages, io.netty.buffer.ByteBuf metadata);
}
