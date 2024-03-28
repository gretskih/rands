package ru.job4j.soap;

import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4j.soap.service.StudentsImpl;

@Configuration
public class WebServiceConfig {

    private final Bus bus;
    private final StudentsImpl studentsImpl;

    @Autowired
    public WebServiceConfig(Bus bus, StudentsImpl studentsImpl) {
        this.bus = bus;
        this.studentsImpl = studentsImpl;
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, studentsImpl);
        endpoint.publish("/Students");
        return endpoint;
    }
}
