package org.freedesktop.appstream;

import org.freedesktop.appstream.appdata.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jorge on 08/04/17.
 */
public class AppdataParser {

    public static Components parseFile(File file) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Components.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Components components = (Components) jaxbUnmarshaller.unmarshal(file);

        return components;

    }

    public static String getName(List<Name> nameList) {

        for (Name name : nameList) {
            if (name.getLang() == null) return name.getValue();
        }

        return "";
    }

    public static String getName(List<Name> nameList, String lang) {

        for (Name name : nameList) {
            if (lang.equalsIgnoreCase(name.getLang())) return name.getValue();
        }

        return "";
    }

    public static String getSummary(List<Summary> summaryList) {

        for (Summary summary : summaryList) {
            if (summary.getLang() == null) return summary.getValue();
        }

        return "";

    }

    public static String getSummary(List<Summary> summaryList, String lang) {

        for (Summary summary : summaryList) {
            if (lang.equalsIgnoreCase(summary.getLang())) return summary.getValue();
        }

        return "";
    }

    public static String getDescription(List<Description> descriptionList) {

        for (Description description : descriptionList) {
            if (description.getLang() == null) return getObjectListAsString(description.getContent());
        }

        return "";

    }

    public static String getDescription(List<Description> descriptionList, String lang) {

        for (Description description : descriptionList) {
            if (lang.equalsIgnoreCase(description.getLang())) return getObjectListAsString(description.getContent());
        }

        return "";

    }


    public static String getObjectListAsString(List<Object> objectList) {

        String contents = "";

        for (Object obj : objectList) {
            contents = contents + getObjectAsString(obj);
        }

        return contents;
    }

    private static String getSerializableObjectListAsString(List<Serializable> objectList) {

        String contents = "";

        for (Object obj : objectList) {
            contents = contents + getObjectAsString(obj);
        }

        return contents;
    }


    public static String getObjectAsString(Object obj) {

        String contents = "";

        if (obj instanceof JAXBElement) {
            contents = contents + AppdataParser.getJAXBElementAsString((JAXBElement) obj);
        } else if (obj instanceof Ul) {
            contents = contents + AppdataParser.getUlAsString((Ul) obj);
        } else if (obj instanceof Ol) {
            contents = contents + AppdataParser.getOlAsString((Ol) obj);
        }


        return contents;
    }


    public static String getDescriptionAsString(Description description) {

        return getObjectListAsString(description.getContent());

    }

    public static String getJAXBElementAsString(JAXBElement element) {
        String contents = "<" + element.getName() + ">";
        contents = contents + element.getValue();
        contents = contents + "</" + element.getName() + ">";
        return contents;
    }

    public static String getUlAsString(Ul element) {
        return "<ul>" + getSerializableObjectListAsString(element.getContent()) + "</ul>";
    }


    public static String getOlAsString(Ol element) {

        return "<ol>" + getSerializableObjectListAsString(element.getContent()) + "</ol>";
    }


}
