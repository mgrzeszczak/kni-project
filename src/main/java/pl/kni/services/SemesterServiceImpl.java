package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.models.Subject;
import pl.kni.repositories.MajorRepository;
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

    @Autowired
    private MajorRepository majorRepository;

    @Override
    public List<Subject> getSubjects(String faculty, String major, int number) throws SemesterNotFoundException {
        Semester semester = semesterRepository.findByFacultyMajorAndNumber(faculty,major,number);
        if (semester==null) throw new SemesterNotFoundException();
        return semester.getSubjects();
    }

    @Override
    public Semester create(long majorId) throws MajorNotFoundException {
        Major major = majorRepository.findOne(majorId);
        if (major==null) throw new MajorNotFoundException();
        Semester semester = new Semester();
        semester.setNumber(major.getSemesters().size() + 1);
        semester.setMajor(major);
        return semesterRepository.save(semester);
    }

    @Override
    public void remove(long id) throws SemesterNotFoundException{
        Semester semester = semesterRepository.findOne(id);
        if (semester==null) throw new SemesterNotFoundException();
        semesterRepository.delete(semester);
    }

    @Override
    public void create(long majorId, int amount) throws MajorNotFoundException {
        Major major = majorRepository.findOne(majorId);
        if (major==null) throw new MajorNotFoundException();
        for (int i=0;i<amount;i++){
            Semester semester = new Semester();
            semester.setNumber(major.getSemesters().size() + 1+i);
            semester.setMajor(major);
            semesterRepository.save(semester);
        }
    }

    @Override
    public void remove(List<Long> ids) {
        for (long id : ids){
            semesterRepository.delete(id);
        }
    }

    @Override
    public Semester findById(long id) throws SemesterNotFoundException {
        Semester semester = semesterRepository.findOne(id);
        if (semester==null) throw new SemesterNotFoundException();
        return semester;
    }
}
