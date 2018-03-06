# appstream-appdata-java

This is a simple Java library to parse [AppData files.](https://www.freedesktop.org/software/appstream/docs/chap-Quickstart.html#sect-Quickstart-DesktopApps) It is used in [linux-store-backend](https://github.com/jgarciao/linux-store-backend)

## How this lib has been created

* Create a new maven project
  * IntelliJ > File > New > Project
  * Maven > Create from archetype: org.apache.maven.archetypes:maven-archetype-quickstart
    * GroupID: org.freedesktop.appstream
    * ArtifactID: appstream-appdata
* Create an example App Data file xds/appstream-flathub-x86_64-201803-subset.xml
* IntelliJ > Select xds/appstream-flathub-x86_64-201803-subset.xml > Generate XSD schema from XML file:
  * Design type: local elements/types
  * Detect simple content type: smart
* Rename obtained file appstream-flathub-x86_64-201803-subset.xsd to appstream.xsd 
* Rename obtained file appstream-flathub-x86_64-201803-subset.xml1.xsd to appstream-lang.xsd  
* Edit xds/appstream.xsd
  * Import appstream-lang.xsd 
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:import namespace="http://www.w3.org/XML/1998/namespace"
      schemaLocation="appstream-lang.xsd"/>
  ``` 
  * Manually modify description type
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

  <xs:element name="description" type="description"/>
  ```
  * Manually add "ol" element
  ```
   <xs:element name="ol">
    <xs:complexType mixed="true">
      <xs:sequence>
        <xs:element ref="li" maxOccurs="unbounded" minOccurs="0"/>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
  ```

* Create bindings using xcj:
```
  cd ROOT_OF_THE_PROJECT
  xjc -d src/main/java/ -p org.freedesktop.appstream.appdata xsd/appstream.xsd 
```


## Building appstream-appdata-java

```
mvn install
```