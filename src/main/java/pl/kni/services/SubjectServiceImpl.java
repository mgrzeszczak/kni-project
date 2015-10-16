package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.SubjectCreateForm;
import pl.kni.models.Semester;
import pl.kni.models.Subject;
import pl.kni.repositories.SemesterRepository;
import pl.kni.repositories.SubjectRepository;

import javax.transaction.Transactional;

/**
 * Created by Maciej on 14.10.2015.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public Subject create(SubjectCreateForm subjectCreateForm) throws SemesterNotFoundException {
        Semester semester = semesterRepository.findOne(subjectCreateForm.getSemesterId());
        if (semester == null) throw new SemesterNotFoundException();
        Subject subject = new Subject();
        subject.setSemester(semester);
        subject.setName(subjectCreateForm.getName());
        subject.setDescription(subjectCreateForm.getDescription());
        subject.setAbbrev(subjectCreateForm.getName().toLowerCase().substring(0, 5));
        return subjectRepository.save(subject);
    }

    @Override
    public void remove(long id) throws SubjectNotFoundException {
        Subject subject = subjectRepository.findOne(id);
        if (subject == null) throw new SubjectNotFoundException();
        subjectRepository.delete(subject);
    }

    @Override
    public Subject findByNameAndSemesterId(String name, long id) throws SubjectNotFoundException {
        Subject subject = subjectRepository.findByNameAndSemesterId(name,id);
        if (subject == null) throw new SubjectNotFoundException();
        return subject;
    }
}
