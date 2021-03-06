package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.FacultyNotFoundException;
import pl.kni.forms.FacultyCreateForm;
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

    @Override
    public Faculty create(FacultyCreateForm facultyCreateForm) {
        Faculty faculty = new Faculty();
        faculty.setAbbrev(facultyCreateForm.getAbbrev());
        faculty.setName(facultyCreateForm.getName());
        return facultyRepository.save(faculty);
    }

    @Override
    public void remove(long id) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findOne(id);
        if(faculty==null) throw new FacultyNotFoundException();
        facultyRepository.delete(faculty);
    }

    @Override
    public long getFacultyIdByAbbrev(String abbrev) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findByAbbrev(abbrev);
        if(faculty==null) throw new FacultyNotFoundException();
        return faculty.getId();
    }

    @Override
    public Faculty findByAbbrev(String abbrev) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findByAbbrev(abbrev);
        if(faculty==null) throw new FacultyNotFoundException();
        return faculty;
    }

    @Override
    public Faculty findByName(String name) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findByName(name);
        if (faculty==null) throw new FacultyNotFoundException();
        return faculty;
    }

    @Override
    public Faculty findById(long id) throws FacultyNotFoundException {
        Faculty faculty = facultyRepository.findOne(id);
        if (faculty==null) throw new FacultyNotFoundException();
        return faculty;
    }
}
