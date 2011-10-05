package org.openiam.idm.srvc.cd.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;




/**
 * ReferenceData generated by hbm2java
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData", propOrder = {
    "id",
    "statusType",
    "description",
    "weight"
})
public class ReferenceData  implements java.io.Serializable {


     private ReferenceDataId id;
     private String statusType;
     private String description;
     private Integer weight;

    public ReferenceData() {
    }

	
    public ReferenceData(ReferenceDataId id) {
        this.id = id;
    }
    public ReferenceData(ReferenceDataId id, String statusType, String description, Integer weight) {
       this.id = id;
       this.statusType = statusType;
       this.description = description;
       this.weight = weight;
    }
   
    public ReferenceDataId getId() {
        return this.id;
    }
    
    public void setId(ReferenceDataId id) {
        this.id = id;
    }
    public String getStatusType() {
        return this.statusType;
    }
    
    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getWeight() {
        return this.weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
    }




}

