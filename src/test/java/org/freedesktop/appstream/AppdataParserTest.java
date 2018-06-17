package org.freedesktop.appstream;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.List;
import org.junit.Test;

public class AppdataParserTest {


  final String APPSTREAM_TYPE_DESKTOP = "desktop";
  final String APPSTREAM_TYPE_RUNTIME = "runtime";
  final String ICON_BASE_RELATIVE_PATH = "/repo/appstream/x86_64/icons/";
  final String SCREENSHOT_TYPE_DEFAULT = "default";
  final String IMAGE_TYPE_THUMBNAIL = "thumbnail";
  final String IMAGE_TYPE_SOURCE = "source";

  final ClassLoader classLoader = getClass().getClassLoader();


  private void printAppdata(AppdataComponent component) {
    printAppdata(component, null);
  }

  private void printAppdata(AppdataComponent component, String lang) {

    System.out.println("Id:             " + component.getId());
    System.out.println("FlatpakId:      " + component.getFlatpakId());
    System.out.println("FlatpakRuntime: " + component.getFlatpakRuntime());

    if (lang != null && !"".equalsIgnoreCase(lang)) {

      System.out.println("Name:           " + component.findNameByLang(lang));
      System.out.println("Summary:        " + component.findSummaryByLang(lang));
      System.out.println("Description:\n" + component.findDescriptionByLang(lang));

    } else {
      System.out.println("Name:           " + component.findDefaultName());
      System.out.println("Summary:        " + component.findDefaultSummary());
      System.out.println("Description:\n" + component.findDefaultDescription());
    }

    //TODO:
    //Categories
    //Icons
    //Keywords (translatable)
    //kudos
    //Screenshots
    //Languages (percentage)
    //<bundle type="flatpak" runtime="org.gnome.Platform/x86_64/3.22" sdk="org.gnome.Sdk/x86_64/3.22">app/org.gnome.Weather/x86_64/stable</bundle>

  }


