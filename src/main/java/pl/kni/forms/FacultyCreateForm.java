package pl.kni.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by Maciej on 13.10.2015.
 */
public class FacultyCreateForm {

    @NotEmpty
    @Pattern(regexp = "([A-Za-z \\u0104\\u0105\\u0106\\u0107\\u0118\\u0119\\u0141\\u0142\\u0143\\u0144\\u00D3\\u00F3\\u015A\\u015B\\u0179\\u017A\\u017B\\u017C]+)")
    private String name;

    @NotEmpty
    @Pattern(regexp = "([A-Za-z]+)")
    @Length(max = 5,min = 2)
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
