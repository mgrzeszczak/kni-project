package pl.kni.models;

import pl.kni.forms.OpinionCreateForm;

import javax.persistence.*;

/**
 * Created by Maciej on 11.10.2015.
 */
@Entity
public class Opinion {

    @Id
    @GeneratedValue
    private long id;
    private String comment;
    private int rating;

    private String username;

    @ManyToOne
    private Subject subject;

    public Opinion(OpinionCreateForm opinionCreateForm, Subject subject) {
        this.comment = opinionCreateForm.getComment();
        this.rating = opinionCreateForm.getRating();
        this.subject= subject;
    }

    public Opinion() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
