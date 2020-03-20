/**
 * 
 */
package com.synectiks.cms.utils;

import java.io.Serializable;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rajesh
 */
public class ESEvent implements Serializable {

	private static final long serialVersionUID = -2522663361842705303L;

	private static final Logger logger = LoggerFactory.getLogger(ESEvent.class);

	public static enum EventType {
		/** Event to handle index create/insert operation */
		CREATE,
		/** Event to handle index update operation */
		UPDATE,
		/** Event to handle index delete operation */
		DELETE;
	}

	private EventType eventType;
	private Object entity;
	private String cls;

	public ESEvent() {
		
	}

	public ESEvent(EventType type, Object entity) {
		this.eventType = type;
		this.entity = entity;
		this.cls = "com.synectiks.cms.entities."+entity.getClass().getSimpleName();
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Class<? extends IESEntity> entity) {
		this.entity = entity;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String clz) {
		this.cls = clz;
	}

	/**
	 * Method check if event type is for save or update entity.
	 * @param type
	 * @return <code>true</code> if event type is {@link EventType#CREATE} or
	 * {@link EventType#UPDATE}
	 */
	public boolean isSaveOrUpdate(EventType type) {
		if (EventType.CREATE == type || EventType.UPDATE == type) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check if event is for {@link EventType#DELETE}
	 * @param type
	 * @return
	 */
	public boolean isDelete(EventType type) {
		return (EventType.DELETE == type);
	}

	public static ESEventBuilder builder() {
		return new ESEventBuilder();
	}

	public static ESEventBuilder builder(
			EventType type, Object entity) {
		Object ent = null;
		if (Objects.isNull(entity) || !(entity instanceof IESEntity)) {
			logger.error("Entity is null or not a valid entity "
					+ "(Subclass of IESEntity) to index.");
		} else {
			ent = entity;
		}
		return new ESEventBuilder(type, ent);
	}

	@Override
	public String toString() {
		try {
			return IUtils.OBJECT_MAPPER.writeValueAsString(this);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @author Rajesh Upadhyay
	 */
	public static class ESEventBuilder {

		private EventType type;
		private Object entity;

		public ESEventBuilder() {
		}

		public ESEventBuilder(
				EventType type, Object entity) {
			this.type = type;
			this.entity = entity;
		}

		public ESEventBuilder withType(EventType type) {
			this.type = type;
			return this;
		}

		public ESEventBuilder withEntity(Class<? extends IESEntity> event) {
			this.entity = event;
			return this;
		}

		public ESEvent build() {
			return new ESEvent(type, entity);
		}
	}
}
