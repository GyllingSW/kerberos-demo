package com.findwise.kerberos.localhost;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Karthikeyan Vaithilingam
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 *
 * @Link https://seenukarthi.com/security/2014/08/13/localhost-authentication-spring-kerberos/
 */
public class LocalhostAuthProvider implements AuthenticationProvider, InitializingBean {

    private UserDetailsService userDetailsService;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    /** {@inheritDoc} */
    @Override
    public void afterPropertiesSet(){
        if(userDetailsService == null) {
            throw new SecurityException("property userDetailsService is null");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationProvider#
     * authenticate(org.springframework.security.core.Authentication)
     */
    /** {@inheritDoc} */
    @Override
    public Authentication authenticate(Authentication authentication){
        LocalhostAuthToken auth = (LocalhostAuthToken) authentication;
        String username = auth.getName();
        UserDetails userDetails = this.userDetailsService
                .loadUserByUsername(username);
        LocalhostAuthToken output = new LocalhostAuthToken(
                userDetails, userDetails.getAuthorities());
        return output;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.authentication.AuthenticationProvider#supports
     * (java.lang.Class)
     */
    /** {@inheritDoc} */
    @Override
    public boolean supports(Class<?> authentication) {
        return LocalhostAuthToken.class.isAssignableFrom(authentication);
    }

    /**
     * <p>Setter for the field <code>userDetailsService</code>.</p>
     *
     * @param detailsService a {@link org.springframework.security.core.userdetails.UserDetailsService} object.
     */
    public void setUserDetailsService(UserDetailsService detailsService) {
        this.userDetailsService = detailsService;
    }
}
