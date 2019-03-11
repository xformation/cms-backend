import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdmissionApplication, defaultValue } from 'app/shared/model/admission-application.model';

export const ACTION_TYPES = {
  SEARCH_ADMISSIONAPPLICATIONS: 'admissionApplication/SEARCH_ADMISSIONAPPLICATIONS',
  FETCH_ADMISSIONAPPLICATION_LIST: 'admissionApplication/FETCH_ADMISSIONAPPLICATION_LIST',
  FETCH_ADMISSIONAPPLICATION: 'admissionApplication/FETCH_ADMISSIONAPPLICATION',
  CREATE_ADMISSIONAPPLICATION: 'admissionApplication/CREATE_ADMISSIONAPPLICATION',
  UPDATE_ADMISSIONAPPLICATION: 'admissionApplication/UPDATE_ADMISSIONAPPLICATION',
  DELETE_ADMISSIONAPPLICATION: 'admissionApplication/DELETE_ADMISSIONAPPLICATION',
  RESET: 'admissionApplication/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdmissionApplication>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AdmissionApplicationState = Readonly<typeof initialState>;

// Reducer

export default (state: AdmissionApplicationState = initialState, action): AdmissionApplicationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ADMISSIONAPPLICATIONS):
    case REQUEST(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ADMISSIONAPPLICATION):
    case REQUEST(ACTION_TYPES.UPDATE_ADMISSIONAPPLICATION):
    case REQUEST(ACTION_TYPES.DELETE_ADMISSIONAPPLICATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ADMISSIONAPPLICATIONS):
    case FAILURE(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION):
    case FAILURE(ACTION_TYPES.CREATE_ADMISSIONAPPLICATION):
    case FAILURE(ACTION_TYPES.UPDATE_ADMISSIONAPPLICATION):
    case FAILURE(ACTION_TYPES.DELETE_ADMISSIONAPPLICATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ADMISSIONAPPLICATIONS):
    case SUCCESS(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMISSIONAPPLICATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADMISSIONAPPLICATION):
    case SUCCESS(ACTION_TYPES.UPDATE_ADMISSIONAPPLICATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADMISSIONAPPLICATION):
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

const apiUrl = 'api/admission-applications';
const apiSearchUrl = 'api/_search/admission-applications';

// Actions

export const getSearchEntities: ICrudSearchAction<IAdmissionApplication> = query => ({
  type: ACTION_TYPES.SEARCH_ADMISSIONAPPLICATIONS,
  payload: axios.get<IAdmissionApplication>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAdmissionApplication> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADMISSIONAPPLICATION_LIST,
  payload: axios.get<IAdmissionApplication>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAdmissionApplication> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADMISSIONAPPLICATION,
    payload: axios.get<IAdmissionApplication>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAdmissionApplication> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADMISSIONAPPLICATION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdmissionApplication> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADMISSIONAPPLICATION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdmissionApplication> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADMISSIONAPPLICATION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
