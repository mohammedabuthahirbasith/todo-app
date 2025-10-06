package dev.basith.code.demo.service;

import dev.basith.code.demo.models.Todo;
import dev.basith.code.demo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(@Valid  Todo todo){
        return todoRepository.save(todo);
    }

    public  Todo getTodoById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Page<Todo>getAllTodoPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public Todo updateTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodoById(Long id){
        try {
            todoRepository.delete(getTodoById(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteAllTodos(){
        todoRepository.deleteAll();
    }


}
