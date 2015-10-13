package pl.kni.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Maciej on 13.10.2015.
 */
public class FacultyCreateForm {

    @NotEmpty
    private String name;
    @NotEmpty
    @Length(max = 5)
    private String abbrev;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
}
