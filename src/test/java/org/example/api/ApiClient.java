package org.example.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.config.ConfigManager;

public class ApiClient {

    private static final RequestSpecification REQUEST_SPEC;

    static {
        REQUEST_SPEC = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getBaseUrl())
                .addQueryParam("key", ConfigManager.getApiKey())
                .addQueryParam("token", ConfigManager.getApiToken())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification spec() {
        return REQUEST_SPEC;
    }
}
