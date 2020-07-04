package org.home.settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.home.settings.Utils.getValueForKey;
import static org.home.settings.Utils.readTextFile;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by oleg on 2017-07-12.
 */
@XmlRootElement
public class Settings {
    private static String filename = "settings.xml";
    String conn;
    List<GroupModel> groups = new ArrayList<GroupModel>();


    public String getConn() {
        return conn;
    }

    public void setConn(String conn) {
        this.conn = conn;
    }

    public List<GroupModel> getGroups() {
        return groups;
    }

    @XmlElement(name = "Group")
    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }

    @XmlElement


    private static Settings instance ;

    public static Settings get() {
        if (instance==null) {
            JAXBContext jc = null;
            try {
                jc = JAXBContext.newInstance(Settings.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                File xml = new File(filename);
                instance = (Settings) unmarshaller.unmarshal(xml);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Settings() {}


    public static void generateSettingsExample() {
        try {
        JAXBContext jc = JAXBContext.newInstance(Settings.class);

        Settings s = new Settings();
        s.conn = "testconnnnnn";
        GroupModel gm = new GroupModel();
        gm.setEntityFolder("c:\\temp");
        gm.setServiceFolder("c:\\temp");
        gm.setDBObjects(Arrays.asList("abs.iacq_order%", "abs.fm_%"));
        gm.setName("firstGroup");

        GroupModel gm2 = new GroupModel();
        gm2.setEntityFolder("c:\\temp");
        gm2.setServiceFolder("c:\\temp");
        gm2.setDBObjects(Arrays.asList("abs.iacq_order%", "abs.fm_%,abs.alalalala"));
        gm2.setName("firstGroup2");

        s.setGroups(Arrays.asList(gm, gm2));
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File xml = new File(filename);
        if (!xml.exists()) {
            xml.createNewFile();
            marshaller.marshal(s, xml);

        }

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
