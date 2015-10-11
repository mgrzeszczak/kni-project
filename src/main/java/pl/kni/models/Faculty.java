package pl.kni.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Faculty {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String abbrev;
    @OneToMany(mappedBy = "faculty",cascade = CascadeType.REMOVE)
    private List<Major> majors;

    public Faculty(List<Major> majors) {
        this.majors = majors;
    }

    public Faculty() {
        this.majors = new ArrayList<Major>();
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }

    public void addMajor(Major major){
        if (majors==null) majors = new ArrayList<Major>();
        majors.add(major);
    }
}
