package com.wlopezob.personav1.config;

import java.nio.charset.StandardCharsets;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ResponseDecorator extends ServerHttpResponseDecorator {

  private final String requestId;
  private final LoggerUtil loggerUtil;
  private final ServerHttpRequest request;

  public ResponseDecorator(ServerHttpResponse delegate,
      String requestId, LoggerUtil loggerUtil, ServerHttpRequest request) {
    super(delegate);
    this.requestId = requestId;
    this.loggerUtil = loggerUtil;
    this.request = request;
  }


  @Override
  public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
    // Add the request ID to the response headers
    getHeaders().add(PropertiesConstants.REQUEST_ID, requestId);
    if (body instanceof Mono) {
      Mono<? extends DataBuffer> monoBody = (Mono<? extends DataBuffer>) body;
      return super.writeWith(monoBody.map(dataBuffer -> {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        // Release the buffer
        DataBufferUtils.release(dataBuffer);
        String responseBody = new String(bytes, StandardCharsets.UTF_8);
        loggerUtil.logResponseDetails(getDelegate(), responseBody, this.request);
        return bufferFactory().wrap(bytes);
      }));
    } else if (body instanceof Flux) {
      Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
      return super.writeWith(fluxBody.map(dataBuffer -> {
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        // Release the buffer
        DataBufferUtils.release(dataBuffer);
        String responseBody = new String(bytes, StandardCharsets.UTF_8);
        loggerUtil.logResponseDetails(getDelegate(), responseBody, this.request);
        return bufferFactory().wrap(bytes);
      }));
    }

    return super.writeWith(body);
  }
}
