package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.forms.OpinionCreateForm;
import pl.kni.models.Opinion;
import pl.kni.models.Subject;
import pl.kni.models.User;
import pl.kni.repositories.OpinionRepository;
import pl.kni.repositories.SubjectRepository;

import javax.transaction.Transactional;

/**
 * Created by Maciej on 17.10.2015.
 */
@Service
@Transactional
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    public Opinion add(OpinionCreateForm opinionCreateForm, String username) throws SubjectNotFoundException {
        long subjectId = opinionCreateForm.getSubjectId();
        Subject subject = subjectService.findById(subjectId);
        Opinion opinion = new Opinion(opinionCreateForm,subject);
        opinion.setUsername(username);
        return opinionRepository.save(opinion);
    }

    @Override
    public Opinion checkIfOpinionCreated(String username, long subjectId) {
        return opinionRepository.findOpinionByUsernameAndSubjectId(username,subjectId);
    }

}
