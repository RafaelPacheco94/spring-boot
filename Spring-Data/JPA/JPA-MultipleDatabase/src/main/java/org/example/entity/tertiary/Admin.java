package org.example.entity.tertiary;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin")
@JacksonXmlRootElement(localName = "admin")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @Column(name = "name")
    @JacksonXmlProperty
    private String name;

    public Admin() {
    }

    public Admin(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
