package ro.dma.dcpm.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.dma.dcpm.book.httpclient.review.ReviewServiceClient;

import java.time.Duration;

@Configuration
@EnableFeignClients(clients = {ReviewServiceClient.class})
public class SpringCloudConfiguration {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
        // default 1 s
        return factory -> factory.configure(builder ->
                builder
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10)).build()
                        ), "ReviewServiceClientgetReviewsForBookLong");
    }
}
