package com.camunda.training;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;

import java.util.HashMap;
import java.util.Map;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessJUnitTest {


  @Rule
  @ClassRule
//  public ProcessEngineRule rule = new ProcessEngineRule();
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();


  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  @Test
  @Deployment(resources = "process.bpmn")
  public void testHappyPath() {

    // Create a HashMap to put in variables for the process instance
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("approved", true);
    variables.put("content", "Exercise 4 test - Kaio Test:  " + Math.random());

    // Start process with Java API and variables

    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("twitter-qa", variables);
    // Make assertions on the process instance
    assertThat(processInstance).isEnded();

  }

}
