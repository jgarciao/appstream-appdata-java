package org.freedesktop.appstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.xml.bind.JAXBElement;
import org.freedesktop.appstream.appdata.Bundle;
import org.freedesktop.appstream.appdata.Categories;
import org.freedesktop.appstream.appdata.Component;
import org.freedesktop.appstream.appdata.ContentRating;
import org.freedesktop.appstream.appdata.Description;
import org.freedesktop.appstream.appdata.Icon;
import org.freedesktop.appstream.appdata.Keywords;
import org.freedesktop.appstream.appdata.Kudos;
import org.freedesktop.appstream.appdata.Languages;
import org.freedesktop.appstream.appdata.Metadata;
import org.freedesktop.appstream.appdata.Mimetypes;
import org.freedesktop.appstream.appdata.Name;
import org.freedesktop.appstream.appdata.Ol;
import org.freedesktop.appstream.appdata.Provides;
import org.freedesktop.appstream.appdata.Release;
import org.freedesktop.appstream.appdata.Screenshot;
import org.freedesktop.appstream.appdata.Summary;
import org.freedesktop.appstream.appdata.Translation;
import org.freedesktop.appstream.appdata.Ul;
import org.freedesktop.appstream.appdata.Url;

/**
 * Created by jorge on 01/05/17.
 */
public class AppdataComponent {

  public static final String ICON_TYPE_CACHED = "cached";
  public static final String ICON_TYPE_REMOTE = "remote";

  public static final String URL_TYPE_HOMEPAGE = "homepage";
  public static final String URL_TYPE_BUGTRACKER = "bugtracker";
  public static final String URL_TYPE_FAQ = "faq";
  public static final String URL_TYPE_HELP = "help";
  public static final String URL_TYPE_DONATION = "donation";
  public static final String URL_TYPE_TRANSLATE = "translate";
  public static final String SCREENSHOT_IMAGE_TYPE_THUMBNAIL = "thumbnail";

  private Component parentComponent = new Component();
  protected List<ScreenshotInfo> screenshotsInfo;


  public String getId() {
    return this.parentComponent.getId();
  }

  public void setId(String id) {
    this.parentComponent.setId(id);
  }

  public Translation getTranslation() {
    return this.parentComponent.getTranslation();
  }

  public void setTranslation(Translation translation) {
    this.parentComponent.setTranslation(translation);
  }

  public List<Name> getName() {
    return this.parentComponent.getName();
  }


  public List<Summary> getSummary() {
    return this.parentComponent.getSummary();
  }

  public String getDeveloperName() {
    return this.parentComponent.getDeveloperName();
  }

  public void setDeveloperName(String developerName) {
    this.parentComponent.setDeveloperName(developerName);
  }

  public List<Description> getDescription() {
    return this.parentComponent.getDescription();
  }


  public List<Icon> getIcon() {
    return this.parentComponent.getIcon();
  }


  public Categories getCategories() {
    return this.parentComponent.getCategories();
  }

  public void setCategories(Categories categories) {
    this.parentComponent.setCategories(categories);
  }

  public Keywords getKeywords() {
    return this.parentComponent.getKeywords();
  }

  public void setKeywords(Keywords keywords) {
    this.parentComponent.setKeywords(keywords);
  }

  public Kudos getKudos() {
    return this.parentComponent.getKudos();
  }

  public void setKudos(Kudos kudos) {
    this.parentComponent.setKudos(kudos);
  }

  public Mimetypes getMimetypes() {
    return this.parentComponent.getMimetypes();
  }

  public void setMimetypes(Mimetypes mimetypes) {
    this.parentComponent.setMimetypes(mimetypes);
  }

  public String getProjectLicense() {
    return this.parentComponent.getProjectLicense();
  }

  public void setProjectLicense(String projectLicense) {
    this.parentComponent.setProjectLicense(projectLicense);
  }

  public List<Url> getUrl() {
    return this.parentComponent.getUrl();
  }

  public String getProjectGroup() {
    return this.parentComponent.getProjectGroup();
  }

  public void setProjectGroup(String projectGroup) {
    this.parentComponent.setProjectGroup(projectGroup);
  }

  public List<ContentRating> getContentRating() {
    return this.parentComponent.getContentRating();
  }

  public Languages getLanguages() {
    return this.parentComponent.getLanguages();
  }

  public void setLanguages(Languages languages) {
    this.parentComponent.setLanguages(languages);
  }

  public Provides getProvides() {
    return this.parentComponent.getProvides();
  }

  public void setProvides(Provides provides) {
    this.parentComponent.setProvides(provides);
  }

  public Bundle getBundle() {
    return this.parentComponent.getBundle();
  }

  public void setBundle(Bundle bundle) {
    this.parentComponent.setBundle(bundle);
  }

  public Metadata getMetadata() {
    return this.parentComponent.getMetadata();
  }

  public void setMetadata(Metadata metadata) {
    this.parentComponent.setMetadata(metadata);
  }

  public String getType() {
    return this.parentComponent.getType();
  }

  public void setType(String type) {
    this.parentComponent.setType(type);
  }

  public AppdataComponent() {}


  public AppdataComponent(Component parentComponent) {
    
    this.parentComponent = parentComponent;

    this.screenshotsInfo = new ArrayList<>();
    if(parentComponent.getScreenshots() != null){
      for (Screenshot screenshot: parentComponent.getScreenshots().getScreenshot()) {
        this.screenshotsInfo.add(new ScreenshotInfo(screenshot));
      }
    }

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

    if (element.getValue() instanceof String) {
      contents = contents + element.getValue();
    } else if (element.getValue() instanceof Description) {
      contents = contents + getObjectListAsString(((Description) element.getValue()).getContent());
    }
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

  public List<ScreenshotInfo> getScreenshots(){
    return this.screenshotsInfo;
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

  public Icon findIconByHeight(short height) {

    for (Icon icon : this.getIcon()) {
      if (icon.getHeight() != null && height == icon.getHeight()) {
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




  public String findIconUrl(String iconBaseRelativePath, short height) {

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


  private Optional<String> findUrlByType(String expectedUrlType) {

    for (Url url : this.getUrl()) {
      if (expectedUrlType.equalsIgnoreCase(url.getType())) {
        return Optional.of(url.getValue());
      }
    }
    return Optional.empty();

  }


  public Optional<ReleaseInfo> findReleaseInfoByMostRecent() {

    if (this.parentComponent.getReleases() == null || this.parentComponent.getReleases().getRelease() == null
      || this.parentComponent.getReleases().getRelease().size() == 0) {
      return Optional.empty();
    }

    List<Release> sortedReleases = this.parentComponent.getReleases().getRelease();
    sortedReleases.sort(Comparator.comparing(Release::getTimestamp).reversed());

    Release release = sortedReleases.get(0);
    String description = getSerializableObjectListAsString(release.getContent());
    description = description.replace("<description>", "");
    description = description.replace("</description>", "");

    return Optional.of(new ReleaseInfo(release.getVersion(), release.getTimestamp(), description));

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