package com.wlopezob.personav1.config;

import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UtilCustom {
  public Consumer<HttpHeaders> headersMdcConsumer() {
    return httpHeaders -> MDC.getCopyOfContextMap()
        .forEach(httpHeaders::add);

  }
}
