package pl.kni.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by Maciej on 18.10.2015.
 */
public class BookCreateForm {

    @NotEmpty
    @Pattern(regexp = "([A-Za-z]+)")
    private String author;
    @NotEmpty
    private String title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
