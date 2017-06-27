package com.findwise.kerberos.localhost;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Karthikeyan Vaithilingam
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 *
 * @Link https://seenukarthi.com/security/2014/08/13/localhost-authentication-spring-kerberos/
 */
public class LocalhostAuthToken extends AbstractAuthenticationToken {

    private final Object principal;

    /**
     * <p>Constructor for LocalhostAuthenticationToken.</p>
     *
     * @param principal a {@link java.lang.Object} object.
     */
    public LocalhostAuthToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    /**
     * <p>Constructor for LocalhostAuthenticationToken.</p>
     *
     * @param principal a {@link java.lang.Object} object.
     * @param authorities a {@link java.util.Collection} object.
     */
    public LocalhostAuthToken(Object principal,
                                        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    /**
     * <p>getCredentials.</p>
     *
     * @return a {@link java.lang.Object} object.
     */
    public Object getCredentials() {
        return null;
    }

    /**
     * <p>Getter for the field <code>principal</code>.</p>
     *
     * @return the principal
     */
    public Object getPrincipal() {
        return this.principal;
    }
}
