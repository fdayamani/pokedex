package com.dayamani.pokedex.scenario;

import com.dayamani.pokedex.client.http.Client;
import com.dayamani.pokedex.client.http.Request;
import com.dayamani.pokedex.client.http.Response;
import com.dayamani.pokedex.config.AppProps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class ScenarioState {
    private static final Logger logger = LoggerFactory.getLogger(ScenarioState.class);

    private final Client httpClient;
    private final AppProps appProps;

    private final Request request;
    private Response responseState;
    private final ObjectMapper objectMapper;

    public ScenarioState(
            AppProps appProps,
            Client httpClient,
            Request request,
            ObjectMapper objectMapper) {
        this.appProps = appProps;
        this.httpClient = httpClient;
        this.request = request;
        this.objectMapper = objectMapper;
    }

    public Request getRequest() {
        return request;
    }

    public void sendRequest() {
        logger.debug("Request: " + request);
        this.responseState = httpClient.execute(request);
        request.clear();
    }

    public void setRequestBody(Object body) {
        try {
            request.setBody(objectMapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing body object: " + body, e);
        }
    }

    public Response getResponseState() {
        return responseState;
    }

    public <T> T getResponseBodyAs(Class<T> type) {
        try {
            return objectMapper.readValue(responseState.body(), type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing http request response body: " + responseState.body(), e);
        }
    }
}
