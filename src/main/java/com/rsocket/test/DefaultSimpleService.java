package com.rsocket.test;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Publisher;

import io.netty.buffer.ByteBuf;
import io.rsocket.RSocketFactory;
import io.rsocket.rpc.testing.protobuf.SimpleRequest;
import io.rsocket.rpc.testing.protobuf.SimpleResponse;
import io.rsocket.rpc.testing.protobuf.SimpleService;
import io.rsocket.rpc.testing.protobuf.SimpleServiceServer;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DefaultSimpleService implements SimpleService {	
	
	private final DirectProcessor<SimpleRequest> processor;
	private final Flux<SimpleResponse> responseFlux;	
	private Map<String, AtomicInteger> requestContext = new ConcurrentHashMap<>();
	
	public DefaultSimpleService ()
	{
	    processor = DirectProcessor.create();
	    responseFlux = createResponseFlux();
	}	
	
	private Flux<SimpleResponse> createResponseFlux() {
		    return processor
		             .log()
		             // map the requests to numbers
		             .map( (x) -> {
		            	 AtomicInteger contextCounter = requestContext.get(x.getRequestMessage());
		            	 if (contextCounter == null)
		            	 {
		            		 contextCounter = new AtomicInteger(0);
		            		 requestContext.put(x.getRequestMessage(), contextCounter);
		            	 }		            	 		            	 
		            	 return SimpleResponse.newBuilder().setResponseMessage(x.getRequestMessage()+" - The current total is " + contextCounter.addAndGet(1)).build();
		             })
		             // drop messages we don't want to sample
		             .onBackpressureDrop()
		             // use publish and refCount to allow multiple subscribers
		             .publish()
		             .refCount();
	}	
    
    @Override
    public Mono<SimpleResponse> requestReply(SimpleRequest message, ByteBuf metadata) {
        return Mono.fromCallable(
          () ->
              SimpleResponse.newBuilder()
                  .setResponseMessage("we got the message -> " + message.getRequestMessage())
                  .build());
    }
    
    @Override
    public Flux<SimpleResponse> streamingRequestAndResponse(Publisher<SimpleRequest> messages, ByteBuf metadata) {
//      return Flux.from(messages)
//    		  .flatMap(e -> requestReply(e, metadata))    		  
//    		  .doFinally((signalType) -> {System.out.println("FIM! - "+signalType);});
    	
    	
    	
        // add the incoming channel to be processed, and limit stream to 8 at a time
    	Flux.from(messages).limitRate(8).subscribe(processor::onNext);
    	return responseFlux;
    }   
        
    public static void startServer ()
    {
    	SimpleServiceServer serviceServer = 
    			new SimpleServiceServer(new DefaultSimpleService(), Optional.empty(), Optional.empty());
    	RSocketFactory
    	        .receive()
    	        .acceptor((setup, sendingSocket) -> {
    	        	return Mono.just(serviceServer);
    	        	})
    	        .transport(TcpServerTransport.create(8081))
    	        .start()
    	        .block();
    }
    
    public static void main (String args[]) throws InterruptedException
    {
    	startServer();
    	Thread.currentThread().join();  	
    }
    
}
