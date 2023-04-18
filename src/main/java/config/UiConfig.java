package config;

import lombok.Data;
@Data
@YamlConfUrl(configUrl = "config.yaml")
public class UiConfig {
    private Boolean driverManager;
    private Long pollingInterval;
    private Long timeout;
    private String browserSize;
    private String browser;
}
