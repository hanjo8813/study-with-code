package com.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class DummyRestClientConfig {

    @Bean
    public DummyHttpInterface dummyHttpInterface() {
        // client -> adapter -> proxy factory !!
        RestClient client = RestClient.builder()
                .baseUrl("http://localhost:8081/dummy")
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();
        return proxyFactory.createClient(DummyHttpInterface.class);
    }
}
