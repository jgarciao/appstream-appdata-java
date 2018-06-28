# appstream-appdata-java

This is a simple Java library to parse [AppData files.](https://www.freedesktop.org/software/appstream/docs/chap-Quickstart.html#sect-Quickstart-DesktopApps) It is used in [linux-store-backend](https://github.com/jgarciao/linux-store-backend)

## How this lib has been created

* Create a new maven project
  * IntelliJ > File > New > Project
  * Maven > Create from archetype: org.apache.maven.archetypes:maven-archetype-quickstart
    * GroupID: org.freedesktop.appstream
    * ArtifactID: appstream-appdata
* Create an example App Data file xds/appstream-seed.xml
  * It contains an example component with most of the appstream spec. 
  * It's added twice in the file on purpose to tell the system that we'll have a list of components. 
* IntelliJ > Select xds/appstream-seed.xml > Generate XSD schema from XML file:
  * Result schema file name: appstream.xsd
  * Design type: local elements/types
  * Detect simple content type: smart
* Rename obtained file appstream-seed.xml1.xsd to appstream-lang.xsd 
* Edit xds/appstream.xsd
  * Import appstream-lang.xsd 
  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xs:import namespace="http://www.w3.org/XML/1998/namespace"
      schemaLocation="appstream-lang.xsd"/>
  ``` 
  * Manually modify "description" type
  ```
  # Before
  <xs:element name="description">
    <xs:complexType>
      <xs:choice maxOccurs="unbounded" minOccurs="0">
        <xs:element ref="p"/>
        <xs:element ref="ul"/>
        <xs:element ref="ol"/>
      </xs:choice>
      <xs:attribute ref="xml:lang"/>
    </xs:complexType>
  </xs:element>

  # After
  <!-- We manually modify description type to allow mixed content -->
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

* Create bindings using xcj:
```
  cd ROOT_OF_THE_PROJECT
  xjc -d src/main/java/ -p org.freedesktop.appstream.appdata xsd/appstream.xsd 
```


## Building appstream-appdata-java

```
mvn install
```