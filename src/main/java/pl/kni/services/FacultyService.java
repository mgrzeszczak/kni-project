package pl.kni.services;

import org.springframework.stereotype.Service;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.forms.FacultyCreateForm;
import pl.kni.models.Faculty;
import pl.kni.models.Major;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface FacultyService {

    List<Faculty> all();
    List<Major> getMajors(String abbrev) throws FacultyNotFoundException;
    Faculty create(FacultyCreateForm facultyCreateForm);
    void remove(long id) throws FacultyNotFoundException;
}
