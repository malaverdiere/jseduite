
package webadmin.util;

/**
 *
 * @author Steve Colombié
 */
public class SQLProtection {

    public static String format(String var) {
        return var.replace("\'", "''");
    }
}
