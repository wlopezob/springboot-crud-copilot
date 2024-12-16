package com.wlopezob.personav1.config;

import com.wlopezob.personav1.config.logging.MdcContextLifter;
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
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

public class CustomAppApplication implements CommandLineRunner {

    @Autowired
    private OpenApiProperties openApiProperties;

    @Override
    public void run(String... args) throws Exception {
        Hooks.enableContextLossTracking();
        Hooks.enableAutomaticContextPropagation();
        Hooks.onEachOperator("MDC", Operators.lift((scannable, coreSubscriber) ->
            new MdcContextLifter<>(coreSubscriber)));
    }

}
