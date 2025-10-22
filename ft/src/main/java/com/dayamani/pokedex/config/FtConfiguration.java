package com.dayamani.pokedex.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.dayamani.pokedex")
public class FtConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AppProps appProps() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("application.yml"));
        Properties properties = yaml.getObject();

        AppProps props = new AppProps();
        props.setScheme(properties.getProperty("app.scheme"));
        props.setHost(properties.getProperty("app.host"));
        props.setPort(Integer.parseInt(properties.getProperty("app.port")));
        props.setWiremockScheme(properties.getProperty("app.wiremock-scheme"));
        props.setWiremockHost(properties.getProperty("app.wiremock-host"));
        props.setWiremockPort(Integer.parseInt(properties.getProperty("app.wiremock-port")));

        return props;
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}

