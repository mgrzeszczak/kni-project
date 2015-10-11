package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.repositories.FacultyRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;


    @Override
    public List<Faculty> all() {
        return (List<Faculty>)facultyRepository.findAll();
    }

    @Override
    public List<Major> getMajors(String abbrev) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findByAbbrev(abbrev);
        if (faculty==null) throw new FacultyNotFoundException();
        return faculty.getMajors();
    }
}
