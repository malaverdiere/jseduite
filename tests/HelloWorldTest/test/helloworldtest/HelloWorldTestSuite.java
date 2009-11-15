/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package helloworldtest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author mosser
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({helloworldtest.HelloWorldProxyTest.class})
public class HelloWorldTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

}