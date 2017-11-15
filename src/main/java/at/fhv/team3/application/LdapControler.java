package at.fhv.team3.application;

import javax.naming.*;
import java.util.Properties;

public class LdapControler {

    private String url;
    private Properties env;

    public LdapControler() {
        url = "ldap://openldap.fhv.at";
        env = new Properties();
    }

    public boolean authenticateUser(String name, String password) throws NamingException {
        boolean acess = false;
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "userid=username,ou=people,o=fhv.at");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            Context ctx = new InitialContext(env);
            ctx.close();
            acess = true;

        } catch (NamingException ex) {
            acess = false;
        }
        return acess;
    }

}
