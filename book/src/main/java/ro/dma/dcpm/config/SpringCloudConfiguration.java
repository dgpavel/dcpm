package ro.dma.dcpm.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ro.dma.dcpm.book.httpclient.review.ReviewServiceClient;

import static ro.dma.dcpm.book.httpclient.review.ReviewServiceClient.REVIEW_SERVICE_NAME;

@Configuration
@EnableDiscoveryClient
public class SpringCloudConfiguration {

    @LoadBalanced
    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public ReviewServiceClient createReviewServiceClient(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://" + REVIEW_SERVICE_NAME).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ReviewServiceClient.class);
    }

}
