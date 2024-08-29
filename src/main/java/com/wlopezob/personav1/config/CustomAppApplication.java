package com.wlopezob.personav1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CustomAppApplication implements CommandLineRunner {

    @Autowired
    private OpenApiProperties openApiProperties;

    @Override
    public void run(String... args) throws Exception {

    }
    @Bean
    public OpenAPI customOpenApi(){
        Info info = new Info();
        info.setTitle(openApiProperties.getTitle());
        info.setVersion(openApiProperties.getVersion());
        info.setDescription(openApiProperties.getDescription());
        OpenAPI openAPI =  new OpenAPI().info(info);
        Supplier<List<Server>> obtenerListadoServidores = () ->
                Optional.ofNullable(openApiProperties.getServers())
                        .map(x -> x.stream()
                                .map(s -> new Server().url(s)).collect(Collectors.toList())
                        ).orElse(new ArrayList<>());
        openAPI.servers(obtenerListadoServidores.get());
        return openAPI;
    }
}
