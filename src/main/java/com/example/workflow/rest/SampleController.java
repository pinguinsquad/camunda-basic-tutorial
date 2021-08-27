package com.example.workflow.rest;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping(path = "/count")
    public long getProcessCount(){
        return runtimeService.createProcessInstanceQuery().count();
    }
    
}
