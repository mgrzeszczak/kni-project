package pl.kni.exceptions;

/**
 * Created by Maciej on 17.10.2015.
 */
public class NoteCreationFailedException extends Exception {

    private Cause cause;
    public enum Cause {
        NAME_TAKEN,SUBJECT_ID,IOEXCEPTION;
    }

    public NoteCreationFailedException(Cause cause) {
        this.cause = cause;
    }

    public Cause cause() {
        return cause;
    }
}
