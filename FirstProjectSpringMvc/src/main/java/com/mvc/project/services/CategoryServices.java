package com.mvc.project.services;

import com.mvc.project.constant.MappingUtils;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices extends BaseServices {
    @Override
    public String baseUploadImages(HttpServletRequest request) {
        final String uploadFile = request.getServletContext().getRealPath(File.separator);
        int indexOfTarget = uploadFile.indexOf("target") - 1;
        final String substring = uploadFile.substring(0, indexOfTarget);
        final String rootDirectory = substring.concat(MappingUtils.ROOT_PATH_CATEGORY);

        return rootDirectory;
    }
}
