package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Term;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Term entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

}
