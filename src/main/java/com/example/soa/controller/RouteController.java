package com.example.soa.controller;

import com.example.soa.model.Location;
import com.example.soa.model.Response;
import com.example.soa.model.Route;
import com.example.soa.model.WithName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Route", description = "First web-service")
@RestController
@RequestMapping("/routes")
public class RouteController {

    @Operation(
            summary = "Create new route",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(examples = {
                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-09-11T08:47:59.748Z\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
                    })
            }),
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
                            responseCode = "400",
                            description = "Invalid data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"Invalid data\"}")
                                    }
                            )
                    )
            }
    )
    @PostMapping
    public Route create(@RequestBody Route route) {
        return route;
    }

    @Operation(
            summary = "Get route by id",
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
    @GetMapping("/{id}")
    public Route getById(@PathVariable Long id) {
        return new Route();
    }

    @Operation(
            summary = "Get all routes",
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
    @GetMapping
    public List<Route> getAll(@Parameter() @RequestParam(defaultValue = "") String nameFilter,
                              @RequestParam(defaultValue = "") Long coordinatesXFilter,
                              @RequestParam(defaultValue = "") Double coordinatesYFilter,
                              @RequestParam(defaultValue = "") ZonedDateTime creationDateFilter,
                              @RequestParam(defaultValue = "") Integer locationFromFilter,
                              @RequestParam(defaultValue = "") Integer locationToFilter,
                              @RequestParam(defaultValue = "") Float distanceFilter,
                              @RequestParam(defaultValue = "") Pageable pageable) {
        return new ArrayList<>();
    }

    @Operation(
            summary = "Update route",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(examples = {
                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-09-11T08:47:59.748Z\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
                    })
            }),
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
                            responseCode = "400",
                            description = "Invalid data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"Invalid data\"}")
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
    @PutMapping
    public Route update(@RequestBody Route route) {
        return route;
    }

    @Operation(
            summary = "Delete route",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation"
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
    @DeleteMapping("/{route-id}")
    public void delete(@PathVariable("route-id") Integer routeId) {
        //should delete route
    }

    @Operation(
            summary = "Compute distances of all routes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class)
                            )
                    ),
            }
    )
    @PostMapping("/all-distances")
    public Response<Integer> computeDistances() {
        return new Response<>(0);
    }

    @Operation(
            summary = "Get object with minimum name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(oneOf = {Route.class, Location.class}),
                                    examples = {
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-09-11T08:47:59.748Z\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Nothing was found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"message\":\"There are no names\"}")
                                    }
                            )
                    )
            }
    )
    @PostMapping("/minimum-name")
    public WithName getObjectWithMinimumName() {
        if (Math.random() > 0.5) {
            return new Route();
        }
        return new Location();
    }

    @Operation(
            summary = "Get groups for \"to\" field with their usage count",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Map.class),
                                    examples = {
                                            @ExampleObject(value = "{\"Location1\":\"322\"}")
                                    }
                            )
                    )
            }
    )
    @PostMapping("/groups/{id-to}")
    public Map<String, Integer> getGroups(@PathVariable("id-to") Long idTo) {
        return new HashMap<>();
    }
}
