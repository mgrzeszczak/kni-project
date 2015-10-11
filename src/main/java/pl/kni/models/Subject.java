package pl.kni.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne()
    private Semester semester;

    @OneToMany(mappedBy = "subject")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.REMOVE)
    private List<Opinion> opinions;
    private String name;
    private String description;
    private String abbrev;

    public Subject() {
        teachers = new ArrayList<Teacher>();
        opinions = new ArrayList<Opinion>();
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions) {
        this.opinions = opinions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
