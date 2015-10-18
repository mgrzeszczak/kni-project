package pl.kni.services;

import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.Opinion;
import pl.kni.models.Subject;
import pl.kni.models.User;

/**
 * Created by Maciej on 17.10.2015.
 */
public interface OpinionService {

    Opinion add(OpinionCreateForm opinionCreateForm, String username) throws SubjectNotFoundException;

    Opinion checkIfOpinionCreated(String username, long subjectId);

}
