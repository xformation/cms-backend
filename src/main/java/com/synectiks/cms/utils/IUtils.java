/**
 * 
 */
package com.synectiks.cms.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Rajesh Upadhyay
 */
public interface IUtils {

	Logger logger = LoggerFactory.getLogger(IUtils.class);
	String JSON_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";
	ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.setVisibility(PropertyAccessor.ALL, Visibility.NONE)
			.setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
			.setDateFormat(new SimpleDateFormat(JSON_DATE_FORMAT))
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
			.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
	String PRM_EV_TYPE = "eventType";
	String PRM_CLASS = "cls";
	String PRM_ENTITY = "entity";
	String KEY_INDX_EVENT_FIRE = "search.fire.event.url";
	String URL_INDX_EVENT_FIRE = "http://localhost:8092/seach/fireEvent";
	// String URL_INDX_EVENT_FIRE = "http://localhost:8092/api/v1/auth/seach/fireEvent";
	String PRM_MSG = "msg";
	String PRM_TOPIC = "topic";
	String KEY_KAFKA_CONFIG = "kafka.url";
	String KEY_KAFKA_TOPIC = "kafka.topic";
	String URL_KAFKA_URL = "http://localhost:8190/kafka/send";

	/**
	 * Check if an object is null
	 * @param object
	 * @return
	 */
	static boolean isNull(Object object) {
		return null == object;
	}

	/**
	 * Method check if object is null or empty
	 * @param object
	 * @return
	 */
	static boolean isNullOrEmpty(String object) {
		if (!isNull(object) && !object.trim().isEmpty()) {
			return false;
		}
		return true;
	}

	static String getValueByKey(Environment env, String key, String defVal) {
		if (isNull(env) || isNullOrEmpty(key)) {
			logger.error(env + " or " + key + " should not be null.");
			return null;
		}
		String val = env.getProperty(key);
		if (isNull(val)) {
			val = defVal;
		}
		return val;
	}

	/**
	 * Internal method to call search api for handing indexing operation
	 * @param url
	 * @param event
	 * @return
	 * @throws Exception
	 */
	static String searchAPIRequest(RestTemplate rest, String url, ESEvent event)
			throws Exception {
		String res = null;
		String cls = (event.getEntity() != null ? event.getEntity().getClass().getName()
				: "");
		logger.info("Entity class: " + cls);
		Map<String, Object> params = getRestParamMap(PRM_EV_TYPE,
				event.getEventType().name(), PRM_CLASS, cls, PRM_ENTITY,
				getStringFromValue(event.getEntity()));
		logger.info("Request: " + params);
		res = sendPostRestRequest(rest, url, null, String.class, params,
				MediaType.APPLICATION_FORM_URLENCODED);
		logger.info("Indexing response: " + res);
		return res;
	}

	/**
	 * Method creates a map from list of key value string or objects
	 * @param <T>
	 * @param keyValPairs array of string, object, string, object
	 * @return
	 */
	@SafeVarargs
	static <T> Map<String, T> getRestParamMap(T... keyValPairs) {
		Map<String, T> map = new HashMap<>();
		if (!isNull(keyValPairs) && (keyValPairs.length % 2) == 0) {
			for (int i = 0; (i + 1) < keyValPairs.length;) {
				String key = (String) keyValPairs[i++];
				map.put(key, keyValPairs[i++]);
			}
		}
		return map;
	}

	/**
	 * Method returns string for {@code Object} type
	 * {@code String, Number, Date} or create json for other type using
	 * {@code ObjectMapper}
	 * @param val {@code Object} instance
	 * @return {@code String}
	 */
	static String getStringFromValue(Object val) {
		String res = null;
		if (!isNull(val)) {
			if (val instanceof String || val instanceof Number || val instanceof Date) {
				res = val.toString();
			} else if (val instanceof JSONArray) {
				JSONArray jarr = (JSONArray) val;
				res = "[";
				for (int i = 0; i < jarr.length(); i++) {
					res += (i > 0 ? ", " : "") + getStringFromValue(jarr.opt(i));
				}
				res += "]";
			} else if (val instanceof JSONObject) {
				res = val.toString();
			} else if (val instanceof Map) {
				Map<?, ?> mp = (Map<?, ?>) val;
				JSONObject json = new JSONObject();
				for (Entry<?, ?> entry : mp.entrySet()) {
					try {
						json.put(entry.getKey().toString(),
								getStringFromValue(entry.getValue()));
					} catch (JSONException e) {
						logger.error(e.getMessage());
					}
				}
				res = json.toString();
			} else if (val instanceof List) {
				res = getStringFromValue(new JSONArray((List<?>) val));
			} else {
				try {
					res = OBJECT_MAPPER.writeValueAsString(val);
				} catch (Exception e) {
					// ignore it
				}
			}
		}
		return res;
	}

	/**
	 * Method to send rest request using RestTemplate
	 * @param template
	 * @param url
	 * @param request
	 * @param cls
	 * @param params
	 * @param cntType
	 * @return
	 */
	static <T> T sendPostRestRequest(RestTemplate template, String url, Object request,
			Class<T> cls, Map<String, Object> params, MediaType cntType) {
		T res = null;
		if (isNullOrEmpty(url)) {
			logger.error("Url is null or empty", new NullPointerException("Url: " + url));
			return res;
		}
		logger.info("Url: " + url);
		if (!isNull(params) && !params.isEmpty()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(cntType);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			params.entrySet().forEach(entry -> {
				map.add(entry.getKey(), entry.getValue());
			});
			ResponseEntity<T> result = template.postForEntity(url,
					new HttpEntity<>(map, headers), cls);
			res = result.getBody();
		} else {
			res = template.postForObject(url, request, cls);
		}
		logger.info("Response: " + res);
		return res;
	}

	/**
	 * Method to send rest request using RestTemplate
	 * @param rest
	 * @param url
	 * @param params
	 * @param cls
	 * @return
	 */
	static <T> T sendGetRestRequest(RestTemplate template, String url,
			Map<String, Object> params, Class<T> cls) {
		if (isNullOrEmpty(url)) {
			logger.error("Url is null or empty", new NullPointerException("Url: " + url));
			return null;
		}
		url = addParamsInUrl(url, params);
		logger.info("Rest Call: " + url);
		T res = template.getForObject(url, cls);
		logger.info("Response: " + res);
		return res;
	}

	/**
	 * Method add params into url for get request by using {@code URIBuilder}
	 * @param url fully qualified url
	 * @param params {@code Map<String, Object>}
	 * @return
	 */
	static String addParamsInUrl(String url, Map<String, Object> params) {
		String res = url;
		if (!isNull(params) && params.size() > 0) {
			try {
				List<NameValuePair> nvps = getNameValuePairList(params);
				res = new URIBuilder(url).addParameters(nvps).build().toString();
			} catch (Throwable e) {
				// ignore it
			}
		}
		return res;
	}

	/**
	 * Method returns {@code NameValuePair} list from params map. Map values
	 * must be in serialized form to convert into json
	 * @param params {@code Map<String, Object>}
	 * @return empty list if params are null or empty
	 * @throws Exception
	 */
	static List<NameValuePair> getNameValuePairList(Map<String, Object> params)
			throws Exception {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (!isNull(params) && !params.isEmpty()) {
			try {
				params.keySet().forEach((key) -> {
					String val = getStringFromValue(params.get(key));
					if (!isNullOrEmpty(val)) {
						nvps.add(new BasicNameValuePair(key, val));
					}
				});
			} catch (Throwable e) {
				throw new Exception(e.getMessage(), e);
			}
		}
		return nvps;
	}
}
