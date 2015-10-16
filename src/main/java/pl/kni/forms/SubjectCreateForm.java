package pl.kni.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Maciej on 14.10.2015.
 */
public class SubjectCreateForm {

    @NotEmpty
    @Pattern(regexp = "([A-Za-z0-9 \\u0104\\u0105\\u0106\\u0107\\u0118\\u0119\\u0141\\u0142\\u0143\\u0144\\u00D3\\u00F3\\u015A\\u015B\\u0179\\u017A\\u017B\\u017C]+)")
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private long semesterId;

    public long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(long semesterId) {
        this.semesterId = semesterId;
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
