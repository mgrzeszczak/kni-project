package pl.kni.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Semester {

    @Id
    @GeneratedValue
    private long id;
    private int number;

    @ManyToOne()
    private Major major;
    @OneToMany(mappedBy = "semester",cascade = CascadeType.REMOVE)
    private List<Subject> subjects;

    public Semester(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Semester() {
        this.subjects = new ArrayList<Subject>();
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        if (subjects==null) subjects = new ArrayList<Subject>();
        subjects.add(subject);
    }
}
