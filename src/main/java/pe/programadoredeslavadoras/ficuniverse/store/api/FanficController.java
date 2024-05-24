package pe.programadoredeslavadoras.ficuniverse.store.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.store.domain.model.entitie.Fanfic;
import pe.programadoredeslavadoras.ficuniverse.store.domain.service.FanficService;
import pe.programadoredeslavadoras.ficuniverse.store.mapping.FanficMapper;
import pe.programadoredeslavadoras.ficuniverse.store.resource.CreateFanficResource;
import pe.programadoredeslavadoras.ficuniverse.store.resource.FanficResource;
import pe.programadoredeslavadoras.ficuniverse.shared.exception.InternalServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/ficuniverse/v1/stores", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Store", description = "Stores Management Endpoints")

public class FanficController {
    private final FanficService fanficService;
    private final FanficMapper fanficMapper;

    @Operation(
            summary = "Add a new store" ,
            description = "Add a new store",
            operationId = "addFanfic",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FanficResource.class)
                            )
                    ),
                    @ApiResponse (
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<FanficResource> save(@RequestBody CreateFanficResource resource){
        /*Fanfic fanfic = fanficMapper.toEntity(resource);
        Fanfic savedFanfic = fanficService.save(fanfic);*/
        return new ResponseEntity<>(
                fanficMapper.toResource(fanficService.save(fanficMapper.toEntity(resource))),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FanficResource>> fetchAll(){
        return new ResponseEntity<>(fanficService.fetchAll().stream().map(
                this::convertToResource)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Operation(
            summary = "Get a store by its id" ,
            description = "Gets a store fetched by its id",
            operationId = "getFanficById",
            responses = {
                    @ApiResponse (
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FanficResource.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    )
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<FanficResource> fetchById(@PathVariable("id") Integer id){
        Fanfic fanfic = fanficService.fetchById(id);
        return new ResponseEntity<>(
                fanficMapper.toResource(fanficService.fetchById(id)),
                HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<FanficResource> fetchByTitle(@PathVariable("title") String title) {
        return new ResponseEntity<>(
                fanficMapper.toResource(fanficService.findByTitle(title)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        if(fanficService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        throw new InternalServerErrorException("Fanfic", "id", String.valueOf(id), "deleted");
    }

    private FanficResource convertToResource(Fanfic fanfic){
        return fanficMapper.toResource(fanfic);
    }
}
