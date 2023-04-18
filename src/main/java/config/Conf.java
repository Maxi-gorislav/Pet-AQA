package config;

import lombok.extern.java.Log;

import java.util.Optional;

@Log
public class Conf {
    private static CoreConfig coreConfig;
    private static UiConfig uiConfig;

    public static CoreConfig core() {
        return returnConfig(coreConfig, CoreConfig.class);
    }

    public static UiConfig browserConf() {
        return returnConfig(uiConfig, UiConfig.class);
    }

    private static <T> T returnConfig(T conf, Class<T> type) {
        if (conf == null) {
            conf = getConfig(type);
        }
        return conf;
    }

    public static String getEnvironment() {
        String env = System.getProperty("env");
        if (env == null) {
            log.info("Environment variable 'env' is not set, env=dev will be used");
            return "dev";
        } else {
            log.info("Environment variable 'env' is " + env);
            return env;
        }
    }

    public static <T> T getConfig(Class<T> type) {
        YamlConfUrl annotation = Optional.ofNullable(type.getAnnotation(YamlConfUrl.class))
                .orElseThrow(
                        () -> new RuntimeException("Config class " + type.getName() + " not properly configured!"));

        String url = annotation.configUrl().replace("{env}", getEnvironment().toLowerCase());
        if (ConfigLoader.isConfigExist(url)) {
            return ConfigLoader.yamlLoadConfig(url, type);
        } else {
            return ConfigLoader.yamlLoadConfig(annotation.configUrl().replace("{env}", "dev"), type);
        }
    }
}
