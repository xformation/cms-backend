package com.synectiks.cms.filter.library;

import com.synectiks.cms.business.service.CmsLibraryService;
import com.synectiks.commons.entities.cms.CmsLibrary;
import com.synectiks.commons.entities.cms.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryFilterProcessor {
    private final Logger logger = LoggerFactory.getLogger(LibraryFilterProcessor.class);

    @Autowired
    private CmsLibraryService cmsLibraryService;

    public List<CmsLibrary> searchLib(String bookTitle, String author,  Long batchId, Long subjectId) throws Exception {
        return cmsLibraryService.searchLib( bookTitle,  author,  batchId,  subjectId);
    }

    public List<Library> searchBook(LibraryFilterInput filter){
        return cmsLibraryService.searchBook(filter);
    }
}
