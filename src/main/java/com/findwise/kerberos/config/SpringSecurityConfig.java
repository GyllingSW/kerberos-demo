package com.findwise.kerberos.config;

import com.findwise.kerberos.localhost.LocalhostAuthFilter;
import com.findwise.kerberos.localhost.LocalhostAuthProvider;
import com.findwise.kerberos.security.RoleStrippingLdapUserDetailsMapper;
import com.kerb4j.client.SpnegoClient;
import com.kerb4j.common.jaas.sun.Krb5LoginContext;
import com.kerb4j.server.spring.*;
import com.kerb4j.server.spring.jaas.sun.SunJaasKerberosTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * SpringSecurityConfig:
 *
 * This is our main security configuration - It follows very closely the
 * examples provided by Spring.IO - Spring Security Kerberos.
 *
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 * @link http://docs.spring.io/spring-security-kerberos/docs/current/reference/htmlsingle/
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.ad-server}")
    private String adServer;

    @Value("${app.service-principal}")
    private String servicePrincipal;

    @Value("${app.keytab-location}")
    private String keytabLocation;

    @Value("${app.ldap-search-base}")
    private String ldapSearchBase;

    @Value("${app.ldap-search-filter}")
    private String ldapSearchFilter;

    @Value("${app.ldap-role-prefix}")
    private String ldapRolePrefix;

    @Value("${app.ldap-role-attribute}")
    private String ldapRoleAttribute;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .exceptionHandling()
                    .authenticationEntryPoint(spnegoEntryPoint())
                    .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().hasRole("APP-PS2-SIK-FOR")
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .addFilterBefore(
                        spnegoAuthenticationProcessingFilter(authenticationManagerBean()),
                        BasicAuthenticationFilter.class
                );

//                .addFilterAt(
//                        spnegoAuthenticationProcessingFilter(authenticationManagerBean()),
//                        BasicAuthenticationFilter.class
//                )
//                .addFilterBefore(
//                        localhostAuthFilter(authenticationManagerBean()),
//                                BasicAuthenticationFilter.class
//                );
    }

    /**
     * This ensures a global configuration for the security of the application.
     *
     * @param auth
     * @param spnegoAuthenticationProvider
     */
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth,
//                                   LocalhostAuthProvider localhostAuthProvider,
                                   SpnegoAuthenticationProvider spnegoAuthenticationProvider) {
        auth
//                .authenticationProvider(localhostAuthProvider)
                .authenticationProvider(spnegoAuthenticationProvider);
    }

    /**
     * Provide the default Spring Authentication Manager bean.
     * This is used by the SpnegoAuthenticationProcessingFilter as
     * part of the configuration.
     *
     * @return
     * @throws Exception
     *
     * @see SpnegoAuthenticationProcessingFilter
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    /**
//     * LocalhostAuthProvider:
//     * Provided with the username from the LocalAuthFilter the LocalhostAuthProvider
//     * calls Ldap and extracts the roles of the current user.
//     *
//     * @return a configured localhost auth provider
//     */
//    @Bean
//    public LocalhostAuthProvider localhostAuthProvider() {
//        LocalhostAuthProvider localhostAuthProvider = new LocalhostAuthProvider();
//        localhostAuthProvider.setUserDetailsService(ldapUserDetailsService());
//        return localhostAuthProvider;
//    }

