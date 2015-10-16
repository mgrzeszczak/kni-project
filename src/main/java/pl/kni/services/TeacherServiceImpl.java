package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.TeacherNotFoundException;
import pl.kni.forms.TeacherCreateForm;
import pl.kni.models.Teacher;
import pl.kni.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by Maciej on 16.10.2015.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher findByFirstAndLastName(String firstName, String lastName) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findByFirstNameAndLastName(firstName,lastName);
        if (teacher==null) throw new TeacherNotFoundException();
        return teacher;
    }

    @Override
    public Teacher create(TeacherCreateForm teacherCreateForm) {
        Teacher teacher = new Teacher(teacherCreateForm);
        return teacherRepository.save(teacher);
    }

    @Override
    public void remove(long id) throws TeacherNotFoundException {
        Teacher teacher = teacherRepository.findOne(id);
        if (teacher==null) throw new TeacherNotFoundException();
        teacherRepository.delete(teacher);
    }

    @Override
    public Optional<Teacher> findById(long id){
        return Optional.ofNullable(teacherRepository.findOne(id));
    }

    @Override
    public List<Teacher> all() {
        return (List<Teacher>)teacherRepository.findAll();
    }
}
