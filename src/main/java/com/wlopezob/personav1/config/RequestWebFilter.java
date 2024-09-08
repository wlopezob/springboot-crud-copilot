package com.wlopezob.personav1.config;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RequestWebFilter implements WebFilter {

  @Autowired
  ApplicationProperties applicationProperties;

  @Autowired
  LoggerUtil loggerUtil;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    String requestId = Optional.ofNullable(
            exchange.getRequest().getHeaders().getFirst(PropertiesConstants.REQUEST_ID))
        .orElse(UUID.randomUUID().toString());

    return Mono.just(requestId)
        .flatMap(rqId -> {
          MDC.put(PropertiesConstants.REQUEST_ID, requestId);
          MDC.put(PropertiesConstants.SERVICE_NAME, applicationProperties.getApplicationName());
          // Decorate the response
          ServerHttpResponseDecorator decoratedResponse = new
              ResponseDecorator(exchange.getResponse(), requestId, loggerUtil,
              exchange.getRequest());
          ServerHttpRequestDecorator decoratedRequest = new
              RequestDecorator(exchange.getRequest(), exchange.getResponse(), loggerUtil);
          loggerUtil.showLogRequest(exchange.getRequest());
          return chain.filter(exchange.mutate()
              .request(decoratedRequest)
              .response(decoratedResponse).build());
        });
  }
}
