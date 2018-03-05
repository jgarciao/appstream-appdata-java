package org.freedesktop.appstream;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBElement;
import org.freedesktop.appstream.appdata.Component;
import org.freedesktop.appstream.appdata.Description;
import org.freedesktop.appstream.appdata.Icon;
import org.freedesktop.appstream.appdata.Name;
import org.freedesktop.appstream.appdata.Ol;
import org.freedesktop.appstream.appdata.Summary;
import org.freedesktop.appstream.appdata.Ul;
import org.freedesktop.appstream.appdata.Url;

/**
 * Created by jorge on 01/05/17.
 */
public class AppdataComponent extends Component {

  private static final String ICON_TYPE_CACHED = "cached";
  private static final String ICON_TYPE_REMOTE = "remote";

  private static final String URL_TYPE_HOMEPAGE = "homepage";
  private static final String URL_TYPE_BUGTRACKER = "bugtracker";
  private static final String URL_TYPE_FAQ = "faq";
  private static final String URL_TYPE_HELP = "help";
  private static final String URL_TYPE_DONATION = "donation";
  private static final String URL_TYPE_TRANSLATE = "translate";

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
      if (name.getLang() == null) {
        return name.getValue();
      }
    }

    return "";
  }

  public String findNameByLang(String lang) {

    for (Name name : this.getName()) {
      if (lang.equalsIgnoreCase(name.getLang())) {
        return name.getValue();
      }
    }

    return "";
  }

  public String findDefaultSummary() {

    for (Summary summary : this.getSummary()) {
      if (summary.getLang() == null) {
        return summary.getValue();
      }
    }

    return "";

  }

  public String findSummaryByLang(String lang) {

    for (Summary summary : this.getSummary()) {
      if (lang.equalsIgnoreCase(summary.getLang())) {
        return summary.getValue();
      }
    }

    return "";
  }

  public String findDefaultDescription() {

    for (Description description : this.getDescription()) {
      if (description.getLang() == null) {
        return getObjectListAsString(description.getContent());
      }
    }

    return "";

  }

  public String findDescriptionByLang(String lang) {

    for (Description description : this.getDescription()) {
      if (lang.equalsIgnoreCase(description.getLang())) {
        return getObjectListAsString(description.getContent());
      }
    }

    return "";

  }

  public Icon findIconByHeight(String height) {

    for (Icon icon : this.getIcon()) {
      if (height.equalsIgnoreCase(icon.getHeight())) {
        return icon;
      }
    }
    return null;
  }

  public Icon findIconWhereTypeIsRemote() {

    for (Icon icon : this.getIcon()) {
      if (ICON_TYPE_REMOTE.equalsIgnoreCase(icon.getType())) {
        return icon;
      }
    }
    return null;
  }

  public String findIconUrl(String iconBaseRelativePath, String height) {

    String url = "";

    Icon icon = this.findIconByHeight(height);
    if (icon != null && icon.getValue() != null) {

      if (ICON_TYPE_CACHED.equalsIgnoreCase(icon.getType())) {

        url = iconBaseRelativePath + height + "x" + height + "/" + icon.getValue();
      } else if (ICON_TYPE_REMOTE.equalsIgnoreCase(icon.getType())) {
        url = icon.getValue();
      }
    } else {
      icon = this.findIconWhereTypeIsRemote();
      if (icon != null && icon.getValue() != null) {
        url = icon.getValue();
      }
    }

    return url;
  }


  private Optional<String> findUrlByType(String expectedUrlType){

    for (Url url : this.getUrl()) {
      if (expectedUrlType.equalsIgnoreCase(url.getType())) {
        return Optional.of(url.getValue());
      }
    }
    return Optional.empty();

  }

  public Optional<String> findHomepageUrl() {
    return findUrlByType(URL_TYPE_HOMEPAGE);
  }

  public Optional<String> findBugtrackerUrl() {
    return findUrlByType(URL_TYPE_BUGTRACKER);
  }

  public Optional<String> findFaqUrl() {
    return findUrlByType(URL_TYPE_FAQ);
  }

  public Optional<String> findHelpUrl() {
    return findUrlByType(URL_TYPE_HELP);
  }

  public Optional<String> findDonationUrl() {
    return findUrlByType(URL_TYPE_DONATION);
  }

  public Optional<String> findTranslateUrl() {
    return findUrlByType(URL_TYPE_TRANSLATE);
  }

}