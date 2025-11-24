package com.fpt.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.fpt.stepDefinitions", "com.fpt.hooks"},
        tags = "@now"
)
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
