package ru.geekbrains;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/cucumber-html-report"},
        features = {"classpath:features"},
//        features = {"classpath:resources"},
//        features = {"classpath:./src/test/resources"},
        glue = {"ru.geekbrains.steps"},
        snippets = SnippetType.CAMELCASE)
public class LaunchTest {

}
