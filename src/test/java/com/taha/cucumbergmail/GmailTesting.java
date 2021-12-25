package com.taha.cucumbergmail;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	
	features = "src/test/java/feature",
	glue = {"stepdefinition"},
	monochrome = true,
	plugin = {"pretty", "html:target/cucumber"}
	
	)
public class GmailTesting {
	
	
	
	

}
