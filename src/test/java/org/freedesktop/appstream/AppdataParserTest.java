package org.freedesktop.appstream;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppdataParserTest
  extends TestCase {


  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public AppdataParserTest(String testName) {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(AppdataParserTest.class);
  }


  public void testParseAppdataFileGnomeApps() throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("appstream-gnome-apps.xml").getFile());

    int i = 1;

    try {
      List<AppdataComponent> componentList = AppdataParser.parseAppdataFile(file);
      assertTrue(componentList.size() > 0);

      for (AppdataComponent component : componentList) {

        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findDefaultName());
        System.out.println("Summary:        " + component.findDefaultSummary());
        System.out.println("Description:\n" + component.findDefaultDescription());

        String lang = "ca";
        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findNameByLang(lang));
        System.out.println("Summary:        " + component.findSummaryByLang(lang));
        System.out.println("Description:\n" + component.findDescriptionByLang(lang));

        //TODO:
        //Categories
        //Icons
        //Keywords (translatable)
        //kudos
        //Screenshots
        //Languages (percentage)
        //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

        i++;
      }

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    assertTrue(true);
  }

  public void testParseAppdataFileKdeApps() throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("appstream-kdeapps.xml").getFile());

    int i = 1;

    try {
      List<AppdataComponent> componentList = AppdataParser.parseAppdataFile(file);
      assertTrue(componentList.size() > 0);

      for (AppdataComponent component : componentList) {

        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findDefaultName());
        System.out.println("Summary:        " + component.findDefaultSummary());
        System.out.println("Description:\n" + component.findDefaultDescription());

        String lang = "ca";
        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findNameByLang(lang));
        System.out.println("Summary:        " + component.findSummaryByLang(lang));
        System.out.println("Description:\n" + component.findDescriptionByLang(lang));

        //TODO:
        //Categories
        //Icons
        //Keywords (translatable)
        //kudos
        //Screenshots
        //Languages (percentage)
        //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

        i++;
      }

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    assertTrue(true);
  }


  public void testParseAppdataFileEosApps() throws Exception {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("appstream-eos-apps.xml").getFile());

    int i = 1;

    try {
      List<AppdataComponent> componentList = AppdataParser.parseAppdataFile(file);
      assertTrue(componentList.size() > 0);

      for (AppdataComponent component : componentList) {

        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findDefaultName());
        System.out.println("Summary:        " + component.findDefaultSummary());
        System.out.println("Description:\n" + component.findDefaultDescription());

        String lang = "es";
        System.out
          .println("----- APP " + i + " / " + componentList.size() + " --------------------");
        System.out.println("Id:             " + component.getId());
        System.out.println("FlatpakId:      " + component.getFlatpakId());
        System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());
        System.out.println("Name:           " + component.findNameByLang(lang));
        System.out.println("Summary:        " + component.findSummaryByLang(lang));
        System.out.println("Description:\n" + component.findDescriptionByLang(lang));

        //TODO:
        //Categories
        //Icons
        //Keywords (translatable)
        //kudos
        //Screenshots
        //Languages (percentage)
        //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

        i++;
      }

    } catch (JAXBException e) {
      e.printStackTrace();
    }

    assertTrue(true);
  }


}
