package pl.kni.services;

import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.Opinion;

/**
 * Created by Maciej on 17.10.2015.
 */
public interface OpinionService {

    Opinion add(OpinionCreateForm opinionCreateForm) throws SubjectNotFoundException;

}
