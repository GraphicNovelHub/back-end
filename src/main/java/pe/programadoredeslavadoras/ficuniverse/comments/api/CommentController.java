package pe.programadoredeslavadoras.ficuniverse.comments.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.model.entities.Comment;
import pe.programadoredeslavadoras.ficuniverse.comments.domain.service.CommentService;
import pe.programadoredeslavadoras.ficuniverse.comments.mapping.CommentMapper;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CommentResource;
import pe.programadoredeslavadoras.ficuniverse.creation.resource.CreateCommentResource;
import pe.programadoredeslavadoras.ficuniverse.shared.exceptions.InternalServerErrorException;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/ficuniverse/v1/comments")

public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;



    @Operation(
            summary = "Add a new comment to the fanfic" ,
            description = "Add a new comment to the fanfic",
            operationId = "addComment",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentResource.class)
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
    public ResponseEntity<CommentResource> save(@RequestBody CreateCommentResource resource){
        return new ResponseEntity<>(
                commentMapper.toResource(commentService.save(commentMapper.toEntity(resource))),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Comment>> fetchAll(){
        return ResponseEntity.ok(commentService.fetchAll());
    }

    @Operation(
            summary = "Get a comment by its id" ,
            description = "Gets a comment from the fanfic fetched by its id",
            operationId = "getCommentById",
            responses = {
                    @ApiResponse (
                            responseCode = "201",
                            description = "Successful operation",
                            content = @Content (
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CommentResource.class)
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
    public ResponseEntity<CommentResource> fetchById(@PathVariable("id") Integer id){
        return new ResponseEntity<>(
                commentMapper.toResource(commentService.fetchById(id)),
                HttpStatus.OK
        );
    }

    @GetMapping("theme?{themeId}")
    public ResponseEntity<List<Comment>> fetchCommentsByTagId(@PathVariable("themeId") Integer themeId){
        return ResponseEntity.ok(commentService.fetchCommentsByThemeId(themeId));
    }

    @GetMapping("comment/{upVote}/{downVote}")
    public ResponseEntity<List<Comment>> fetchCommentsByupVotes(@PathVariable("upVote") Integer upVote, @PathVariable("downVote") Integer downVote){
        return ResponseEntity.ok(commentService.fetchCommentsByupVotes(upVote, downVote));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        if(commentService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        throw new InternalServerErrorException("Comment", "id", String.valueOf(id), "deleted");
    }

}
