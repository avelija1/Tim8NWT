package com.example.demo.Controllers;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Activity;

@RestController
public class ActivityController {


    @GetMapping("/activity")
    @ResponseBody
    public Activity activity(@RequestParam(value="name", defaultValue="World") String name) {
        return new Activity("Test",null,null,null);
    }
    
 
}