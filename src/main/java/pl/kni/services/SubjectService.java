package pl.kni.services;

import pl.kni.exceptions.SemesterNotFoundException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.forms.SubjectCreateForm;
import pl.kni.models.Opinion;
import pl.kni.models.Subject;

/**
 * Created by Maciej on 11.10.2015.
 */
public interface SubjectService {

    Subject findById(long id) throws SubjectNotFoundException;
    Subject create(SubjectCreateForm subjectCreateForm) throws SemesterNotFoundException;
    void remove(long id) throws SubjectNotFoundException;
    Subject findByNameAndSemesterId(String name, long id) throws SubjectNotFoundException;
    double averageDifficulty(Subject subject);

}
