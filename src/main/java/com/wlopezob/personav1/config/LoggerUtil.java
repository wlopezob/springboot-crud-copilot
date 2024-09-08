package com.wlopezob.personav1.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

@Component
@Slf4j
public class LoggerUtil {

  public void showLogRequest(ServerHttpRequest request) {
    var methods = List.of("GET", "DELETE");
    methods.stream().filter(rq -> rq.equals(request.getMethod().name()))
        .findAny()
        .ifPresent(rq -> logRequestDetails(request, null));
  }

  public void logRequestDetails(ServerHttpRequest request, byte[] bytes) {
    String method = request.getMethod().name();
    String url = request.getURI().toString();
    String headers = request.getHeaders().toString();
    String body = Optional.ofNullable(bytes)
        .map(bys -> new String(bytes, StandardCharsets.UTF_8))
        .orElse(StringUtils.EMPTY);
    log.info("Request -----> Method: {}, URL: {}, Headers: {}, Body: {}",
        method, url, headers, body);
  }

  public void logResponseDetails(ServerHttpResponse response, String body,
      ServerHttpRequest request) {
    String status = response.getStatusCode().toString();
    String headers = response.getHeaders().toString();
    String method = request.getMethod().name();
    String url = request.getURI().toString();
    log.info("Response <----- Method: {}, URL: {}, Status: {}, Headers: {}, Body: {}", method, url,
        status, headers, body);
  }
}
