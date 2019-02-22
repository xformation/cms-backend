package com.synectiks.cms.repository;

import com.synectiks.cms.domain.DueDate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DueDate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DueDateRepository extends JpaRepository<DueDate, Long> {

}
