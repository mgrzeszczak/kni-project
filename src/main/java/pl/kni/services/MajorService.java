package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.models.Semester;
import pl.kni.repositories.MajorRepository;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface MajorService {

    List<Semester> getSemesters(String facultyAbbrev, String majorAbrrev) throws MajorNotFoundException;

}
