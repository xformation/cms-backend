import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdmissionEnquiry, defaultValue } from 'app/shared/model/admission-enquiry.model';

export const ACTION_TYPES = {
  SEARCH_ADMISSIONENQUIRIES: 'admissionEnquiry/SEARCH_ADMISSIONENQUIRIES',
  FETCH_ADMISSIONENQUIRY_LIST: 'admissionEnquiry/FETCH_ADMISSIONENQUIRY_LIST',
  FETCH_ADMISSIONENQUIRY: 'admissionEnquiry/FETCH_ADMISSIONENQUIRY',
  CREATE_ADMISSIONENQUIRY: 'admissionEnquiry/CREATE_ADMISSIONENQUIRY',
  UPDATE_ADMISSIONENQUIRY: 'admissionEnquiry/UPDATE_ADMISSIONENQUIRY',
  DELETE_ADMISSIONENQUIRY: 'admissionEnquiry/DELETE_ADMISSIONENQUIRY',
  RESET: 'admissionEnquiry/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdmissionEnquiry>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AdmissionEnquiryState = Readonly<typeof initialState>;

// Reducer

export default (state: AdmissionEnquiryState = initialState, action): AdmissionEnquiryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ADMISSIONENQUIRIES):
    case REQUEST(ACTION_TYPES.FETCH_ADMISSIONENQUIRY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADMISSIONENQUIRY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ADMISSIONENQUIRY):
    case REQUEST(ACTION_TYPES.UPDATE_ADMISSIONENQUIRY):
    case REQUEST(ACTION_TYPES.DELETE_ADMISSIONENQUIRY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ADMISSIONENQUIRIES):
    case FAILURE(ACTION_TYPES.FETCH_ADMISSIONENQUIRY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADMISSIONENQUIRY):
    case FAILURE(ACTION_TYPES.CREATE_ADMISSIONENQUIRY):
    case FAILURE(ACTION_TYPES.UPDATE_ADMISSIONENQUIRY):
    case FAILURE(ACTION_TYPES.DELETE_ADMISSIONENQUIRY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ADMISSIONENQUIRIES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMISSIONENQUIRY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMISSIONENQUIRY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADMISSIONENQUIRY):
    case SUCCESS(ACTION_TYPES.UPDATE_ADMISSIONENQUIRY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADMISSIONENQUIRY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/admission-enquiries';
const apiSearchUrl = 'api/_search/admission-enquiries';

// Actions

export const getSearchEntities: ICrudSearchAction<IAdmissionEnquiry> = query => ({
  type: ACTION_TYPES.SEARCH_ADMISSIONENQUIRIES,
  payload: axios.get<IAdmissionEnquiry>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAdmissionEnquiry> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADMISSIONENQUIRY_LIST,
  payload: axios.get<IAdmissionEnquiry>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAdmissionEnquiry> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADMISSIONENQUIRY,
    payload: axios.get<IAdmissionEnquiry>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAdmissionEnquiry> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADMISSIONENQUIRY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdmissionEnquiry> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADMISSIONENQUIRY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdmissionEnquiry> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADMISSIONENQUIRY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
