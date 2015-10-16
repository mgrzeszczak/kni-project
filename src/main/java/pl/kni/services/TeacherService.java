package pl.kni.services;

import pl.kni.exceptions.TeacherNotFoundException;
import pl.kni.forms.TeacherCreateForm;
import pl.kni.models.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface TeacherService {

    Teacher findByFirstAndLastName(String firstName, String lastName) throws TeacherNotFoundException;
    Teacher create(TeacherCreateForm teacherCreateForm);
    void remove(long id) throws TeacherNotFoundException;
    Optional<Teacher> findById(long id);
    List<Teacher> all();

}
