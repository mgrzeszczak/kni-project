package pl.kni.models;

import javax.persistence.*;

/**
 * Created by Maciej on 16.10.2015.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String author;
    private int recommended =1;

    @ManyToOne
    private Subject subject;

    public Book() {

    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
