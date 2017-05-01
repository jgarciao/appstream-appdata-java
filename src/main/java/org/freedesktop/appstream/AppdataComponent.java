package org.freedesktop.appstream;

import org.freedesktop.appstream.appdata.*;

import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by jorge on 01/05/17.
 */
public class AppdataComponent extends Component {

    public AppdataComponent() {
        super();
    }

    public AppdataComponent(Component parentComponent) {
        super();
        this.id = parentComponent.getId();
        this.translation = parentComponent.getTranslation();
        this.name = parentComponent.getName();
        this.summary = parentComponent.getSummary();
        this.description = parentComponent.getDescription();
        this.icon = parentComponent.getIcon();
        this.categories = parentComponent.getCategories();
        this.keywords = parentComponent.getKeywords();
        this.kudos = parentComponent.getKudos();
        this.projectLicense = parentComponent.getProjectLicense();
        this.url = parentComponent.getUrl();
        this.projectGroup = parentComponent.getProjectGroup();
        this.compulsoryForDesktop = parentComponent.getCompulsoryForDesktop();
        this.screenshots = parentComponent.getScreenshots();
        this.languages = parentComponent.getLanguages();
        this.bundle = parentComponent.getBundle();
        this.type = parentComponent.getType();
    }

    public String getFlatpakId() {

        //TODO: check component.getBundle().getType.equals("flatpak")

        String[] idArray;
        String flatpakID = "";

        if (this.getBundle() != null && this.getBundle().getValue() != null) {
            idArray = this.getBundle().getValue().split("/");

            if (idArray.length == 4 && idArray[1] != null) {
                flatpakID = idArray[1];
            }
        }

        return flatpakID;
    }

    public String getFlatpakRuntime() {

        return this.getBundle().getRuntime();
    }

    public String getFlatpakSdk() {

        return this.getBundle().getSdk();
    }


    public String findDefaultName() {

        for (Name name : this.getName()) {
            if (name.getLang() == null) return name.getValue();
        }

        return "";
    }

    public String findNameByLang(String lang) {

        for (Name name : this.getName()) {
            if (lang.equalsIgnoreCase(name.getLang())) return name.getValue();
        }

        return "";
    }

    public String findDefaultSummary() {

        for (Summary summary : this.getSummary()) {
            if (summary.getLang() == null) return summary.getValue();
        }

        return "";

    }

    public String findSummaryByLang(String lang) {

        for (Summary summary : this.getSummary()) {
            if (lang.equalsIgnoreCase(summary.getLang())) return summary.getValue();
        }

        return "";
    }

    public String findDefaultDescription() {

        for (Description description : this.getDescription()) {
            if (description.getLang() == null) return getObjectListAsString(description.getContent());
        }

        return "";

    }

    public String findDescriptionByLang(String lang) {

        for (Description description : this.getDescription()) {
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
            contents = contents + AppdataComponent.getJAXBElementAsString((JAXBElement) obj);
        } else if (obj instanceof Ul) {
            contents = contents + AppdataComponent.getUlAsString((Ul) obj);
        } else if (obj instanceof Ol) {
            contents = contents + AppdataComponent.getOlAsString((Ol) obj);
        }


        return contents;
    }


    private static String getJAXBElementAsString(JAXBElement element) {
        String contents = "<" + element.getName() + ">";
        contents = contents + element.getValue();
        contents = contents + "</" + element.getName() + ">" + "\n";
        return contents;
    }

    private static String getUlAsString(Ul element) {
        return "<ul>" + "\n" + getSerializableObjectListAsString(element.getContent()) + "</ul>" + "\n";
    }


    private static String getOlAsString(Ol element) {

        return "<ol>" + "\n" + getSerializableObjectListAsString(element.getContent()) + "</ol>" + "\n";
    }


}
