package com.wfdss.nextgen.servicetemplate.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "global-properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalProperties {
    private String environment;
    private int threadPool;
}
