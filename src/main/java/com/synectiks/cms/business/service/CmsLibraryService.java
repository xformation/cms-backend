package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CmsLibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public List<CmsLibrary> searchLib(String bookTitle, String author, Long batchId, Long subjectId) throws Exception {
        Library lib = new Library();
        Batch batch = new Batch();
        batch.setId(batchId);

        if (!CommonUtil.isNullOrEmpty(bookTitle)) {
            lib.setBookTitle(bookTitle);
        }
        if(!CommonUtil.isNullOrEmpty(author)){
            lib.setAuthor(author);
        }
        if (subjectId != null && subjectId >= 0) {
            Subject subject = new Subject();
            subject.setId(subjectId);
            lib.setSubject(subject);
        }
        Example<Library> example =Example.of(lib);
        List<Library>list=this.libraryRepository.findAll(example);
        List<CmsLibrary>ls =new ArrayList<>();
        return ls;
    }
}


