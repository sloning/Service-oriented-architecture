package com.example.proxy.controller;

import com.example.proxy.model.LengthFilterDto;
import com.example.proxy.model.OrderedRoutesDto;
import com.example.proxy.model.Response;
import com.example.proxy.model.Route;
import com.example.proxy.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Navigator", description = "Second web-service's REST proxy")
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class NavigatorController {

    private final RouteService routeService;

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
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid length",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"Invalid length\"}")
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
                                            @ExampleObject(value = "{\"message\":\"There are no routes\"}")
                                    }
                            )
                    )
            }
    )
    @PostMapping("/route")
    public Route getRouteByLength(@RequestBody @Parameter(description = "Get route by length (shortest or longest)", examples = {@ExampleObject(name = "Example with all fields", value = "{\"idFrom\":1,\"idTo\":2,\"length\":shortest}")}) LengthFilterDto lengthFilterDto) {
        return routeService.getRouteByLength(lengthFilterDto);
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
                                            @ExampleObject(value = "[{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}]")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"Invalid request\"}")
                                    }
                            )
                    ),
            }
    )
    @PostMapping("/routes")
    public List<Route> getOrderedRoutes(@RequestBody @Parameter(description = "Get routes ordered by field (shortest or longest)", examples = {@ExampleObject(name = "Example with all fields", value = "{\"idFrom\":1,\"idTo\":2,\"orderBy\":\"name\"}")}) OrderedRoutesDto orderedRoutesDto) {
        return routeService.getOrderedRoutes(orderedRoutesDto);
    }
}
