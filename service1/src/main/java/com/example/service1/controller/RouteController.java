package com.example.service1.controller;

import com.example.service1.dto.Response;
import com.example.service1.dto.RouteUpdateRequestDto;
import com.example.service1.dto.RoutesFilterDto;
import com.example.service1.model.Location;
import com.example.service1.model.Route;
import com.example.service1.model.WithName;
import com.example.service1.service.NameService;
import com.example.service1.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Tag(name = "Routes", description = "First web-service")
@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final NameService nameService;

    @Operation(
            summary = "Create new route",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(examples = {
                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
    @PostMapping
    public Route create(@Valid @RequestBody Route route) {
        return routeService.save(route);
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
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
    @GetMapping("/{route-id}")
    public Route getById(@PathVariable("route-id") @Parameter(description = "Id of the route", example = "1") int id) {
        return routeService.getById(id);
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
                                            @ExampleObject(value = "{\"content\":[{\"id\":10,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":1662886079.748000000,\"from\":{\"id\":8,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":9,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5},{\"id\":13,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":1662886079.748000000,\"from\":{\"id\":11,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":12,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5},{\"id\":16,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":1662886079.748000000,\"from\":{\"id\":14,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":15,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"HOME\"},\"distance\":13.5}],\"pageable\":{\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":20,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":3,\"size\":20,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":3,\"empty\":false}")
                                    }
                            )
                    )
            }
    )
    @GetMapping
    public Page<Route> getAll(@Parameter(name = "Routes filters", examples = {@ExampleObject(name = "Example without creation date field", description = "ZonedDateTime doesn't work in swagger (._.')", value = "{\"nameFilter\":\"ITMO\",\"coordinatesXFilter\":59,\"coordinatesYFilter\":30.3083,\"locationFromFilter\":1,\"locationToFilter\":2,\"distanceFilter\":13.5}"), @ExampleObject(name = "Example with all fields", description = "ZonedDateTime doesn't work in swagger (._.')", value = "{\"nameFilter\":\"ITMO\",\"coordinatesXFilter\":59,\"coordinatesYFilter\":30.3083,\"creationDateFilter\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"locationFromFilter\":1,\"locationToFilter\":2,\"distanceFilter\":13.5}")}) RoutesFilterDto routesFilter,
                              @Parameter(name = "Paging and sorting", examples = {@ExampleObject(name = "Example with paging and sorting", description = "To sort fields use the following format: \"field, order\"", value = "{\"page\":0,\"size\":5,\"sort\":[\"id,desc\",\"name,asc\"]}")}) Pageable pageable) {
        return routeService.findAll(routesFilter, pageable);
    }

    @Operation(
            summary = "Update route",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
                    @Content(examples = {
                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
                                            @ExampleObject(value = "{\"id\":3,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":1,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":2,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
    public Route update(@Valid @RequestBody RouteUpdateRequestDto route) {
        return routeService.update(route);
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
    public void delete(@PathVariable("route-id") @Parameter(description = "Id of the route", example = "1") int id) {
        routeService.delete(id);
    }

    @Operation(
            summary = "Compute distances of all routes",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Response.class),
                                    examples = {
                                            @ExampleObject(value = "{\"payload\":13.5}")
                                    }
                            )
                    ),
            }
    )
    @PostMapping("/all-distances")
    public Response<Double> computeDistances() {
        return new Response<>(routeService.computeDistances());
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
                                            @ExampleObject(value = "{\"id\":1,\"name\":\"From ITMO to dometry\",\"coordinates\":{\"x\":59,\"y\":30.3083},\"creationDate\":\"2022-10-09T14:36:16.583148500+03:00[Europe/Moscow]\",\"from\":{\"id\":2,\"x\":59.9573,\"y\":30.3083,\"z\":95.5543,\"name\":\"ITMO\"},\"to\":{\"id\":3,\"x\":59.8483,\"y\":30.3294,\"z\":95.5544,\"name\":\"Dometry\"},\"distance\":13.5}")
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
    public Response<WithName> getObjectWithMinimumName() {
        return new Response<>(nameService.findMinimumName());
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
    @PostMapping("/groups")
    public Map<String, Long> getGroups() {
        return routeService.getGroups();
    }
}
