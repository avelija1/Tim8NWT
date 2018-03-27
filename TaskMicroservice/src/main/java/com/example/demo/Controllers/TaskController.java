package com.example.demo.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    
    @GetMapping("/task")
    @ResponseBody
    public String task(@RequestParam(value="name", defaultValue="World") String name) {
        return "Test";
    }
}