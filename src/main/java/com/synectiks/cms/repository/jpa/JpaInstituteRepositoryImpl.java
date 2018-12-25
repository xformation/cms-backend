package com.synectiks.cms.repository.jpa;

import com.synectiks.cms.model.Institute;
import com.synectiks.cms.repository.InstituteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
//@Profile("jpa")
@Transactional
public class JpaInstituteRepositoryImpl implements InstituteRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Institute findById(int id) {
        Query query = this.em.createQuery("SELECT institute FROM Institute institute  WHERE institute.id =:id");
        query.setParameter("id", id);
        return (Institute) query.getSingleResult();
    }

    @Override
    public void save(Institute institute) {
        if (institute.getId() == null) {
            this.em.persist(institute);
        } else {
            this.em.merge(institute);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Institute> findAll() throws DataAccessException {
        Query query = this.em.createQuery("SELECT institute FROM Institute institute");
        return query.getResultList();
    }

    @Override
    public void delete(Institute institute) throws DataAccessException {
        this.em.remove(this.em.contains(institute) ? institute : this.em.merge(institute));
    }
}
