package org.jufe.konfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "anmeldetool")
public class AnmeldeToolKonfiguration {
    // Beispielproperties. Nur für den fall, dass wir iwann man was konfigurieren müssen
    private String host;
    private String baseurl;
}
