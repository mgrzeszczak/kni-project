package pl.kni.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Major {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne()
    private Faculty faculty;
    @OneToMany(mappedBy = "major", cascade = CascadeType.REMOVE)
    private List<Semester> semesters;
    private String name;
    private String abbrev;


    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public Major(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public Major() {
        this.semesters = new ArrayList<Semester>();
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public void addSemester(Semester semester){
        if (semesters==null) semesters = new ArrayList<Semester>();
        semesters.add(semester);
    }
}
