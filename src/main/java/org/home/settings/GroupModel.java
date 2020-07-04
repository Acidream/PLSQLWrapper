package org.home.settings;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import java.util.List;

import static org.home.settings.Utils.getValueForKey;

/**
 * Created by oleg on 2017-07-12.
 */
public class GroupModel {

//    group=iacq_order
//    EntityFolder=c:\temp\entity
//    ServiceFolder=c:\temp\service
//    DBObjects=abs.iacq_order%
//    SingleService=true

    String name;
    String entityFolder;
    String serviceFolder;
    List<String> DBObjects;
    boolean isSingleService;

    public GroupModel() {
    }

    public String getName() {
        return name;
    }
    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getEntityFolder() {
        return entityFolder;
    }

    public void setEntityFolder(String entityFolder) {
        this.entityFolder = entityFolder;
    }

    public String getServiceFolder() {
        return serviceFolder;
    }

    public void setServiceFolder(String serviceFolder) {
        this.serviceFolder = serviceFolder;
    }


    public boolean isSingleService() {
        return isSingleService;
    }

    public void setSingleService(boolean singleService) {
        isSingleService = singleService;
    }

    public List<String> getDBObjects() {
        return DBObjects;
    }
    @XmlList
    public void setDBObjects(List<String> DBObjects) {
        this.DBObjects = DBObjects;
    }
}

