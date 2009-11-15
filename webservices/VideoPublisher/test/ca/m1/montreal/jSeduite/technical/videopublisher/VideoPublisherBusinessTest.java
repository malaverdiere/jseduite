/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.m1.montreal.jSeduite.technical.videopublisher;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vincentbonmalais
 */
public class VideoPublisherBusinessTest {

    public VideoPublisherBusinessTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setFile method, of class VideoPublisherBusiness.
     */
    @Test
    public void testSetFile() {
        System.out.println("setFile");

        // Create all variable necessary
        // TODO modify the filename variable to test the web service
        DataInputStream dis = null;
        byte[] md5 = null;
        String filename = "/Users/vincentbonmalais/Downloads/Safari/TV/France 2 - JTLSF - 06-05-2009 06h30 10m.ts";
        
        File f = new File(filename);
        long length = f.length();

        /**
         * Need to create a byte array to send the file.
         * But, a byte array have boundaries.
         * It can't exceed the MAX_VALUE of an Integer
         */
        if(length > Integer.MAX_VALUE)
            fail("Can't convert the file into a Byte array. (too large)");

        //Create the byte array
        byte[] b = new byte[(int) length];


        try {
            dis = new DataInputStream(
                new FileInputStream(filename));

            int offset = 0;
            int numRead = 0;
            while ( (offset < b.length)
                    &&
                    ( (numRead = dis.read(b, offset, b.length-offset)) >= 0) ) {

                offset += numRead;
            }

            if (offset < b.length) {
                throw new IOException("Can't read all content for " + f.getName());
            }

            dis.close();

            /**
             * generate the MD5 for this file
             *
             */
            md5 = toMD5(b);

        } catch (IOException ex)
        {
            Logger.getLogger(VideoPublisherBusinessTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("IO exception.");
        } catch (Exception e)
        {
            Logger.getLogger(VideoPublisherBusinessTest.class.getName()).log(Level.SEVERE, null, e);
            fail("Unknown issue.");
        }

        //Access the web service
        VideoPublisherBusiness instance = new VideoPublisherBusiness();

        boolean expResult = true;
        boolean result = instance.setFile(b, md5);

        assertEquals(expResult, result);
    }

    /**
     * Test of cutFile method, of class VideoPublisherBusiness.
     */
    @Test
    public void testCutFile() {
        System.out.println("cutFile");
        String url = "";
        VideoPublisherBusiness instance = new VideoPublisherBusiness();
        boolean expResult = false;
        boolean result = instance.cutFile(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private byte[] toMD5(byte[] b) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(b, 0, b.length);
        return m.digest();
    }

}