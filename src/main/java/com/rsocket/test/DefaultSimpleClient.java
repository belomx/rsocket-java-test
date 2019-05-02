package com.rsocket.test;

import java.util.UUID;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.rpc.testing.protobuf.SimpleRequest;
import io.rsocket.rpc.testing.protobuf.SimpleServiceClient;
import io.rsocket.transport.netty.client.TcpClientTransport;
import reactor.core.publisher.Flux;

public class DefaultSimpleClient {

	static RSocket rSocket;
    public static SimpleServiceClient startClient ()
    {
    	rSocket = RSocketFactory
    			.connect()
    			.transport(TcpClientTransport.create(8081))
    			.start()
    			.block();
    	return new SimpleServiceClient(rSocket);
    }
	
    public static void main (String args[])
    {    	
    	String uuid = UUID.randomUUID().toString();
    	SimpleServiceClient client = startClient();
    	Flux<SimpleRequest> requests =
    		    Flux.range(1, 6)
    		        .map(s -> SimpleRequest.newBuilder().setRequestMessage(uuid).build());
    	
    	//SimpleResponse response = client.streamingRequestSingleResponse(requests).block();
    	//System.out.println(response.getResponseMessage());
    	
    	client.streamingRequestAndResponse(requests)
    	.doOnNext(response ->
    		{
    			System.out.println(response.getResponseMessage());
    		}
    	)
    	.all((res) -> {
    		System.out.println(res.getResponseMessage());
    		return Boolean.TRUE;
    	})
    	.block();
//    	.blockFirst();    
    	rSocket.dispose();
    }

}
