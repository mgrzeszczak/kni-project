package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.forms.MajorCreateForm;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.MajorRepository;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface MajorService {

    List<Semester> getSemesters(String facultyAbbrev, String majorAbrrev) throws MajorNotFoundException;
    Major create(MajorCreateForm majorCreateForm) throws FacultyNotFoundException;
    void remove(long id) throws MajorNotFoundException;
    long getMajorId(String facultyAbbrev, String majorAbbrev) throws MajorNotFoundException;
    Major findByFacultyAndMajorAbbrev(String facultyAbbrev, String majorAbbrev) throws MajorNotFoundException;
    Major findByNameAndFacultyId(String name,long id) throws MajorNotFoundException;
    Major findById(long id) throws MajorNotFoundException;

}
