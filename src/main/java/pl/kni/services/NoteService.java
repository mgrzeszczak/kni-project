package pl.kni.services;

import org.springframework.web.multipart.MultipartFile;
import pl.kni.exceptions.NoteCreationFailedException;
import pl.kni.models.Note;

import java.io.IOException;

/**
 * Created by Maciej on 17.10.2015.
 */
public interface NoteService {

    Note create(String name, long subjectId, MultipartFile file) throws NoteCreationFailedException;

}
