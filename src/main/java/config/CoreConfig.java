package config;

import lombok.Data;

@Data
@YamlConfUrl(configUrl = "core.yaml")
public class CoreConfig {
    private String hostUrl;
    private String uiUrl;
    private String amadeusUrl;
    private String checkInLink;
    private String petPath;
    private String petUrl;
    private String clientId;
    private String secretKey;
}
