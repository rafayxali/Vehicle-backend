package com.rafay.backend_task.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @GetMapping("/")
    public String home() {
        return "Backend Task is running! 🚀";
    }

    @GetMapping("/test-db")
    public String testDb() {
        return "Connected to Neon PostgreSQL ✅";
    }
}
