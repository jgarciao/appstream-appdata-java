package org.freedesktop.appstream;

import org.freedesktop.appstream.appdata.Components;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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
}
