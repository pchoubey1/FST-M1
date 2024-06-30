package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "Features",
glue = {"stepDefinitions"},
tags = "@Activity5"
)

public class ActivitiesRunner {

}
