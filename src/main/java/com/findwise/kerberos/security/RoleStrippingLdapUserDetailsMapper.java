package com.findwise.kerberos.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

/**
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 */
public class RoleStrippingLdapUserDetailsMapper extends LdapUserDetailsMapper {

    private final Log logger = LogFactory.getLog(RoleStrippingLdapUserDetailsMapper.class);
    private String rolePrefix = "ROLE_";
    private boolean convertToUpperCase = true;

    /**
     * Creates a GrantedAuthority from a role attribute. Override to customize authority
     * object creation.
     * <p>
     * The default implementation converts string attributes to roles, making use of the
     * <tt>rolePrefix</tt> and <tt>convertToUpperCase</tt> properties. Non-String
     * attributes are ignored.
     * </p>
     *
     * The role typically looks like this:
     *  - CN=APP-PS2-USER,OU=PINTDEV Security Groups,DC=pintdev,DC=local
     *  - and will be stripped and returned as
     *  - ROLE_APP-PS2-USER as used in the SpringSecurityConfig class
     *
     * @param role the attribute returned from
     * @return the authority to be added to the list of authorities for the user, or null
     * if this attribute should be ignored.
     */
    @Override
    protected GrantedAuthority createAuthority(Object role) {
        if (role instanceof String) {
            logger.debug("Recieved role: " + role);
            if (this.convertToUpperCase) {
                role = ((String) role).toUpperCase();
            }
            //
            // Strip to known role
            //
            String activeRole = null;
            String[] roleParts = ((String) role).split(",");
            for(String part : roleParts) {
                if ( part.startsWith("CN")){
                    activeRole = part.substring(part.lastIndexOf("=")+1);
                }
            }
            if(activeRole == null)
                return null;

            return new SimpleGrantedAuthority(this.rolePrefix + activeRole);
        }
        return null;
    }

}
