package pe.tuna.servicioitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Al tener configurado Eureka ya no es necesario definir esta anotacion
// @RibbonClient(name = "servicio-productos") Si en caso tuvieramos mas clientes tendriamos que utilizar la anotacion en plural
@EnableCircuitBreaker // Habilitamos Hystrix
@EnableEurekaClient // cuando tenemos configurado un eureka
@EnableFeignClients
@SpringBootApplication
public class ServicioItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioItemApplication.class, args);
    }

}
