package com.dayamani.pokedex;

public class TestRunner {

    public static void main(String[] args) {
        String[] cucumberArgs = new String[]{
                "--tags", "not @wip",
                "--tags", "not @local-only",
                "--plugin", "pretty",
                "--plugin", "json:build/cucumber-reports/cucumber-cli.json",
                "--plugin", "html:build/cucumber-reports/cucumber-cli.html",
                "--glue", "com.dayamani.pokedex",
                "classpath:features"
        };

        io.cucumber.core.cli.Main.main(cucumberArgs);
    }
}
