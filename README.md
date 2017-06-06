# appstream-appdata-java

This is a simple Java library to parse [AppData files.](https://www.freedesktop.org/software/appstream/docs/chap-Quickstart.html#sect-Quickstart-DesktopApps) It is used in [linux-store-backend](https://github.com/jgarciao/linux-store-backend)

## How this lib has been created

* Create a new maven project
  * IntelliJ > File > New > Project
  * Maven > Create from archetype: org.apache.maven.archetypes:maven-archetype-quickstart
    * GroupID: org.freedesktop.appstream
    * ArtifactID: appstream-appdata
* Create an example App Data file xds/appstream-gnome-apps-few.xml
* IntelliJ > Select xds/appstream-gnome-apps-few.xml > Generate XSD schema from XML file:
  * Design type: local elements/local complex types
  * Detect simple content type: smart
* Rename appstream-gnome-apps-few.xml1.xsd to appstream-gnome-apps-lang.xsd  
* Edit xds/appstream-gnome-apps-few.xsd
  * Import appstream-gnome-apps-lang.xsd 
  ```
  <xs:import namespace="http://www.w3.org/XML/1998/namespace"
            schemaLocation="appstream-gnome-apps-lang.xsd"/>
  ``` 
  * Manually fix description type and others with mixed content
  ```
  <!-- schema fragment having  mixed content -->
  <xs:complexType name="description" mixed="true">
    <xs:sequence>
      <xs:element name="p" type="xs:string" maxOccurs="unbounded" minOccurs="0"/>
      <xs:element ref="ul"  minOccurs="0"/>
      <xs:element ref="ol"  minOccurs="0"/>
    </xs:sequence>
    <xs:attribute ref="xml:lang"/>
  </xs:complexType>
  ```

* Create bindings using xcj:
```
mkdir appstream-gnome-apps-generated
	xjc -d appstream-gnome-apps-generated -p org.freedesktop.appstream.appdata appstream-gnome-apps-few.xsd
```
* Move classes to org.freedesktop.appstream.appdata