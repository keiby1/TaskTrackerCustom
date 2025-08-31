package com.example.TaskTrackerCustom;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    private static final String TASKS_FILE = "src/main/resources/static/tasks.json";
    private static final String TAGS_FILE = "src/main/resources/static/tags.json";

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/api/tasks")
    @ResponseBody
    public ResponseEntity<String> getTasks() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(TASKS_FILE)));
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.ok("[]");
        }
    }

    @PostMapping("/api/tasks")
    @ResponseBody
    public ResponseEntity<String> saveTasks(@RequestBody String tasksJson) {
        try {
            Files.write(Paths.get(TASKS_FILE), tasksJson.getBytes());
            return ResponseEntity.ok("{\"status\": \"success\"}");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"Failed to save tasks\"}");
        }
    }

    @GetMapping("/api/tags")
    @ResponseBody
    public ResponseEntity<String> getTags() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(TAGS_FILE)));
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.ok("[]");
        }
    }
}
