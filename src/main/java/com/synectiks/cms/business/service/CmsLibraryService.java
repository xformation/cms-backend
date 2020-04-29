package com.synectiks.cms.business.service;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.RouteFrequency;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
//import com.synectiks.cms.filter.library.LibraryFilterInput;
import com.synectiks.cms.filter.library.LibraryFilterInput;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.graphql.types.Library.AddLibraryInput;
import com.synectiks.cms.graphql.types.TransportRoute.AddTransportRouteInput;
import com.synectiks.cms.repository.LibraryRepository;
import com.synectiks.cms.service.util.CommonUtil;
import org.glassfish.jersey.internal.inject.AliasBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CmsLibraryService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private CommonService commonService;

    public List<CmsLibraryVo> searchLib(String bookTitle,Long departmentId,Long libraryId) {
        Library lb = new Library();

        if (bookTitle != null) {
            lb.setBookTitle(bookTitle);
        }
//
//        if (author != null) {
//            book.setAuthor(author);
//        }
//
        if (departmentId != null) {
            lb.setId(departmentId);
        }
        if (libraryId != null) {
            lb.setId(libraryId);
        }
        Example<Library> example = Example.of(lb);
        List<Library> list = this.libraryRepository.findAll(example);
        List<CmsLibraryVo> ls = new ArrayList<>();
        return ls;
    }

    public List<CmsLibraryVo> searchLib(LibraryFilterInput filter) {
        Library lb = new Library();
        if (!CommonUtil.isNullOrEmpty(filter.getLibraryId())) {
            if (lb != null) {
                lb.setId(Long.valueOf(filter.getLibraryId()));
            }
        }
        if (!CommonUtil.isNullOrEmpty(filter.getDepartmentId())) {
            lb.setDepartmentId(Long.parseLong(filter.getDepartmentId()));
        }
        Example<Library> example = Example.of(lb);
        List<Library> list = this.libraryRepository.findAll(example);
        List<CmsLibraryVo> ls = new ArrayList<>();
        return ls;
    }
    public List<CmsLibraryVo> getLibraryList(){
        List<Library> list = this.libraryRepository.findAll();
        List<CmsLibraryVo> ls = changeLibraryToCmsLibraryList(list);
        logger.debug("Library list : "+list);
        return ls;
    }

    public Library getLibrary(Long id){
        Optional<Library> lb = this.libraryRepository.findById(id);
        if(lb.isPresent()) {
            logger.debug("Library object found for given id : "+id+". Library object : "+lb.get());
            return lb.get();
        }
        logger.debug("Library object not found for the given id. "+id+". Returning null");
        return null;
    }

    public List<CmsLibraryVo> getCmsLibraryList(){
        List<Library> list = this.libraryRepository.findAll();
        List<CmsLibraryVo> ls = changeLibraryToCmsLibraryList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        logger.debug("CmsLibrary list : "+list);
        return ls;
    }

    public CmsLibraryVo getCmsLibrary(Long id){
        Optional<Library> lb = this.libraryRepository.findById(id);
        if(lb.isPresent()) {
            CmsLibraryVo vo = CommonUtil.createCopyProperties(lb.get(), CmsLibraryVo.class);
            logger.debug("CmsLibrary for given id : "+id+". CmsLibrary object : "+vo);
            return vo;
        }
        logger.debug("Library object not found for the given id. "+id+". Returning null object");
        return null;
    }

    private List<CmsLibraryVo> changeLibraryToCmsLibraryList(List<Library> list) {
        List<CmsLibraryVo> ls = new ArrayList<>();
        for (Library lb : list) {
            CmsLibraryVo vo = CommonUtil.createCopyProperties(lb, CmsLibraryVo.class);
            ls.add(vo);
        }
        return ls;
    }

    public CmsLibraryVo addLibrary(AddLibraryInput input) {
        logger.info("Saving library");
        CmsLibraryVo vo = null;
        try {
            Library library = null;
            if (input.getId() == null) {
                logger.debug("Adding new Library");
                library = CommonUtil.createCopyProperties(input, Library.class);
            } else {
                logger.debug("Updating existing Library");
                library = this.libraryRepository.findById(input.getId()).get();
            }
            library.setRowName(input.getRowName());
            library.setBookTitle(input.getBookTitle());
            library.setBookNo(input.getBookNo());
            library.setAuthor(input.getAuthor());
            library.setNoOfCopies(input.getNoOfCopies());
            library.setUniqueNo(input.getUniqueNo());
            library.setDepartmentId(input.getDepartmentId());
            library = this.libraryRepository.save(library);
            vo = CommonUtil.createCopyProperties(library, CmsLibraryVo.class);
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("library is added successfully");
                logger.debug("library is added successfully");
            } else {
                vo.setExitDescription("library is updated successfully");
                logger.debug("library is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsLibraryVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, library data not be saved");
            logger.error("Library save failed. Exception : ", e);
        }
        logger.info("Library saved successfully");
        List ls = getLibraryList();
        vo.setDataList(ls);
        return vo;
    }
}
