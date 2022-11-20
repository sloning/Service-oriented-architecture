package com.example.config;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class ServiceDiscoveryConfig {

    private final Consul client = Consul.builder()
            .withUrl(URL)
            .withAclToken(ACL_TOKEN)
            .build();

    private final AgentClient agentClient = client.agentClient();

    private static final String ADDRESS = "127.0.0.1";

    private static final String URL = "http://" + ADDRESS + ":36520";

    private static final String SERVICE_ID = "service1";

    private static final String ACL_TOKEN = "f7dd018d-21ae-78ab-ada7-c3d375b04a71";

    @PostConstruct
    public void init() throws NotRegisteredException {
        Registration service = ImmutableRegistration.builder()
                .id(SERVICE_ID)
                .name(SERVICE_ID)
                .address(ADDRESS)
                .port(36501)
                .check(Registration.RegCheck.ttl(6L)) // registers with a TTL of 6 seconds
                .build();

        agentClient.register(service);
        agentClient.pass(SERVICE_ID);
    }

    @Scheduled(fixedDelay = 5000)
    private void updateConsulHealthCheck() throws NotRegisteredException {
        agentClient.pass(SERVICE_ID);
    }

}
