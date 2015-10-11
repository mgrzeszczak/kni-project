package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.models.Semester;
import pl.kni.models.Subject;
import pl.kni.repositories.SemesterRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Subject> getSubjects(String faculty, String major, int number) throws SemesterNotFoundException {
        Semester semester = semesterRepository.findByFacultyMajorAndNumber(faculty,major,number);
        if (semester==null) throw new SemesterNotFoundException();
        return semester.getSubjects();
    }
}
