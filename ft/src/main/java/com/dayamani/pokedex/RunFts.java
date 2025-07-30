package com.dayamani.pokedex;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "com.dayamani.pokedex.steps")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty")
public class RunFts {

}
