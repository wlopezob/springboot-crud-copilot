package com.wlopezob.personav1.config;

import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;

@Slf4j
public class RequestDecorator extends ServerHttpRequestDecorator {

  private final ServerHttpRequest request;
  private final ServerHttpResponse response;
  private final LoggerUtil loggerUtil;

  public RequestDecorator(ServerHttpRequest delegate, ServerHttpResponse response,
      LoggerUtil loggerUtil) {
    super(delegate);
    this.request = delegate;
    this.response = response;
    this.loggerUtil = loggerUtil;
  }

  @Override
  public Flux<DataBuffer> getBody() {
    return super.getBody().map(dataBuffer -> {
      byte[] bytes = new byte[dataBuffer.readableByteCount()];
      dataBuffer.read(bytes);
      // Release the buffer
      DataBufferUtils.release(dataBuffer);
      this.loggerUtil.logRequestDetails(request, bytes);
      return this.response.bufferFactory().wrap(bytes);
    });
  }
}
