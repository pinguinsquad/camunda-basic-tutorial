package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class FaktenCheckDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Thread.sleep(10000);
        Object shouldFail = execution.getVariable("shouldFail");
        if (shouldFail != null) {
            throw new IllegalArgumentException("nönödu");
        }

        Long anzahlTage = (Long)execution.getVariable("anzahlTage");
        if (anzahlTage > 5){
            execution.setVariable("vorgesetzter", "Anna");
        } else {
            execution.setVariable("vorgesetzter", "Paul");
        }
    }


}
