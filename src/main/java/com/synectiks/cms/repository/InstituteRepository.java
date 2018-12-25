package com.synectiks.cms.repository;

import com.synectiks.cms.model.Institute;
import org.springframework.dao.DataAccessException;


import java.util.Collection;

public interface InstituteRepository {
    Institute findById(int id) throws DataAccessException;

    void save(Institute institute) throws DataAccessException;

    Collection<Institute> findAll()  throws DataAccessException;

    void delete(Institute institute) throws DataAccessException;
}
