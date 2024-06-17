package com.example.fancode;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports.json"},
    features = "src/test/java/featureFile",
    glue = "com.example.fancode"
)
public class RunCucumberTest {
}
