package io.rsocket.rpc.testing.protobuf;

/**
 */
@javax.annotation.Generated(
    value = "by RSocket RPC proto compiler (version 0.2.12)",
    comments = "Source: SimpleService.proto")
public interface BlockingSimpleService {
  String SERVICE_ID = "io.rsocket.rpc.testing.SimpleService";
  String METHOD_REQUEST_REPLY = "RequestReply";
  String METHOD_STREAMING_REQUEST_AND_RESPONSE = "StreamingRequestAndResponse";

  /**
   * <pre>
   * Request / Response
   * </pre>
   */
  io.rsocket.rpc.testing.protobuf.SimpleResponse requestReply(io.rsocket.rpc.testing.protobuf.SimpleRequest message, io.netty.buffer.ByteBuf metadata);

  /**
   * <pre>
   * Streaming Request / Streaming Response
   * </pre>
   */
  Iterable<io.rsocket.rpc.testing.protobuf.SimpleResponse> streamingRequestAndResponse(Iterable<io.rsocket.rpc.testing.protobuf.SimpleRequest> messages, io.netty.buffer.ByteBuf metadata);
}
