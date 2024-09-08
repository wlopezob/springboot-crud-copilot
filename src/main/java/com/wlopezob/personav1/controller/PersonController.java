package com.wlopezob.personav1.controller;

import com.wlopezob.personav1.Util.ApiException;
//import com.wlopezob.personav1.config.LogWithRequestId;
import com.wlopezob.personav1.model.HeaderData;
import com.wlopezob.personav1.model.dto.PersonRequestDto;
import com.wlopezob.personav1.model.dto.PersonResponseDto;
import com.wlopezob.personav1.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @PostMapping("/save/{id}")
  @Operation(
      description = "Registra los datos de una persona",
      method = "POST",
      tags = {"persona"},
      summary = "Registra los datos de una persona y verifica que el dni no exista",
      extensions = {
          @Extension(
              name = "x-amazon-apigateway-integration",
              properties = {
                  @ExtensionProperty(name = "uri", value = "http://petstore.execute-api.us-west-2.amazonaws.com/petstore/pets"),
                  @ExtensionProperty(name = "passthroughBehavior", value = "when_no_match"),
                  @ExtensionProperty(name = "httpMethod", value = "GET"),
                  @ExtensionProperty(name = "type", value = "http"),
                  @ExtensionProperty(name = "responses.default.statusCode", value = "200"),
                  @ExtensionProperty(name = "responses.default.responseParameters.method.response.header.Access-Control-Allow-Origin", value = "'*'"),
                  @ExtensionProperty(name = "requestParameters.integration.request.querystring.page", value = "method.request.querystring.page"),
                  @ExtensionProperty(name = "requestParameters.integration.request.querystring.type", value = "method.request.querystring.type"),
                  @ExtensionProperty(name = "requestParameters.integration.request.header.clientAuth", value = "method.request.header.clientAuth"),
                  @ExtensionProperty(name = "requestParameters.integration.request.header.x-tenant", value = "method.request.header.x-tenant")
              }
          )
      }
  )

  @ApiResponses({
      @ApiResponse(
          responseCode = "200", description = "Resultado con los datos de la persona",
          content = {
              @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PersonResponseDto.class)
              )
          }
      ),
      @ApiResponse(
          responseCode = "500", description = "Error de ejecucion en el servidor",
          content = {
              @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiException.class)
              )
          }
      )
  })
  public Mono<ResponseEntity<PersonResponseDto>> savePerson01(
      @Valid @RequestBody PersonRequestDto personRequestDto,
      @RequestHeader HeaderData headerData,
      @Parameter(description = "Identificador de la persona", example = "1", in = ParameterIn.PATH)
      @PathVariable(name = "id") Long id,
      @Parameter(description = "Nombre de la persona", in = ParameterIn.QUERY)
      @RequestParam(name = "name", required = false) String name) {
    return Mono.just(personService.savePerson(personRequestDto))
        .map(ResponseEntity::ok);
  }

  @PostMapping
  @Operation(
      description = "Registra los datos de una persona",
      method = "POST",
      tags = {"persona"},
      summary = "Registra los datos de una persona y verifica que el dni no exista"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200", description = "Resultado con los datos de la persona",
          content = {
              @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = PersonResponseDto.class)
              )
          }
      ),
      @ApiResponse(
          responseCode = "500", description = "Error de ejecucion en el servidor",
          content = {
              @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ApiException.class)
              )
          }
      )
  })
  //@LogWithRequestId
  public Mono<ResponseEntity<PersonResponseDto>> savePerson(
      @Valid @RequestBody PersonRequestDto personRequestDto) {
    return Mono.just(personService.savePerson(personRequestDto))
        .map(ResponseEntity::ok);
  }

  @GetMapping("/hi")
  public Mono<String> getMethodName() {
    log.info("Application is running Hello");
    return  Mono.just("Hola");
  }

  @GetMapping("/lista")
  public Flux<PersonResponseDto> lista() {
    var persons = List.of(PersonResponseDto.builder()
            .id(1L)
            .name("w")
        .build(), PersonResponseDto.builder()
        .id(2L)
        .name("j")
        .build());
    return Flux.fromIterable(persons);
  }

  @GetMapping("/success")
  public Mono<Void> success() {
    return Mono.just("Success")
        .doOnSuccess(s -> log.info("Success"))
        .then();
  }
}