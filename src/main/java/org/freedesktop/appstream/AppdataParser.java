package org.freedesktop.appstream;

import org.freedesktop.appstream.appdata.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 08/04/17.
 */
public class AppdataParser {


    public static List<AppdataComponent> parseAppdataFile(File file) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Components.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Components components = (Components) jaxbUnmarshaller.unmarshal(file);
        List<AppdataComponent> componentList = new ArrayList<AppdataComponent>();

        for (Component component: components.getComponent()) {
            componentList.add(new AppdataComponent(component));
        }

        return componentList;

    }

}
