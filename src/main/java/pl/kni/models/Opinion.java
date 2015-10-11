package pl.kni.models;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Subject subject;

    public Opinion() {}

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
