package com.wlopezob.personav1.config.properties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("lwp")
@Getter
@Setter
public class LwpApiProperties {
  private Http http;
  private Apis apis;

  // getters and setters

  @Getter
  @Setter
  public static class Http {
    private List<String> headers;

    // getters and setters
  }

  @Getter
  @Setter
  public static class Apis {
    private String urlDataPersona;

    // getters and setters
  }
}
