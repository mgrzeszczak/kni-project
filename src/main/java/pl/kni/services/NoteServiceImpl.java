package pl.kni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.kni.exceptions.NoteCreationFailedException;
import pl.kni.exceptions.SubjectNotFoundException;
import pl.kni.models.*;
import pl.kni.repositories.NoteRepository;
import sun.awt.CausedFocusEvent;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maciej on 17.10.2015.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private SubjectService subjectService;

    @Value("${file.location}")
    private String resourceLocation;

    @Override
    public Note create(String name, long subjectId, MultipartFile file) throws NoteCreationFailedException{
        Subject subject = null;
        try {
            subject = subjectService.findById(subjectId);
        } catch (SubjectNotFoundException e) {
            throw new NoteCreationFailedException(NoteCreationFailedException.Cause.SUBJECT_ID);
        }
        String folderPath = getFolderPath(subject);
        File outputFile = null;
        try {
            outputFile = checkNameAvailability(name, resourceLocation+"/"+folderPath);
            if (outputFile==null) throw new NoteCreationFailedException(NoteCreationFailedException.Cause.NAME_TAKEN);
            writeToHardDrive(file,outputFile);
        } catch (IOException e) {
            throw new NoteCreationFailedException(NoteCreationFailedException.Cause.IOEXCEPTION);
        }
        return saveToDatabase(name,folderPath+"/"+name,subject);
    }

    private void writeToHardDrive(MultipartFile file, File outputFile) throws IOException {
        byte[] bytes = file.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(outputFile));
        stream.write(bytes);
        stream.close();
    }

    private String getFolderPath(Subject subject){
        Semester semester = subject.getSemester();
        Major major = semester.getMajor();
        Faculty faculty = major.getFaculty();
        return faculty.getName()+"/"+major.getName()+"/Semestr "+semester.getNumber()+"/"+subject.getName();
    }

    private File checkNameAvailability(String name, String folderPath) throws IOException {
        String filePath = folderPath+"/"+name;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            return new File(filePath);
        }
        File file = new File(filePath);
        if (file.exists()) return null;
        return file;
    }

    private Note saveToDatabase(String name, String path, Subject subject){
        Note note = new Note();
        note.setPath(path);
        note.setName(name);
        note.setSubject(subject);
        return noteRepository.save(note);
    }
}
