package dev.basith.code.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplicationHelloController {
    @GetMapping("/hello")
    String printHelloWorld() {
        return "Hello World";
    }
}
