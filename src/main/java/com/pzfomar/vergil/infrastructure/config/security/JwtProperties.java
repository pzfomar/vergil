package com.pzfomar.vergil.infrastructure.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    //private String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";
    //private long validityInMs = 3600000;
}
