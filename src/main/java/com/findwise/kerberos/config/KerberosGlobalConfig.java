//package com.findwise.kerberos.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.kerberos.authentication.sun.GlobalSunJaasKerberosConfig;
//
//
///**
// * KerberosGlobalConfig:
// *
// * This class provides the JRE with the expected Kerberos configuration.
// *
// * !! NOTE - Kerberos configuration trick #2 !!
// * The pure JAVA implementation of Kerberos authentication cannot find the
// * actual username if this global configuration is not provided
// *
// * !! NOTE - Kerberos configuration trick #3 !!
// * Be aware, that this is in it's own @Configuration class, this is
// * by intention as if embedded in the SpringSecurityConfig class the
// * backend server part is not configured correctly by the native SUN implementation
// *
// * @author Peter Gylling - email: peter.jorgensen@findwise.com
// * @see GlobalSunJaasKerberosConfig
// * @link https://web.mit.edu/kerberos/krb5-1.12/doc/admin/conf_files/krb5_conf.html
// */
//@Configuration
//public class KerberosGlobalConfig {
//
//    @Value("${app.kerberos-conf}")
//    private String kerberosGlobalConfPath;
//
//    @Bean
//    public GlobalSunJaasKerberosConfig globalSunJaasKerberosConfig() {
//
//        GlobalSunJaasKerberosConfig globalSunJaasKerberosConfig = new GlobalSunJaasKerberosConfig();
//        //
//        // When you are up and running disable debug here
//        //
//        globalSunJaasKerberosConfig.setDebug(true);
//        globalSunJaasKerberosConfig.setKrbConfLocation(kerberosGlobalConfPath);
//        return globalSunJaasKerberosConfig;
//    }
//
//}
