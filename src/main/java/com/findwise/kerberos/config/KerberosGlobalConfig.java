package com.findwise.kerberos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.kerberos.authentication.sun.GlobalSunJaasKerberosConfig;


/**
 * Created by pidpejo72 on 14-06-2017.
 */
@Configuration
public class KerberosGlobalConfig {

    @Value("${app.kerberos-conf}")
    private String kerberosGlobalConfPath;

    @Bean
    public GlobalSunJaasKerberosConfig globalSunJaasKerberosConfig() {

        GlobalSunJaasKerberosConfig globalSunJaasKerberosConfig = new GlobalSunJaasKerberosConfig();
        globalSunJaasKerberosConfig.setDebug(true);
        globalSunJaasKerberosConfig.setKrbConfLocation(kerberosGlobalConfPath);
        return globalSunJaasKerberosConfig;
    }

}
