package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AddNewBook;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AddNewBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddNewBookRepository extends JpaRepository<AddNewBook, Long> {

}