  @Test
  public void when_ParsingKde_Expect_ComponentInfoObtained() throws Exception {

    final int EXPECTED_COMPONENT_COUNT = 69;
    final int EXPECTED_APP_COUNT = 69;
    final int EXPECTED_RUNTIME_COUNT = 0;

    //Given
    List<AppdataComponent> componentList = null;
    int appCount = 0;
    int runtimeCount = 0;

    //When
    File file = new File(classLoader.getResource("appstream-kdeapps.xml").getFile());
    componentList = AppdataParser.parseAppdataFile(file);

    for (AppdataComponent component : componentList) {
      if (APPSTREAM_TYPE_DESKTOP.equalsIgnoreCase(component.getType())) {
        appCount++;
      } else {
        runtimeCount++;
      }
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(EXPECTED_COMPONENT_COUNT);
    assertThat(appCount).isEqualTo(EXPECTED_APP_COUNT);
    assertThat(runtimeCount).isEqualTo(EXPECTED_RUNTIME_COUNT);

  }


  @Test
  public void when_ParsingEndlessOS_Expect_ComponentInfoObtained() throws Exception {

    final int EXPECTED_COMPONENT_COUNT = 631;
    final int EXPECTED_APP_COUNT = 631;
    final int EXPECTED_RUNTIME_COUNT = 0;

    //Given
    List<AppdataComponent> componentList = null;
    int appCount = 0;
    int runtimeCount = 0;

    //When
    File file = new File(classLoader.getResource("appstream-eos-apps.xml").getFile());
    componentList = AppdataParser.parseAppdataFile(file);

    for (AppdataComponent component : componentList) {
      if (APPSTREAM_TYPE_DESKTOP.equalsIgnoreCase(component.getType())) {
        appCount++;
      } else {
        runtimeCount++;
      }
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(EXPECTED_COMPONENT_COUNT);
    assertThat(appCount).isEqualTo(EXPECTED_APP_COUNT);
    assertThat(runtimeCount).isEqualTo(EXPECTED_RUNTIME_COUNT);

  }

  @Test
  public void when_ParsingGnome_Expect_ComponentInfoObtained() throws Exception {

    final int EXPECTED_COMPONENT_COUNT = 24;
    final int EXPECTED_APP_COUNT = 24;
    final int EXPECTED_RUNTIME_COUNT = 0;

    //Given
    List<AppdataComponent> componentList = null;
    int appCount = 0;
    int runtimeCount = 0;

    //When
    File file = new File(classLoader.getResource("appstream-gnome-apps.xml").getFile());
    componentList = AppdataParser.parseAppdataFile(file);

    for (AppdataComponent component : componentList) {
      if (APPSTREAM_TYPE_DESKTOP.equalsIgnoreCase(component.getType())) {
        appCount++;
      } else {
        runtimeCount++;
      }
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(EXPECTED_COMPONENT_COUNT);
    assertThat(appCount).isEqualTo(EXPECTED_APP_COUNT);
    assertThat(runtimeCount).isEqualTo(EXPECTED_RUNTIME_COUNT);

  }


  @Test
  public void when_ParsingFlathub_Expect_ComponentInfoObtained() throws Exception {

    final int EXPECTED_COMPONENT_COUNT = 252;
    final int EXPECTED_APP_COUNT = 209;
    final int EXPECTED_RUNTIME_COUNT = 43;

    //Given
    List<AppdataComponent> componentList = null;
    int appCount = 0;
    int runtimeCount = 0;

    //When
    File file = new File(classLoader.getResource("appstream-flathub-x86_64-201803.xml").getFile());
    componentList = AppdataParser.parseAppdataFile(file);

    for (AppdataComponent component : componentList) {
      if (APPSTREAM_TYPE_DESKTOP.equalsIgnoreCase(component.getType())) {
        appCount++;
      } else {
        runtimeCount++;
      }
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(EXPECTED_COMPONENT_COUNT);
    assertThat(appCount).isEqualTo(EXPECTED_APP_COUNT);
    assertThat(runtimeCount).isEqualTo(EXPECTED_RUNTIME_COUNT);

  }


  @Test
  public void when_ParsingBuilder_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-builder.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.gnome.Builder";
    String EXPECTED_RUNTIME = "org.gnome.Sdk/x86_64/3.28";
    String EXPECTED_DEFAULT_NAME = "Builder";
    String EXPECTED_DEFAULT_SUMMARY = "An IDE for GNOME";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>Builder is an actively developed Integrated Development Environment for GNOME. It combines integrated support for essential GNOME technologies such as GTK+, GLib, and GNOME APIs with features that any developer will appreciate, like syntax highlighting and snippets.</p>\n"
        + "<p>You can rely on predictable releases of Builder with each new release of GNOME every six months.</p>\n"
        + "<p>Features:</p>\n"
        + "<ul>\n"
        + "<li>Built in syntax highlighting for many languages</li>\n"
        + "<li>Side-by-side code editors</li>\n"
        + "<li>Integration with Git</li>\n"
        + "<li>Integration with Autotools, Cargo, CMake, Meson, Make, and PHPize</li>\n"
        + "<li>Clang based auto-completion, semantic highlighting, and diagnostics</li>\n"
        + "<li>Python based auto-completion, semantic highlighting, and diagnostics</li>\n"
        + "<li>Vala based auto-completion and diagnostics</li>\n"
        + "<li>Auto indentation support for C, Python, Vala, and XML</li>\n"
        + "<li>HTML, Markdown, and reStructuredText, and Sphinx live preview</li>\n"
        + "<li>Optional Vim-style editing</li>\n"
        + "<li>An integrated software profiler for native applications</li>\n"
        + "<li>An integrated debugger for native applications</li>\n"
        + "<li>Support for building with jhbuild and flatpak runtimes</li>\n"
        + "</ul>\n";

    String EXPECTED_HOMEPAGE_URL = "https://wiki.gnome.org/Apps/Builder";
    String EXPECTED_DONATION_URL = "http://www.gnome.org/friends/";
    String EXPECTED_BUGTRACKER_URL = "https://gitlab.gnome.org/GNOME/gnome-builder/issues";

    String EXPECTED_PROJECT_LICENSE = "GPL-3.0+ and GPL-2.0+ and LGPL-3.0+ and LGPL-2.0+ and MIT";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.gnome.Builder.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.gnome.Builder.png";
    int EXPECTED_SCREENSHOT_COUNT = 6;
    short EXPECTED_SCHEENSHOT0_HEIGHT = 846;
    String EXPECTED_SCREENSHOT0_URL = "https://flathub.org/repo/screenshots/org.gnome.Builder-stable/1504x846/org.gnome.Builder-3582853b1ad4a82da236a964983cef7c.png";
    short EXPECTED_SCHEENSHOT1_HEIGHT = 351;
    String EXPECTED_SCREENSHOT1_URL = "https://flathub.org/repo/screenshots/org.gnome.Builder-stable/624x351/org.gnome.Builder-19b4818c4fda40f94d8e6ccc1379dc6d.png";

    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "Development";

    String EXPECTED_RELEASE_VERSION = "3.28.2";
    String EXPECTED_RELEASE_DESCRIPTION =
      "<p>Builder 3.28.2 contains a number of important bugfixes and translation updates.</p>\n"
        + "<ul>\n"
        + "<li>A leak of buffers when using semantic highlighting as been fixed, which has the potential to significantly reduce memory usage for some languages such as C.</li>\n"
        + "<li>Various fixes with DTD validation.</li>\n"
        + "<li>Various correctness fixes in API usage.</li>\n"
        + "<li>The new task and threading engine has been backported which provides a new layer of saftey for our threading usage.</li>\n"
        + "<li>Various fixes for the flatpak plugin.</li>\n"
        + "<li>Ctags tries to be more careful to avoid recursive mining and no longer attempts to load ~/.tags.</li>\n"
        + "<li>The clang plugin tries harder to cook cflags delivered to libclang.</li>\n"
        + "<li>A preferences menu item was added back to the perspective selector. It still uses a dialog for preferences.</li>\n"
        + "<li>The recent-projects plugin performs additional checks to avoid recursive directory cleanup when that would result in deleting some external data.</li>\n"
        + "<li>A number of miscellaneous memory leaks have been plugged.</li>\n"
        + "<li>When meson/ninja are not found, a message is displayed to the user in the messages pane.</li>\n"
        + "<li>The clang plugin will try to drop state as soon as possible to help keep memory usage down.</li>\n"
        + "<li>The npm modules now marks \"node_modules\" as an ignored pattern.</li>\n"
        + "</ul>\n"
        + "\n";
    int EXPECTED_RELEASE_TIMESTAMP = 1527465600;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);
    assertThat(component.findDonationUrl().get()).isEqualToIgnoringCase(EXPECTED_DONATION_URL);
    assertThat(component.findBugtrackerUrl().get()).isEqualToIgnoringCase(EXPECTED_BUGTRACKER_URL);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

    assertThat(component.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
    assertThat(component.findReleaseInfoByMostRecent().get().getDescription())
      .isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

  }


  @Test
  public void when_ParsingBuilderTranslations_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-builder.xml";
    String LANG = "pt";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_DEFAULT_NAME = "Construtor";
    String EXPECTED_DEFAULT_SUMMARY = "Um IDE para o GNOME";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>Construtor é um Ambiente de Desenvolvimento Integrado (IDE) em desenvolvimentoativo. Combina suporte integrado para tecnologias GNOME essenciais tal como o GTK+, GLib, e as GNOME APIs com características que qualquer programador irá apreciar, como snippets e realce de sintaxe.</p>\n"
        + "<p>Pode contar com novos lançamentos do Construtor a cada novo lançamento do GNOME, cada seis meses.</p>\n"
        + "<p>Características:</p>\n"
        + "<ul>\n"
        + "<li>Crie com realce de sintaxe para muitas linguagens</li>\n"
        + "<li>Editores de código lado-a-lado</li>\n"
        + "<li>Integração com Git</li>\n"
        + "<li>Conclusão automática baseada em Clang, realce semântico, e diagnósticos</li>\n"
        + "<li>Conclusão automática baseada em Python, realce semântico, e diagnósticos</li>\n"
        + "<li>Conclusão automática baseada em Vala e diagnósticos</li>\n"
        + "<li>Suporte de auto indentação para C, Python, Vala e XML</li>\n"
        + "<li>Edição estilo-Vim opcional</li>\n"
        + "<li>Um perfil integrado de programas para programas nativos</li>\n"
        + "</ul>\n";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(component.findNameByLang(LANG)).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findSummaryByLang(LANG)).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDescriptionByLang(LANG))
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);

  }


  @Test
  public void when_ParsingGIMP_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-gimp.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.gimp.GIMP";
    String EXPECTED_RUNTIME = "org.gnome.Platform/x86_64/3.28";
    String EXPECTED_DEFAULT_NAME = "GNU Image Manipulation Program";
    String EXPECTED_DEFAULT_SUMMARY = "Create images and edit photographs";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>GIMP is an acronym for GNU Image Manipulation Program. It is a freely distributed program for such tasks as photo retouching, image composition and image authoring.</p>\n"
        + "<p>It has many capabilities. It can be used as a simple paint program, an expert quality photo retouching program, an online batch processing system, a mass production image renderer, an image format converter, etc.</p>\n"
        + "<p>GIMP is expandable and extensible. It is designed to be augmented with plug-ins and extensions to do just about anything. The advanced scripting interface allows everything from the simplest task to the most complex image manipulation procedures to be easily scripted. GIMP is available for Linux, Microsoft Windows and OS X.</p>\n";

    String EXPECTED_HOMEPAGE_URL = "https://www.gimp.org/";
    String EXPECTED_DONATION_URL = "https://www.gimp.org/donating/";
    String EXPECTED_BUGTRACKER_URL = "https://bugzilla.gnome.org/page.cgi?id=browse.html&product=GIMP";

    String EXPECTED_PROJECT_LICENSE = "GPL-3.0+ AND LGPL-3.0+";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.gimp.GIMP.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.gimp.GIMP.png";
    int EXPECTED_SCREENSHOT_COUNT = 2;
    short EXPECTED_SCHEENSHOT0_HEIGHT = 846;
    String EXPECTED_SCREENSHOT0_URL = "https://flathub.org/repo/screenshots/org.gimp.GIMP-stable/1504x846/org.gimp-9a006ea8c92d7eef681324078fe8d9ad.png";
    short EXPECTED_SCHEENSHOT1_HEIGHT = 351;
    String EXPECTED_SCREENSHOT1_URL = "https://flathub.org/repo/screenshots/org.gnome.Builder-stable/624x351/org.gnome.Builder-19b4818c4fda40f94d8e6ccc1379dc6d.png";

    int EXPECTEC_CATEGORY_COUNT = 3;
    String EXPECTED_CATEGORY_FIRST = "2DGraphics";

    String EXPECTED_RELEASE_VERSION = "2.10.2";
    String EXPECTED_RELEASE_DESCRIPTION =
      "<p>This second release in the GIMP 2.10 series, so soon after 2.10.0, is mostly the usual bug-fixing version after a major release, with a few dozen bugs fixed.</p>\n"
        +
        "<p>It also features a new plug-in for the support of the HEIF format, both for importing and exporting, as well as 2 new filters: \"Spherize\" and \"Recursive Transform\". These are nice examples of our relaxed feature policy in stable micro releases.</p>\n";

    int EXPECTED_RELEASE_TIMESTAMP = 1526774400;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);
    assertThat(component.findDonationUrl().get()).isEqualToIgnoringCase(EXPECTED_DONATION_URL);
    assertThat(component.findBugtrackerUrl().get()).isEqualToIgnoringCase(EXPECTED_BUGTRACKER_URL);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

    assertThat(component.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
//    assertThat(component.findReleaseInfoByMostRecent().get().getDescription())
      //.isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

  }


  @Test
  public void when_ParsingPitivi_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-pitivi.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.pitivi.Pitivi";
    String EXPECTED_DEVELOPER_NAME = "The Pitivi Team";

    String EXPECTED_HOMEPAGE_URL = "http://www.pitivi.org";
    String EXPECTED_DONATION_URL = "http://www.pitivi.org/?go=donators";
    String EXPECTED_HELP_URL = "http://www.pitivi.org/manual/";
    String EXPECTED_BUGTRACKER_URL = "https://phabricator.freedesktop.org/tag/pitivi/";
    String EXPECTED_TRANSLATE_URL = "https://wiki.gnome.org/TranslationProject#How_can_I_help.3F";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);

    assertThat(component.findDefaultDeveloperName()).isEqualToIgnoringCase(EXPECTED_DEVELOPER_NAME);
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);
    assertThat(component.findDonationUrl().get()).isEqualToIgnoringCase(EXPECTED_DONATION_URL);
    assertThat(component.findHelpUrl().get()).isEqualToIgnoringCase(EXPECTED_HELP_URL);
    assertThat(component.findBugtrackerUrl().get()).isEqualToIgnoringCase(EXPECTED_BUGTRACKER_URL);
    assertThat(component.findTranslateUrl().get()).isEqualToIgnoringCase(EXPECTED_TRANSLATE_URL);
  }

  @Test
  public void when_ParsingDiscord_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-discord.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "com.discordapp.Discord";
    String EXPECTED_DEVELOPER_NAME = "Discord";
    String EXPECTED_RUNTIME = "org.freedesktop.Platform/x86_64/1.6";
    String EXPECTED_DEFAULT_NAME = "Discord";
    String EXPECTED_DEFAULT_SUMMARY = "Chat client";
    String EXPECTED_DEFAULT_DESCRIPTION = "<p>All-in-one voice and text chat for gamers that's free, secure, and works on both your desktop and phone. Stop paying for TeamSpeak servers and hassling with Skype. Simplify your life.</p>\n";

    String EXPECTED_PROJECT_LICENSE = "LicenseRef-proprietary";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "https://lh3.googleusercontent.com/_4zBNFjA8S9yjNB_ONwqBvxTvyXYdC7Nh1jYZ2x6YEcldBr2fyijdjM2J5EoVdTpnkA=w256";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "https://lh3.googleusercontent.com/_4zBNFjA8S9yjNB_ONwqBvxTvyXYdC7Nh1jYZ2x6YEcldBr2fyijdjM2J5EoVdTpnkA=w256";
    int EXPECTED_SCREENSHOT_COUNT = 2;
    short EXPECTED_SCHEENSHOT_HEIGHT = 423;
    String EXPECTED_SCREENSHOT_URL = "https://flathub.org/repo/screenshots/com.discordapp.Discord-stable/752x423/com.discordapp.Discord-37a5e4d4631fa5155a87bb08d30da1b0.png";
    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "InstantMessaging";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.findDefaultDeveloperName()).isEqualToIgnoringCase(EXPECTED_DEVELOPER_NAME);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

  }

  @Test
  public void when_ParsingKdenlive_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-kdenlive.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.kde.kdenlive";
    String EXPECTED_RUNTIME = "org.kde.Platform/x86_64/5.11";
    String EXPECTED_DEFAULT_NAME = "Kdenlive";
    String EXPECTED_DEFAULT_SUMMARY = "Video Editor";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>Kdenlive is a non linear video editor. It is based on the MLT framework and accepts many audio and video formats, allows you to add effects, transitions and render into the format of your choice.</p>\n"
        + "<p>Features:</p>\n"
        + "<ul>\n"
        + "<li>Intuitive multitrack interface.</li>\n"
        + "<li>Many effects and transitions.</li>\n"
        + "<li>Color scopes</li>\n"
        + "<li>Basic DVD Wizard</li>\n"
        + "</ul>\n";

    String EXPECTED_PROJECT_LICENSE = "GPL-2.0+";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.kde.kdenlive.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.kde.kdenlive.png";
    int EXPECTED_SCREENSHOT_COUNT = 1;
    short EXPECTED_SCHEENSHOT_HEIGHT = 423;
    String EXPECTED_SCREENSHOT_URL = "https://flathub.org/repo/screenshots/org.kde.kdenlive-stable/752x423/org.kde.kdenlive-cafab05e4cbb473c160a6adbc41532e2.png";
    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "AudioVideo";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);
  }

  @Test
  public void when_ParsingVim_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-vim.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.vim.Vim";
    String EXPECTED_RUNTIME = "org.freedesktop.Platform/x86_64/1.6";
    String EXPECTED_DEFAULT_NAME = "Vim";
    String EXPECTED_DEFAULT_SUMMARY = "Edit text files";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>Vim is an advanced text editor that seeks to provide the power of the de-facto Unix editor 'Vi', with a more complete feature set. It's useful whether you're already using vi or using a different editor.</p>\n"
        + "<p>Vim is a highly configurable text editor built to enable efficient text editing. Vim is often called a \"programmer's editor,\" and so useful for programming that many consider it an entire IDE. It is not just for programmers, though. Vim is perfect for all kinds of text editing, from composing email to editing configuration files.</p>\n";

    String EXPECTED_PROJECT_LICENSE = "Vim";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.vim.Vim.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.vim.Vim.png";
    int EXPECTED_SCREENSHOT_COUNT = 1;
    short EXPECTED_SCHEENSHOT_HEIGHT = 423;
    String EXPECTED_SCREENSHOT_URL = "https://flathub.org/repo/screenshots/org.vim.Vim-stable/752x423/org.vim.Vim-b8bde54ac7d233817a6d3ef72792e564.png";
    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "TextEditor";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);
  }


  @Test
  public void when_MergingComponentData_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-duplicated-entry.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    AppdataComponent component2 = null;
    String EXPECTED_FLATPAKID = "org.mozilla.Thunderbird";
    String EXPECTED_RUNTIME = "org.gnome.Platform/x86_64/3.28";
    String EXPECTED_DEFAULT_NAME = "Thunderbird";
    String EXPECTED_DEFAULT_SUMMARY = "EMail Client";
    String EXPECTED_DEFAULT_DESCRIPTION = "<p>Mozilla Thunderbird is a free, open-source, and cross-platform email, news, RSS, and chat client developed by the Mozilla Foundation.</p>\n";

    String EXPECTED_HOMEPAGE_URL = "https://www.mozilla.org/en-US/thunderbird/";
    String EXPECTED_DONATION_URL = "http://www.gnome.org/friends/";
    String EXPECTED_BUGTRACKER_URL = "https://bugzilla.gnome.org/enter_bug.cgi?product=gnome-builder";

    String EXPECTED_PROJECT_LICENSE = "MPL-2.0";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.mozilla.Thunderbird.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.mozilla.Thunderbird.png";
    int EXPECTED_SCREENSHOT_COUNT = 1;
    short EXPECTED_SCHEENSHOT0_HEIGHT = 846;
    String EXPECTED_SCREENSHOT0_URL = "https://flathub.org/repo/screenshots/org.mozilla.Thunderbird-stable/1504x846/org.mozilla.Thunderbird-2c4899b0d7f7c95fae89b60643336417.png";
    short EXPECTED_SCHEENSHOT1_HEIGHT = 351;
    String EXPECTED_SCREENSHOT1_URL = "https://flathub.org/repo/screenshots/org.gnome.Builder-stable/624x351/org.gnome.Builder-19b4818c4fda40f94d8e6ccc1379dc6d.png";

    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "Email";

    String EXPECTED_RELEASE_VERSION = "52.7.0";
    String EXPECTED_RELEASE_DESCRIPTION = "";

    int EXPECTED_RELEASE_TIMESTAMP = 1521763200;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null && componentList.size() == 2) {
      component = componentList.get(0);
      component2 = componentList.get(1);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(2);

    //Check info present in component
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);

    assertThat(component.getScreenshots()).isNotNull();
    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
    assertThat(component.findReleaseInfoByMostRecent().get().getDescription())
      .isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

    //Check missing info at component
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNull();
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNull();
    assertThat(component.getCategories()).isNull();

    //Check info present in component2
    assertThat(component2.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component2.getCategories()).isNotNull();
    assertThat(component2.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component2.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

    //Merge component with component2
    component.merge(component2);

    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);

    assertThat(component.getScreenshots()).isNotNull();
    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
    assertThat(component.findReleaseInfoByMostRecent().get().getDescription())
      .isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNotNull();
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNotNull();
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

  }

  @Test
  public void when_MergingComponentDataReverse_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-duplicated-entry.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    AppdataComponent component2 = null;
    String EXPECTED_FLATPAKID = "org.mozilla.Thunderbird";
    String EXPECTED_RUNTIME = "org.gnome.Platform/x86_64/3.28";
    String EXPECTED_DEFAULT_NAME = "Thunderbird";
    String EXPECTED_DEFAULT_SUMMARY = "EMail Client";
    String EXPECTED_DEFAULT_DESCRIPTION = "<p>Mozilla Thunderbird is a free, open-source, and cross-platform email, news, RSS, and chat client developed by the Mozilla Foundation.</p>\n";

    String EXPECTED_HOMEPAGE_URL = "https://www.mozilla.org/en-US/thunderbird/";
    String EXPECTED_DONATION_URL = "http://www.gnome.org/friends/";
    String EXPECTED_BUGTRACKER_URL = "https://bugzilla.gnome.org/enter_bug.cgi?product=gnome-builder";

    String EXPECTED_PROJECT_LICENSE = "MPL-2.0";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/org.mozilla.Thunderbird.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/org.mozilla.Thunderbird.png";
    int EXPECTED_SCREENSHOT_COUNT = 1;
    short EXPECTED_SCHEENSHOT0_HEIGHT = 846;
    String EXPECTED_SCREENSHOT0_URL = "https://flathub.org/repo/screenshots/org.mozilla.Thunderbird-stable/1504x846/org.mozilla.Thunderbird-2c4899b0d7f7c95fae89b60643336417.png";
    short EXPECTED_SCHEENSHOT1_HEIGHT = 351;
    String EXPECTED_SCREENSHOT1_URL = "https://flathub.org/repo/screenshots/org.gnome.Builder-stable/624x351/org.gnome.Builder-19b4818c4fda40f94d8e6ccc1379dc6d.png";

    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "Email";

    String EXPECTED_RELEASE_VERSION = "52.7.0";
    String EXPECTED_RELEASE_DESCRIPTION = "";

    int EXPECTED_RELEASE_TIMESTAMP = 1521763200;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null && componentList.size() == 2) {
      component = componentList.get(0);
      component2 = componentList.get(1);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(2);

    //Check info present in component
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);

    assertThat(component.getScreenshots()).isNotNull();
    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
    assertThat(component.findReleaseInfoByMostRecent().get().getDescription())
      .isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

    //Check missing info at component
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNull();
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNull();
    assertThat(component.getCategories()).isNull();

    //Check info present in component2
    assertThat(component2.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component2.getCategories()).isNotNull();
    assertThat(component2.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component2.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

    //Merge component2 with component
    component2.merge(component);

    assertThat(component2.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component2.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component2.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component2.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component2.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component2.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component2.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);
    assertThat(component2.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);

    assertThat(component2.getScreenshots()).isNotNull();
    assertThat(component2.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component2.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT))
      .isPresent();
    assertThat(
      component2.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT0_HEIGHT)
        .get()).isEqualToIgnoringCase(EXPECTED_SCREENSHOT0_URL);

    assertThat(component2.findReleaseInfoByMostRecent()).isPresent();
    assertThat(component2.findReleaseInfoByMostRecent().get().getVersion())
      .isEqualToIgnoringCase(EXPECTED_RELEASE_VERSION);
    assertThat(component2.findReleaseInfoByMostRecent().get().getTimestamp())
      .isEqualTo(EXPECTED_RELEASE_TIMESTAMP);
    assertThat(component2.findReleaseInfoByMostRecent().get().getDescription())
      .isEqualTo(EXPECTED_RELEASE_DESCRIPTION);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64)).isNotNull();
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component2.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component2.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component2.getCategories()).isNotNull();
    assertThat(component2.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component2.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);

  }

  @Test
  public void when_ParsingLbry_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-app-lbry.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "io.lbry.lbry-app";
    String EXPECTED_RUNTIME = "org.freedesktop.Platform/x86_64/1.6";
    String EXPECTED_DEFAULT_NAME = "LBRY";
    String EXPECTED_DEFAULT_SUMMARY = "A browser and wallet for LBRY, the decentralized, user-controlled content marketplace.";
    String EXPECTED_DEFAULT_DESCRIPTION =
      "<p>LBRY is a free, open, and community-run digital marketplace.</p>\n"
        + "<p>You own your data. You control the network. Indeed, you are the network.</p>\n"
        + "<p>Hollywood films, college lessons, amazing streamers and more are on the first media network ruled by you.</p>\n";

    String EXPECTED_PROJECT_LICENSE = "MIT";

    short EXPECTED_ICONHEIGHT_128 = 128;
    String EXPECTED_ICONURL_128 = "/repo/appstream/x86_64/icons/128x128/io.lbry.lbry-app.png";
    short EXPECTED_ICONHEIGHT_64 = 64;
    String EXPECTED_ICONURL_64 = "/repo/appstream/x86_64/icons/64x64/io.lbry.lbry-app.png";
    int EXPECTED_SCREENSHOT_COUNT = 5;
    short EXPECTED_SCHEENSHOT_HEIGHT = 846;
    String EXPECTED_SCREENSHOT_URL = "https://flathub.org/repo/screenshots/io.lbry.lbry-app-stable/1504x846/io.lbry.lbry-app-21533f305ef79994d2c3a678b3e6cd60.png";
    int EXPECTEC_CATEGORY_COUNT = 2;
    String EXPECTED_CATEGORY_FIRST = "AudioVideo";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_DESKTOP);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_128);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_128).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_128))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_128);

    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getHeight())
      .isEqualTo(EXPECTED_ICONHEIGHT_64);
    assertThat(component.findIconByHeight(EXPECTED_ICONHEIGHT_64).getValue()).isNotNull();
    assertThat(component.findIconUrl(ICON_BASE_RELATIVE_PATH, EXPECTED_ICONHEIGHT_64))
      .isEqualToIgnoringCase(EXPECTED_ICONURL_64);

    assertThat(component.getScreenshots().size()).isEqualTo(EXPECTED_SCREENSHOT_COUNT);
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT))
      .isPresent();
    assertThat(
      component.getScreenshots().get(0).findThumbnailUrlByHeight(EXPECTED_SCHEENSHOT_HEIGHT).get())
      .isEqualToIgnoringCase(EXPECTED_SCREENSHOT_URL);

    assertThat(component.getCategories()).isNotNull();
    assertThat(component.getCategories().getCategory().size()).isEqualTo(EXPECTEC_CATEGORY_COUNT);
    assertThat(component.getCategories().getCategory().get(0))
      .isEqualToIgnoringCase(EXPECTED_CATEGORY_FIRST);
  }

  @Test
  public void when_ParsingGnomeRuntime_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-runtime-gnome.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.gnome.Platform";
    String EXPECTED_RUNTIME = "org.gnome.Platform/x86_64/3.26";
    String EXPECTED_DEFAULT_NAME = "GNOME Application Platform version 3.26";
    String EXPECTED_DEFAULT_SUMMARY = "Shared libraries used by GNOME applications";
    String EXPECTED_DEFAULT_DESCRIPTION = "";
    String EXPECTED_HOMEPAGE_URL = "http://www.gnome.org/";
    String EXPECTED_PROJECT_LICENSE = "GPL-2.0+";

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_RUNTIME);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findHomepageUrl()).isPresent();
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);
    assertThat(component.findDonationUrl()).isNotPresent();
  }


  @Test
  public void when_ParsingGtk3ThemeAdapta_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-runtime-gtk3theme-adapta.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.gtk.Gtk3theme.Adapta";
    String EXPECTED_RUNTIME = "org.gnome.Sdk/x86_64/3.26";
    String EXPECTED_DEFAULT_NAME = "Adapta Gtk+ theme";
    String EXPECTED_DEFAULT_SUMMARY = "Adapta Gtk+ theme";
    String EXPECTED_DEFAULT_DESCRIPTION = "<p>An adaptive Gtk+ theme based on Material Design</p>\n";
    String EXPECTED_HOMEPAGE_URL = "https://github.com/adapta-project/adapta-gtk-theme";
    String EXPECTED_PROJECT_LICENSE = null;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_RUNTIME);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

    assertThat(component.findHomepageUrl()).isPresent();
    assertThat(component.findHomepageUrl().get()).isEqualToIgnoringCase(EXPECTED_HOMEPAGE_URL);
    assertThat(component.findDonationUrl()).isNotPresent();
  }

  @Test
  public void when_ParsingOpenJdk9_Expect_ComponentInfoObtained() throws Exception {

    //Given
    String appDataResourceFile = "appstream-test-runtime-openjdk9.xml";
    List<AppdataComponent> componentList = null;
    AppdataComponent component = null;
    String EXPECTED_FLATPAKID = "org.freedesktop.Sdk.Extension.openjdk9";
    String EXPECTED_RUNTIME = "org.freedesktop.Sdk/x86_64/1.6";
    String EXPECTED_DEFAULT_NAME = "OpenJdk 9 Sdk extension";
    String EXPECTED_DEFAULT_SUMMARY = "OpenJdk 9 Sdk extension";
    String EXPECTED_DEFAULT_DESCRIPTION = "<p>This SDK extension allows you to build java apps.</p>\n";
    String EXPECTED_PROJECT_LICENSE = null;

    //When
    File file = new File(classLoader.getResource(appDataResourceFile).getFile());
    componentList = AppdataParser.parseAppdataFile(file);
    if (componentList != null) {
      component = componentList.get(0);
    }

    //Then
    assertThat(componentList).isNotNull();
    assertThat(componentList.size()).isEqualTo(1);
    assertThat(component.getType()).isEqualToIgnoringCase(APPSTREAM_TYPE_RUNTIME);
    assertThat(component.getFlatpakId()).isEqualToIgnoringCase(EXPECTED_FLATPAKID);
    assertThat(component.getFlatpakRuntime()).isEqualToIgnoringCase(EXPECTED_RUNTIME);
    assertThat(component.findDefaultName()).isEqualToIgnoringCase(EXPECTED_DEFAULT_NAME);
    assertThat(component.findDefaultSummary()).isEqualToIgnoringCase(EXPECTED_DEFAULT_SUMMARY);
    assertThat(component.findDefaultDescription())
      .isEqualToIgnoringCase(EXPECTED_DEFAULT_DESCRIPTION);
    assertThat(component.getProjectLicense()).isEqualToIgnoringCase(EXPECTED_PROJECT_LICENSE);

  }


}
