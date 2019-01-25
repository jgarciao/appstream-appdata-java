package org.freedesktop.appstream;

import java.io.Serializable;
import java.security.InvalidParameterException;
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
import org.freedesktop.appstream.appdata.DeveloperName;
import org.freedesktop.appstream.appdata.Icon;
import org.freedesktop.appstream.appdata.Image;
import org.freedesktop.appstream.appdata.Keywords;
import org.freedesktop.appstream.appdata.Kudos;
import org.freedesktop.appstream.appdata.Languages;
import org.freedesktop.appstream.appdata.Metadata;
import org.freedesktop.appstream.appdata.Mimetypes;
import org.freedesktop.appstream.appdata.Name;
import org.freedesktop.appstream.appdata.Ol;
import org.freedesktop.appstream.appdata.Provides;
import org.freedesktop.appstream.appdata.Release;
import org.freedesktop.appstream.appdata.Releases;
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

  public List<DeveloperName> getDeveloperName() {
    return this.parentComponent.getDeveloperName();
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

  public ContentRating getContentRating() {
    return this.parentComponent.getContentRating();
  }

  public void setContentRating(ContentRating contentRating){
    this.parentComponent.setContentRating(contentRating);
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

  Releases getReleases(){
    return this.parentComponent.getReleases();
  }

  void setReleases(Releases releases){
    this.parentComponent.setReleases(releases);
  }

  public AppdataComponent() {}

  public AppdataComponent(Component parentComponent) {
    
    this.parentComponent = parentComponent;

  }

  public void merge(AppdataComponent anotherComponent){

    if(anotherComponent.getFlatpakId() == null || !anotherComponent.getFlatpakId().equalsIgnoreCase(this.getFlatpakId())){
      throw new InvalidParameterException("FlatpakId must be the same to merge");
    }

    if(this.getTranslation() == null && anotherComponent.getTranslation() != null){
      this.setTranslation(anotherComponent.getTranslation());
    }

    if(this.getName().size() < anotherComponent.getName().size()){
      this.getName().clear();
      this.getName().addAll(anotherComponent.getName());
    }

    if (this.getSummary().size() < anotherComponent.getSummary().size()){
      this.getSummary().clear();
      this.getSummary().addAll(anotherComponent.getSummary());
    }

    if (this.getDeveloperName().size() < anotherComponent.getDeveloperName().size()){
      this.getDeveloperName().clear();
      this.getDeveloperName().addAll(anotherComponent.getDeveloperName());
    }

    if(this.getDescription().size() < anotherComponent.getDescription().size()){
      this.getDescription().clear();
      this.getDescription().addAll(anotherComponent.getDescription());
    }

    if(this.getIcon().size() < anotherComponent.getIcon().size()){
      this.getIcon().clear();
      this.getIcon().addAll(anotherComponent.getIcon());
    }

    if(this.getCategories() == null && anotherComponent.getCategories() != null){
      this.setCategories(anotherComponent.getCategories());
    }

    if(this.getKeywords() == null && anotherComponent.getKeywords() != null){
      this.setKeywords(anotherComponent.getKeywords());
    }

    if(this.getKudos() == null && anotherComponent.getKudos() != null){
      this.setKudos(anotherComponent.getKudos());
    }

    if(this.getMimetypes() == null && anotherComponent.getMimetypes() != null){
      this.setMimetypes(anotherComponent.getMimetypes());
    }

    if(this.getProjectLicense() == null && anotherComponent.getProjectLicense() != null){
      this.setProjectLicense(anotherComponent.getProjectLicense());
    }

    if(this.getUrl().size() < anotherComponent.getUrl().size()){
      this.getUrl().clear();
      this.getUrl().addAll(anotherComponent.getUrl());
    }

    if(this.getProjectGroup() == null && anotherComponent.getProjectGroup() != null){
      this.setProjectGroup(anotherComponent.getProjectGroup());
    }

    if(this.getScreenshotsByLangDefault().size() < anotherComponent.getScreenshotsByLangDefault().size()){
      this.getScreenshotsByLangDefault().clear();
      this.getScreenshotsByLangDefault().addAll(anotherComponent.getScreenshotsByLangDefault());
    }

    if(this.getContentRating() == null && anotherComponent.getContentRating() != null){
      this.setContentRating(anotherComponent.getContentRating());
    }

    if(this.getReleases() == null  && anotherComponent.getReleases() != null){
      this.setReleases(anotherComponent.getReleases());
    }

    if(this.getLanguages() == null && anotherComponent.getLanguages() != null){
      this.setLanguages(anotherComponent.getLanguages());
    }

    if(this.getProvides() == null && anotherComponent.getProvides() != null){
      this.setProvides(anotherComponent.getProvides());
    }

    if(this.getBundle() == null && anotherComponent.getBundle() != null){
      this.setBundle(anotherComponent.getBundle());
    }

    if(this.getMetadata() == null && anotherComponent.getMetadata() != null){
      this.setMetadata(anotherComponent.getMetadata());
    }

    if(this.getType() == null && anotherComponent.getType() != null){
      this.setType(anotherComponent.getType());
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
    return "<ol>" + "\n" + getListAsStringUsingTag(element.getLi(), "li") + "</ol>" + "\n";
  }


  private static String getListAsStringUsingTag(List<String> list, String tag) {

    String contents = "";

    for (String value : list) {
      contents = contents + "<" + tag + ">\n" + value  + "</" + tag + ">\n";
    }

    return contents;
  }

  public String getFlatpakId() {

    //TODO: check component.getBundle().getType.equals("flatpak")

    String[] idArray;
    String flatpakID = "";

    if (this.getBundle() != null && this.getBundle().getValue() != null) {
      idArray = this.getBundle().getValue().split("/");

      if (idArray.length == 4 && idArray[1] != null) {
        flatpakID = idArray[1].trim();
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

  public List<ScreenshotInfo> getScreenshotsByLangDefault(){

    if(this.screenshotsInfo == null){

      this.screenshotsInfo = new ArrayList<>();

      if(parentComponent.getScreenshots() != null){

        for (Screenshot screenshot: parentComponent.getScreenshots().getScreenshot()) {

          for(Image image : screenshot.getImage())

            if(image.getLang() == null){
              this.screenshotsInfo.add(new ScreenshotInfo(screenshot));
              break;
            }

        }
      }
    }



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

  public String findDefaultDeveloperName() {

    for (DeveloperName developerName : this.getDeveloperName()) {
      if (developerName.getLang() == null) {
        return developerName.getValue();
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

  public Optional<ReleaseInfo> findReleaseInfoByMostRecentAndLangIsDefault() {

    if (this.parentComponent.getReleases() == null || this.parentComponent.getReleases().getRelease() == null
      || this.parentComponent.getReleases().getRelease().size() == 0) {
      return Optional.empty();
    }

    List<Release> sortedReleases = this.parentComponent.getReleases().getRelease();
    sortedReleases.sort(Comparator.comparing(Release::getTimestamp).reversed());

    Release release = sortedReleases.get(0);

    if(release.getVersion() != null && release.getTimestamp() != null){

      String strDescription = "";

      for (Description description : release.getDescription()) {
        if (description.getLang() == null) {
          strDescription = getObjectListAsString(description.getContent());
          break;
        }
      }

      strDescription = strDescription.replace("<description>", "");
      strDescription = strDescription.replace("</description>", "");

      Optional.of(new ReleaseInfo(release.getVersion(), release.getTimestamp(), strDescription));

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