package io.github.jangalinski.camunda

import org.camunda.bpm.engine.ProcessEngineConfiguration
import org.camunda.bpm.engine.test.Deployment
import org.camunda.bpm.extension.process_test_coverage.junit.rules.ProcessCoverageInMemProcessEngineConfiguration
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test


class TestCoverageTest {

  companion object {

    @Rule
    @ClassRule
    @JvmField
    val camunda = TestCoverageProcessEngineRuleBuilder.create(
        ProcessCoverageInMemProcessEngineConfiguration().apply {
          databaseSchemaUpdate = ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP
        }.buildProcessEngine()
    ).build()

  }

  @Test
  @Deployment(resources = ["my-process.bpmn"])
  internal fun `deploy and start`() {
    camunda.processEngine.runtimeService.startProcessInstanceByKey("my-process")


  }
}