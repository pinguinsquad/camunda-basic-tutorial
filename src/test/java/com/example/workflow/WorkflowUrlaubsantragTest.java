package com.example.workflow;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowUrlaubsantragTest extends AbstractProcessEngineRuleTest {

    @Autowired public RuntimeService runtimeService;

    @Test
    public void shouldExecuteHappyPath() {
        // given
        String processDefinitionKey = "urlaubsantrag";

        // when
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(processDefinitionKey, Map.of("anzahlTage",1L));

        // then
        assertThat(processInstance)
                .isStarted()
                .isWaitingAt("Activity_FaktenCheck");
        execute(job());

        assertThat(processInstance)
                .task()
                .hasDefinitionKey("Activity_AntragPruefen")
                .isAssignedTo("Paul");

        complete(task());
        assertThat(processInstance)
                .isStarted()
                .task()
                .hasDefinitionKey("Activity_AntragBuchen")
                .isNotAssigned();

        complete(task());
        assertThat(processInstance)
                .isEnded()
                .hasPassed("Activity_MitarbeiterBenachrichtigenHR", "Event_UrlaubsantragGebucht");
    }
}
