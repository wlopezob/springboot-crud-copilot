package com.wlopezob.personav1.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ApplicationProperties {

  @Value("${spring.application.name}")
  private String applicationName;
}
