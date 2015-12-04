package pl.kni.config;


import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Maciej on 17.11.2015.
 */
public class ExtendedCommonsMultipartResolver extends CommonsMultipartResolver {
    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        try {
            return super.parseRequest(request);
        } catch (MaxUploadSizeExceededException e) {
            request.setAttribute("MaxUploadSizeExceededException", e);
            return parseFileItems(Collections.<FileItem> emptyList(), null);
        }
    }
}
