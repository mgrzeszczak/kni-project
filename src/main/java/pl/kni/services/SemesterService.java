package pl.kni.services;

import pl.kni.exceptions.MajorNotFoundException;
import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.models.Semester;
import pl.kni.models.Subject;

import java.util.List;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface SemesterService {

    List<Subject> getSubjects(String faculty, String major, int number) throws SemesterNotFoundException;
    Semester create(long majorId) throws MajorNotFoundException;
    void remove(long id) throws SemesterNotFoundException;
    void create(long majorId,int amount) throws MajorNotFoundException;
    void remove(List<Long> ids);
    Semester findById(long id) throws SemesterNotFoundException;
}
