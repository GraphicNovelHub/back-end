package pe.programadoredeslavadoras.ficuniverse.creation.api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.model.entities.Chapter;
import pe.programadoredeslavadoras.ficuniverse.creation.domain.service.ChapterService;
import pe.programadoredeslavadoras.ficuniverse.creation.mapping.ChapterMapper;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.ChapterResource;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CreateChapterResource;
import pe.programadoredeslavadoras.ficuniverse.security.domain.model.User;
import pe.programadoredeslavadoras.ficuniverse.security.resource.UserResource;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.InternalServerErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/ficuniverse/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Product", description = "Products Management Endpoints")
public class ChapterController {
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    @Operation(
            summary = "Add a new product to the store" ,
            description = "Add a new product to the store",
            operationId = "addChapter",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChapterResource.class)
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
    @PostMapping("{id}/product")
    public ResponseEntity<ChapterResource> save(@PathVariable(name = "id") Integer id,@RequestBody CreateChapterResource resource){
        return new ResponseEntity<>(
            chapterMapper.toResource(chapterService.createChapter(id,chapterMapper.toEntity(resource))),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<ChapterResource>> fetchAll(){
        return new ResponseEntity<>(chapterService.fetchAll().stream().map(
                this::convertToResource)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @Operation(
            summary = "Get a chapter by its id" ,
            description = "Gets a chapter from the fanfic fetched by its id",
            operationId = "getChapterById",
            responses = {
                    @ApiResponse (
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ChapterResource.class)
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
    @GetMapping("{id}")
    public ResponseEntity<ChapterResource> fetchById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                chapterMapper.toResource(chapterService.fetchById(id)),
                HttpStatus.OK
        );
    }

    //@GetMapping("title/{title}")
    //public Chapter fetchTitle(@PathVariable("title") String title){
    //    return chapterService.fetchByTitle(title);
    //}

    @GetMapping("title/{title}")
    public ResponseEntity<ChapterResource> fetchTitle(@PathVariable("title") String title){
        return new ResponseEntity<>(
                chapterMapper.toResource(chapterService.fetchByTitle(title)),
                HttpStatus.OK
        );
    }


    //@GetMapping("fanfic?{fanficId}")
    //public List<Chapter> fetchChaptersByFanficId(@PathVariable("fanficId") Integer fanficId){
    //    return chapterService.fetchChaptersByFanficId(fanficId);
    //}

    @GetMapping("fanfic?{fanficId}")
    public ResponseEntity<List<Chapter>> fetchChaptersByFanficId(@PathVariable("fanficId") Integer fanficId){
        return ResponseEntity.ok(chapterService.fetchChaptersByFanficId(fanficId));
    }

    @GetMapping("order/{orderInit}/{orderEnd}")
    public ResponseEntity<List<Chapter>> fetchChaptersBetweenOrder(@PathVariable("orderInit") Integer orderInit, @PathVariable("orderEnd") Integer orderEnd){
        return ResponseEntity.ok(chapterService.fetchByChapterOrderBetween(orderInit,orderEnd));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        if(chapterService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        throw new InternalServerErrorException("Chapter", "id", String.valueOf(id), "deleted");
    }

    private ChapterResource convertToResource(Chapter chapter){
        return chapterMapper.toResource(chapter);
    }

}
