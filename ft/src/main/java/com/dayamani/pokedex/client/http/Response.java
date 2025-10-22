package com.dayamani.pokedex.client.http;

import java.util.Map;

public record Response(int statusCode, String body, Map<String, String> headers) {}

