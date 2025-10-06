package dev.basith.code.demo.repository;


import dev.basith.code.demo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
