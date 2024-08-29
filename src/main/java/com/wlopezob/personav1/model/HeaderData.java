package com.wlopezob.personav1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderData {
    @Schema(description = "Cliente que realiza la peticion",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("Client")
    String client;

    @Schema(description = "Authorization del cliente",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("ClientAuth")
    String clientAuth;

    @Schema(description = "tenant del cliente",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("X-Tenant")
    String xTenant;
}
