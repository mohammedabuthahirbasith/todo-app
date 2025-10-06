package dev.basith.code.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @NotEmpty
    @Schema(name = "title", example = "Buy Milk")
    String title;

//    @NotNull
//    @NotEmpty
//    String description;

    Boolean isCompleted;
}
