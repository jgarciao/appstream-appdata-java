package org.freedesktop.appstream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.bind.JAXBException;
import java.io.File;

import org.freedesktop.appstream.appdata.Component;
import org.freedesktop.appstream.appdata.Components;

/**
 * Unit test for simple App.
 */
public class AppdataParserTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppdataParserTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppdataParserTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testParseGnomeAppsXML()
    {

        File file = new File("/home/jorge/IdeaProjects/Flathub/appstream-appdata/src/test/resources/appstream-gnome-apps.xml");

        try {
            Components components = AppdataParser.parseFile(file);
            assertTrue(components.getComponent().get(0) != null);

            Component component = components.getComponent().get(0);

            assertTrue(true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        assertTrue( true );
    }




}
