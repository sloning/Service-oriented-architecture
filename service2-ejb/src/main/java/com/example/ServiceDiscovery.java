package com.example;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import com.orbitz.consul.model.health.Service;
import com.orbitz.consul.model.health.ServiceHealth;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.List;

@Singleton
public class ServiceDiscovery {

    private final Consul client = Consul.builder()
            .withUrl(URL)
            .withAclToken(ACL_TOKEN)
            .build();

    private AgentClient agentClient;

    private static final String ADDRESS = "127.0.0.1";

    private static final String URL = "http://" + ADDRESS + ":36520";

    private static final String SERVICE_ID = "service2-ejb";

    private static final String ACL_TOKEN = "f7dd018d-21ae-78ab-ada7-c3d375b04a71";

    @PostConstruct
    private void init() throws NotRegisteredException {
        agentClient = client.agentClient();

        Registration service = ImmutableRegistration.builder()
                .id(SERVICE_ID)
                .name(SERVICE_ID)
                .address(ADDRESS)
                .port(36501)
                .check(Registration.RegCheck.ttl(6L))
                .build();

        agentClient.register(service);
        agentClient.pass(SERVICE_ID);
    }

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    private void updateConsulHealthCheck() throws NotRegisteredException {
        agentClient.pass(SERVICE_ID);
    }

    public String getMainServiceAddress() {
        HealthClient healthClient = client.healthClient();

        List<ServiceHealth> nodes = healthClient.getHealthyServiceInstances("service1").getResponse();
        Service service = nodes.get(0).getService();
        return service.getAddress() + ":" + service.getPort();
    }
}
