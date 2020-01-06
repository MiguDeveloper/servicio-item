package pe.tuna.servicioitem;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Para poder usar balanceador de carga con restTemplate debemos de usar la anotacion @LoadBalanced
    @Bean("clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }
}
