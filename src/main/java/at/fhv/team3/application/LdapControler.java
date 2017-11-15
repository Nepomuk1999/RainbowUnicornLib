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

        Context ctx = new InitialContext(env);
        browsRecursive(ctx, 0);
        return acess;
    }

    public static final void browsRecursive(Context ctx, int depth) throws NamingException {
        NamingEnumeration<Binding> namingEnum = ctx.listBindings("o=fhv.at");
        while (namingEnum.hasMore()) {
            Binding bnd = namingEnum.next();
            if (bnd.getObject() instanceof Context) {
                Context curCtx = (Context) bnd.getObject();
                for (int i = 0; i < depth * 2; i++) {
                    System.out.println(' ');
                    System.out.println(bnd.getName());
                    browsRecursive(curCtx, depth + 1);
                }
            }
        }
    }
}
