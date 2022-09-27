package com.self.education.delivery.resource;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.self.education.delivery.api.AreaRequest;
import com.self.education.delivery.api.AreaResponse;
import com.self.education.delivery.api.ErrorResponse;
import com.self.education.delivery.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/areas")
public class AreaResource {

    private final AreaService areaService;

    //@formatter:off
    @Operation(
            summary = "Get all areas",
            description = "Endpoint for getting all areas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            })
    //@formatter:on
    @GetMapping
    public ResponseEntity<List<AreaResponse>> findAllAreas(
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "isDelivery", required = false) final Boolean isDelivery) {
        return ok(areaService.findAll(name, isDelivery));
    }

    //@formatter:off
    @Operation(
            summary = "Update area information",
            description = "Endpoint for updating information about area",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    //@formatter:on
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateArea(@PathVariable final Long id, @RequestBody final AreaRequest request) {
        areaService.updateArea(id, request);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
