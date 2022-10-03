package com.example.soa.controller;

import com.example.soa.model.Length;
import com.example.soa.model.Response;
import com.example.soa.model.Route;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Navigator", description = "Second web-service")
@RestController
@RequestMapping("/navigator")
public class NavigatorController {

    @Operation(
            summary = "Get route by length",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Route.class),
                                    examples = {
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-09-11T08:47:59.748Z\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Route was not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"Route was not found\"}")
                                    }
                            )
                    )
            }
    )

    @PostMapping("/route/{id-from}/{id-to}/{length}")
    public Route getRouteByLength(@PathVariable("id-from") @Parameter(description = "Id of \"from\" field.", example = "0") Integer idFrom, @PathVariable("id-to") @Parameter(description = "Id of \"to\" field.", example = "0") int idTo, @PathVariable Length length) {
        return new Route();
    }

    @Operation(
            summary = "Get routes ordered by some field",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Route.class)),
                                    examples = {
                                            @ExampleObject(value = "[{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-09-11T08:47:59.748Z\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}]")
                                    }
                            )
                    )
            }
    )
    @PostMapping("/routes/{id-from}/{id-to}/{order-by}")
    public Route getOrderedRoutes(@PathVariable("id-from") @Parameter(description = "Id of \"from\" field.", example = "0") Long idFrom, @PathVariable("id-to") @Parameter(description = "Id of \"to\" field.", example = "0") int idTo, @PathVariable("order-by") @Parameter(description = "Field to order.", example = "id") String orderBy) {
        return new Route();
    }
}
