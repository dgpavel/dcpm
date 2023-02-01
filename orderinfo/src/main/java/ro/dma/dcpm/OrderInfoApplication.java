package ro.dma.dcpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderInfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderInfoApplication.class, args);
    }

}
