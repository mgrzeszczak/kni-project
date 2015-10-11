package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.MajorRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Service
@Transactional
public class MajorServiceImpl implements MajorService{

    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Semester> getSemesters(String facultyAbbrev, String majorAbrrev) throws MajorNotFoundException {
        Major major = majorRepository.findByFacultyAndMajorAbbrevs(facultyAbbrev, majorAbrrev);
        if (major==null) throw new MajorNotFoundException();
        return major.getSemesters();
    }

}
