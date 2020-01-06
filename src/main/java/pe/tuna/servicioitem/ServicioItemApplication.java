package pe.tuna.servicioitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Si en caso tuvieramos mas clientes tendriamos que utilizar la anotacion en plural
@RibbonClient(name = "servicio-productos")
@EnableFeignClients
@SpringBootApplication
public class ServicioItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioItemApplication.class, args);
    }

}