//    /**
//     * LocalhostAuthFilter:
//     * Graps the SPNEGO request before the Kerberos based SPNEGO authentication filter
//     * and shortcuts the system to allow for local users. (The developer use cae).
//     *
//     * @param authenticationManager - Standard Spring Security
//     * @return a configured LocalHostAuth filter.
//     */
//    @Bean
//    public LocalhostAuthFilter localhostAuthFilter(AuthenticationManager authenticationManager) {
//        LocalhostAuthFilter localhostAuthFilter = new LocalhostAuthFilter();
//        localhostAuthFilter.setAuthenticationManager(authenticationManager);
//        return localhostAuthFilter;
//    }


    /**
     * Setup SpnegoEntryPoint to point to the login
     * page provided by the login.jsp page.
     *
     * @return
     */
    @Bean
    public SpnegoEntryPoint spnegoEntryPoint() {
        return new SpnegoEntryPoint("/login");
    }

    /**
     * SpnegoAuthenticationProcessingFilter:
     *
     * This is your friendly SSO filter, that kindly automatically
     * logs the user in if the Browser provides the actual credentials
     *
     * @param authenticationManager - with BeanIds.AUTHENTICATION_MANAGER
     * @return
     *
     * @See AuthenticationManager
     */
    @Bean
    public SpnegoAuthenticationProcessingFilter spnegoAuthenticationProcessingFilter(
            AuthenticationManager authenticationManager) {
        SpnegoAuthenticationProcessingFilter filter = new SpnegoAuthenticationProcessingFilter();

        SpnegoMutualAuthenticationHandler successHandler = new SpnegoMutualAuthenticationHandler();
        filter.setAuthenticationSuccessHandler(successHandler);

        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    ExtractGroupsUserDetailsService extractGroupsUserDetailsService() {
        ExtractGroupsUserDetailsService extractGroupsUserDetailsService = new ExtractGroupsUserDetailsService();
        extractGroupsUserDetailsService.setSpnegoClient(SpnegoClient.loginWithKeyTab(servicePrincipal,keytabLocation));
        return  extractGroupsUserDetailsService;
    }

    /**
     * KerberosServiceAuthenticationProvider:
     *
     * This bean is needed by the global AuthenticationManager bean as the only
     * accepted authentication providers.
     *
     * To actually provide Spring Security with the required user details the
     * LdapUserDetailsService is provided to the service auth provider.
     *
     * The Ldap service will not be used until the TicketValidator has granted
     * general access.
     *
     * @see LdapUserDetailsService
     * @return - A configured Kerberos Service Auth Provider
     */
    @Bean
    public SpnegoAuthenticationProvider spnegoAuthenticationProvider() {
        SpnegoAuthenticationProvider provider = new SpnegoAuthenticationProvider();
        provider.setTicketValidator(sunJaasKerberosTicketValidator());
        provider.setExtractGroupsUserDetailsService(extractGroupsUserDetailsService());
        provider.setServerSpn(servicePrincipal);
        return provider;
    }

    /**
     * SunJaasKerberosTicketValidator
     *
     * This bean will on behalf of the web application validate the visiting users provided
     * Kerberos Ticket. This will not kick in if the underlying JAAS and KRB5 configuration is
     * not working as expected.
     *
     * Find the values of the servicePrincipal and keytabLocation in application.properties
     *
     * @return - A Kerberos Ticket Validator
     */
    @Bean
    public SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {
        SunJaasKerberosTicketValidator ticketValidator = new SunJaasKerberosTicketValidator();
        ticketValidator.setServicePrincipal(servicePrincipal);
        ticketValidator.setKeyTabLocation(new FileSystemResource(keytabLocation));

        return ticketValidator;
    }

//    /**
//     * KerberosLdapContextSource
//     *
//     * This bean will contact the AD to initiate extraction of the actual user details
//     * using the classic Kerberos KRB5 login configuration
//     * @return a configured KerberosLdapContext
//     */
//    @Bean
//    public Krb5LoginContext krb5LoginContext() {
//        Krb5LoginContext krb5LoginContext = Krb5LoginContext.loginWithKeyTab(servicePrincipal,keytabLocation);
//        return krb5LoginContext;
//    }
//
//
//    /**
//     * LdapUserDetailsService:
//     *
//     * This is the bean, that does the magic in the ActiveDirectory to access the
//     * visiting users profile and list of attributes. To make things work the
//     * expected way it is required to configure both a FilterBasedLdapSearch and a UserDetailsMapper
//     *
//     * @return - a configured LdapUserDetailsService.
//     *
//     * @see FilterBasedLdapUserSearch
//     * @see RoleStrippingLdapUserDetailsMapper
//     * @see LdapUserDetailsMapper
//     */
//    @Bean
//    public LdapUserDetailsService ldapUserDetailsService() {
//        Krb5LoginContext krb5LoginContext = Krb5LoginContext.loginWithKeyTab(servicePrincipal,keytabLocation);
//        FilterBasedLdapUserSearch userSearch =
//                new FilterBasedLdapUserSearch(ldapSearchBase, ldapSearchFilter, krb5LoginContext);
//        LdapUserDetailsService service = new LdapUserDetailsService(userSearch);
//        service.setUserDetailsMapper(ldapUserDetailsMapper());
//        return service;
//    }
//
//    /**
//     * !! NOTE - Kerberos configuration trick #5 !!
//     * When an AD acts as LDAP the actual roles, that the user is granted
//     * is located in the "memberof" attribute. The LdapUserDetailsMapper
//     * needs to be aware of this variable otherwise any user will be denied access
//     * as soon as any role based access pattern is used in the HttpSecurity builder.
//     *
//     * @return a customized user details mapper.
//     *
//     */
//    @Bean
//    public LdapUserDetailsMapper ldapUserDetailsMapper() {
//        RoleStrippingLdapUserDetailsMapper ldapUserDetailsMapper = new RoleStrippingLdapUserDetailsMapper();
//        ldapUserDetailsMapper.setRolePrefix(ldapRolePrefix);
//        String[] roleAttributes = new String[1];
//        roleAttributes[0] = ldapRoleAttribute;
//        ldapUserDetailsMapper.setRoleAttributes(roleAttributes);
//        return ldapUserDetailsMapper;
//    }

}
