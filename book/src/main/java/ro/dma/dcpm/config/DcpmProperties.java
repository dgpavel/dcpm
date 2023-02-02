package ro.dma.dcpm.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(value = "dcpm", ignoreUnknownFields = false)
public class DcpmProperties {
    private String reviewApiPath;
}
