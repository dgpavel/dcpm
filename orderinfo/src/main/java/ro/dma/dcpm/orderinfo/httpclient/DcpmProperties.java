package ro.dma.dcpm.orderinfo.httpclient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "dcpm", ignoreUnknownFields = false)
public class DcpmProperties {
    private String bookApiPath;
    private String orderApiPath;
    private String inventoryApiPath;


}
