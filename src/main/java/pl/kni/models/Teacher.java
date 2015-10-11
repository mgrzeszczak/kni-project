package pl.kni.models;

import javax.persistence.*;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String description;

    @ManyToOne
    private Subject subject;

    public Teacher() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
