package com.synectiks.cms.filter.library;

import com.synectiks.cms.business.service.CmsLibraryService;
import com.synectiks.cms.domain.Book;
import com.synectiks.cms.domain.CmsLibraryVo;
import com.synectiks.cms.domain.Library;
import com.synectiks.cms.filter.Book.BookListFilterInput;
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

    public List<CmsLibraryVo> searchLib(String bookTitle, Long departmentId, Long libraryId) throws Exception {
        return cmsLibraryService.searchLib(bookTitle, departmentId,libraryId);
    }

    public List<CmsLibraryVo> searchLib(LibraryFilterInput filter) throws Exception {
        return cmsLibraryService.searchLib(filter);
    }
}
