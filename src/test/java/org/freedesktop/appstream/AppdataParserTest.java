package org.freedesktop.appstream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.bind.JAXBException;
import java.io.File;

import org.freedesktop.appstream.appdata.Component;
import org.freedesktop.appstream.appdata.Components;
import org.freedesktop.appstream.appdata.Description;

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

            String descriptionStr;

            for (Component component: components.getComponent()) {

//                System.out.println("--------------------------");
//                System.out.println("Id:" + component.getId());
//                System.out.println("Name:" + AppdataParser.getName(component.getName()));
//                System.out.println("Summary:" + AppdataParser.getSummary(component.getSummary()));
//                System.out.println("Description:\n" +  AppdataParser.getDescription(component.getDescription()));

//                String lang = null;
//                System.out.println("--------------------------");
//                System.out.println("Id:" + component.getId());
//                System.out.println("Name:" + AppdataParser.getName(component.getName(), lang));
//                System.out.println("Summary:" + AppdataParser.getSummary(component.getSummary(), lang));
//                System.out.println("Description:\n" +  AppdataParser.getDescription(component.getDescription(), lang));

                //Categories
                //Icons
                //Keywords (translatable)
                //kudos
                //Screenshots
                //Languagfes (percentantge)
                //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        assertTrue( true );
    }

    public void testComponentParserWithGnomeAppsXML()
    {

        File file = new File("/home/jorge/IdeaProjects/Flathub/appstream-appdata/src/test/resources/appstream-gnome-apps.xml");

        try {
            Components components = AppdataParser.parseFile(file);
            assertTrue(components.getComponent().get(0) != null);

            String descriptionStr;

            for (Component component: components.getComponent()) {

                System.out.println("--------------------------");
                System.out.println("Id:" + component.getId());
                System.out.println("Name:" + ComponentParser.getName(component));
                System.out.println("Summary:" + ComponentParser.getSummary(component));
                System.out.println("Description:\n" +  ComponentParser.getDescription(component));

                String lang = "ca";
                System.out.println("--------------------------");
                System.out.println("Id:" + component.getId());
                System.out.println("Name:" + ComponentParser.getName(component,lang));
                System.out.println("Summary:" + ComponentParser.getSummary(component, lang));
                System.out.println("Description:\n" +  ComponentParser.getDescription(component,lang));

                //Categories
                //Icons
                //Keywords (translatable)
                //kudos
                //Screenshots
                //Languagfes (percentantge)
                //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        assertTrue( true );
    }




}
