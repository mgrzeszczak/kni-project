package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.forms.MajorCreateForm;
import pl.kni.models.Faculty;
import pl.kni.models.Major;
import pl.kni.models.Semester;
import pl.kni.repositories.FacultyRepository;
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
    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public List<Semester> getSemesters(String facultyAbbrev, String majorAbrrev) throws MajorNotFoundException {
        Major major = majorRepository.findByFacultyAndMajorAbbrevs(facultyAbbrev, majorAbrrev);
        if (major==null) throw new MajorNotFoundException();
        return major.getSemesters();
    }

    @Override
    public Major create(MajorCreateForm majorCreateForm) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findOne(majorCreateForm.getFacultyId());
        if (faculty==null) throw new FacultyNotFoundException();
        Major major = new Major();
        major.setFaculty(faculty);
        major.setName(majorCreateForm.getName());
        major.setAbbrev("null");
        return majorRepository.save(major);
    }

    @Override
    public void remove(long id) throws MajorNotFoundException {
        Major major = majorRepository.findOne(id);
        if (major==null) throw new MajorNotFoundException();
        majorRepository.delete(major);
    }

    @Override
    public long getMajorId(String facultyAbbrev, String majorAbbrev) throws MajorNotFoundException {
        Major major = majorRepository.findByFacultyAndMajorAbbrevs(facultyAbbrev,majorAbbrev);
        if(major==null) throw new MajorNotFoundException();
        return major.getId();
    }

    @Override
    public Major findByFacultyAndMajorAbbrev(String facultyAbbrev, String majorAbbrev) throws MajorNotFoundException {
        Major major = majorRepository.findByFacultyAndMajorAbbrevs(facultyAbbrev,majorAbbrev);
        if(major==null) throw new MajorNotFoundException();
        return major;
    }

    @Override
    public Major findByNameAndFacultyId(String name, long id) throws MajorNotFoundException {
        Major major = majorRepository.findByNameAndFacultyId(name, id);
        if(major==null) throw new MajorNotFoundException();
        return major;
    }

    @Override
    public Major findById(long id) throws MajorNotFoundException {
        Major major = majorRepository.findOne(id);
        if(major==null) throw new MajorNotFoundException();
        return major;
    }

}
