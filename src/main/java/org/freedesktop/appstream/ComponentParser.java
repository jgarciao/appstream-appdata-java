package org.freedesktop.appstream;

import org.freedesktop.appstream.appdata.*;

import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jorge on 21/04/17.
 */
public class ComponentParser {


    public static String getId(Component component) {

        return component.getId();
    }

    public static String getFlatpakId(Component component) {

        //TODO: check component.getBundle().getType.equals("flatpak")

        String[] idArray;
        String flatpakID = "";

        if (component.getBundle() != null && component.getBundle().getValue() != null) {
            idArray = component.getBundle().getValue().split("/");

            if (idArray.length == 4 && idArray[1] != null) {
                flatpakID = idArray[1];
            }
        }

        return flatpakID;
    }

    public static String getFlatpakRuntime(Component component) {
        return component.getBundle().getRuntime();
    }

    public static String getFlatpakSdk(Component component) {
        return component.getBundle().getSdk();
    }

    public static String getName(Component component) {

        for (Name name : component.getName()) {
            if (name.getLang() == null) return name.getValue();
        }

        return "";
    }

    public static String getName(Component component, String lang) {

        for (Name name : component.getName()) {
            if (lang.equalsIgnoreCase(name.getLang())) return name.getValue();
        }

        return "";
    }

    public static String getSummary(Component component) {

        for (Summary summary : component.getSummary()) {
            if (summary.getLang() == null) return summary.getValue();
        }

        return "";

    }

    public static String getSummary(Component component, String lang) {

        for (Summary summary : component.getSummary()) {
            if (lang.equalsIgnoreCase(summary.getLang())) return summary.getValue();
        }

        return "";
    }

    public static String getDescription(Component component) {

        for (Description description : component.getDescription()) {
            if (description.getLang() == null) return getObjectListAsString(description.getContent());
        }

        return "";

    }

    public static String getDescription(Component component, String lang) {

        for (Description description : component.getDescription()) {
            if (lang.equalsIgnoreCase(description.getLang())) return getObjectListAsString(description.getContent());
        }

        return "";

    }


    private static String getObjectListAsString(List<Object> objectList) {

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


    private static String getObjectAsString(Object obj) {

        String contents = "";

        if (obj instanceof JAXBElement) {
            contents = contents + ComponentParser.getJAXBElementAsString((JAXBElement) obj);
        } else if (obj instanceof Ul) {
            contents = contents + ComponentParser.getUlAsString((Ul) obj);
        } else if (obj instanceof Ol) {
            contents = contents + ComponentParser.getOlAsString((Ol) obj);
        }


        return contents;
    }


    private static String getJAXBElementAsString(JAXBElement element) {
        String contents = "<" + element.getName() + ">";
        contents = contents + element.getValue();
        contents = contents + "</" + element.getName() + ">";
        return contents;
    }

    private static String getUlAsString(Ul element) {
        return "<ul>" + getSerializableObjectListAsString(element.getContent()) + "</ul>";
    }


    private static String getOlAsString(Ol element) {

        return "<ol>" + getSerializableObjectListAsString(element.getContent()) + "</ol>";
    }

}
