package com.example.workflow.rest;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @GetMapping(path = "/count")
    public long getProcessCount(){
        taskService.createTaskQuery().taskUnassigned().count();
        return runtimeService.createProcessInstanceQuery().activityIdIn("Activity_0gk9vtp").count();
    }

    @PostMapping(path = "/start")
    @RolesAllowed("user")
    public String startProcess(@RequestBody Long anzahlTage){
        Map<String, Object> variables = new HashMap<>();
        variables.put("anzahlTage", anzahlTage);
        identityService.setAuthenticatedUserId("admin");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("urlaubsantrag", variables);
        return processInstance.getId();
    }
    
}
