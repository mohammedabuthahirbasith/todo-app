package dev.basith.code.demo.controller;

import dev.basith.code.demo.service.TodoService;
import dev.basith.code.demo.models.Todo;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@Slf4j
public class TodoController {
    @Autowired
    private TodoService todoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Retrived Successfully"),
            @ApiResponse(responseCode ="404", description = "Todo was not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getTodoById(@PathVariable long id) {
        try {
            Todo createdTodo = todoService.getTodoById(id);
            return new ResponseEntity<>(createdTodo, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Error");
            log.warn("Warning");
            log.error("Error :", e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getTodosPaged(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getAllTodoPages(page, size), HttpStatus.OK);
    }

    @GetMapping("/todos")
    ResponseEntity<List<Todo>> getAllTodos() {
        try {
            List<Todo> allTodos = todoService.getAllTodos();
            return  new ResponseEntity<>(allTodos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Request Param

    @GetMapping
    String getTodoByParam(@RequestParam("todoId") long id) {
        return "Todos with Request Param id " + id;
    }

    @PostMapping("/create")
    ResponseEntity<Todo> createUser(@Valid @RequestBody Todo todo) {
      return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
    }

//    @PatchMapping("/{id}")
//    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {
//        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
//    }

    @PutMapping()
    ResponseEntity<Todo> updateTodoById(@RequestBody Todo todo) {

        return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteTodoById(@PathVariable long id) {
        todoService.deleteTodoById(id);
    }

    @DeleteMapping()
    void deleteAllTodos() {
        todoService.deleteAllTodos();
    }


}
