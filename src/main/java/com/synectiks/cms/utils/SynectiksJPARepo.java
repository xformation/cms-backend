/**
 * 
 */
package com.synectiks.cms.utils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.web.client.RestTemplate;

import com.synectiks.cms.CmsApp;
import com.synectiks.commons.entities.search.ESEvent;
import com.synectiks.commons.entities.search.ESEvent.EventType;
import com.synectiks.commons.interfaces.IESEntity;

/**
 * @author Rajesh Upadhyay
 */
public class SynectiksJPARepo<T, ID extends Serializable>
		extends SimpleJpaRepository<T, ID> implements JPASearchRepository<T, ID> {

	private static final Logger logger = LoggerFactory.getLogger(SynectiksJPARepo.class);

	private Environment env;
	private RestTemplate rest;

	public SynectiksJPARepo(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

	public SynectiksJPARepo(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public void deleteById(ID id) {
		super.deleteById(id);
		T entity = super.getOne(id);
		fireEvent(EventType.DELETE, entity);
	}

	@Override
	public void delete(T entity) {
		super.delete(entity);
		fireEvent(EventType.DELETE, entity);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		super.deleteAll(entities);
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		super.deleteInBatch(entities);
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteAll() {
		List<T> entities = super.findAll();
		super.deleteAll();
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public void deleteAllInBatch() {
		List<T> entities = super.findAll();
		super.deleteAllInBatch();
		if (!IUtils.isNull(entities)) {
			entities.forEach(entity -> {
				fireEvent(EventType.DELETE, entity);
			});
		}
	}

	@Override
	public <S extends T> S save(S entity) {
		S ent = super.save(entity);
		logger.debug("calling save using kafka");
		fireEvent(EventType.CREATE, ent);
		return ent;
	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		logger.debug("calling saveAndFlush using kafka");
		S ent = super.saveAndFlush(entity);
		fireEvent(EventType.CREATE, ent);
		return ent;
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		List<S> lst = super.saveAll(entities);
		logger.debug("calling saveall using kafka");
		if (!IUtils.isNull(lst)) {
			lst.forEach(entity -> {
				fireEvent(EventType.CREATE, entity);
			});
		}
		return lst;
	}

	private void fireEvent(EventType type, T entity) {
        if (IUtils.isNull(env)) {
        	env = CmsApp.getBean(Environment.class);
        }
        if (IUtils.isNull(rest)) {
        	rest = CmsApp.getBean(RestTemplate.class);
        }
		logger.info(type + ": " + IUtils.getStringFromValue(entity));
		if (!IUtils.isNull(entity) && entity instanceof IESEntity) {
			ESEvent event = ESEvent.builder(type, entity).build();
			logger.info("msg Str: " + IUtils.getStringFromValue(event));
			String res = null;
			try {
				res = IUtils.sendGetRestRequest(rest, IUtils.getValueByKey(
						env, IUtils.KEY_KAFKA_CONFIG, IUtils.URL_KAFKA_URL),
						IUtils.getRestParamMap(IUtils.PRM_TOPIC,
								IUtils.getValueByKey(env, IUtils.KEY_KAFKA_TOPIC, "cms"),
								IUtils.PRM_MSG,
								IUtils.getStringFromValue(event)), String.class);
			} catch(Exception ex) {
				logger.error(ex.getMessage(), ex);
				res = null;
			}
			if (IUtils.isNullOrEmpty(res)) {
				// TODO: We failed to record operation, store it for processing later.
			}
		} else {
			logger.error("Entity should not be null");
		}
	}

}
