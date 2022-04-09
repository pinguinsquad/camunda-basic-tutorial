package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class BenachrichtigenDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Boolean genehmigt = (Boolean)execution.getVariable("genehmigt");
        String initiator = (String)execution.getVariable("initiator");

        if (genehmigt != null && genehmigt) {
        System.out.println("hallo "+initiator+" du darfst urlaub machen");
        } else {
            System.out.println("hallo "+initiator+" du darfst nicht");
        }
    }
}
