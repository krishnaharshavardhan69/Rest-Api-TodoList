package tuto.demo;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tuto.demo.model.Todo;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class TodoController {

    @Autowired
    private TodoService TodoService;





    @PostMapping("/create") ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
        return new ResponseEntity<>(TodoService.createTodo(todo), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Todo>> getTodos() {
        return new ResponseEntity<List<Todo>>(TodoService.getTodos(), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {
        return new ResponseEntity<>(TodoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id) {
        TodoService.deleteTodo(id);
    }
    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPaged(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(TodoService.getAllTodoPages(page, size), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Retrieved Successfully"),
            @ApiResponse(responseCode = "404", description = "Todo was not found!")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable long id) {
        try {
            Todo todo = TodoService.getTodoById(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Todo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
