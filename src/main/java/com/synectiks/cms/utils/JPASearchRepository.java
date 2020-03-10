/**
 *
 */
package com.synectiks.cms.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Interface to extend JPA functionlity to extend
 * and use kafka queue to index into elastic search
 * @author Rajesh Upadhyay
 */
@NoRepositoryBean
public interface JPASearchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}
