package com.findwise.kerberos;

/**
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 */

import com.sun.security.auth.module.Krb5LoginModule;

import javax.security.auth.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This is simple Java program that tests ability to authenticate
 * with Kerberos using the JDK implementation.
 * <p>
 * The program uses no libraries but JDK itself.
 */
public class KrbTest {

    private void loginImpl(final String propertiesFileName) throws Exception {
        System.out.println("NB: system property to specify the krb5 config: [java.security.krb5.conf]");
        //System.setProperty("java.security.krb5.conf", "/etc/krb5.conf");

        System.out.println(System.getProperty("java.version"));

        System.setProperty("sun.security.krb5.debug", "true");
        System.setProperty("java.security.krb5.conf", "C:\\_kerberos\\kerberos.conf");

        final Subject subject = new Subject();

        final Krb5LoginModule krb5LoginModule = new Krb5LoginModule();
        final Map<String, String> optionMap = new HashMap<String, String>();

        if (propertiesFileName == null) {
            optionMap.put("keyTab", "C:\\_kerberos\\svc_pintdev_3676.keytab");
            optionMap.put("principal", "HTTP/pintdev-3676.pintdev.local@PINTDEV.LOCAL"); // default realm

            optionMap.put("doNotPrompt", "true");
            optionMap.put("refreshKrb5Config", "true");
            optionMap.put("useTicketCache", "false");
            optionMap.put("renewTGT", "false");
            optionMap.put("useKeyTab", "true");
            optionMap.put("storeKey", "true");
            optionMap.put("isInitiator", "true");
        } else {
            File f = new File(propertiesFileName);
            System.out.println("======= loading property file [" + f.getAbsolutePath() + "]");
            Properties p = new Properties();
            InputStream is = new FileInputStream(f);
            try {
                p.load(is);
            } finally {
                is.close();
            }
            optionMap.putAll((Map) p);
        }
        optionMap.put("debug", "true"); // switch on debug of the Java implementation

        krb5LoginModule.initialize(subject, null, new HashMap<String, String>(), optionMap);

        boolean loginOk = krb5LoginModule.login();
        System.out.println("======= login:  " + loginOk);

        boolean commitOk = krb5LoginModule.commit();
        System.out.println("======= commit: " + commitOk);

        System.out.println("======= Subject: " + subject);
    }

    public static void main(String[] args) {
        KrbTest krbTest = new KrbTest();
        try {
            krbTest.loginImpl(null);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());

        }
    }
}
