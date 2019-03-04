import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IReports, defaultValue } from 'app/shared/model/reports.model';

export const ACTION_TYPES = {
  SEARCH_REPORTS: 'reports/SEARCH_REPORTS',
  FETCH_REPORTS_LIST: 'reports/FETCH_REPORTS_LIST',
  FETCH_REPORTS: 'reports/FETCH_REPORTS',
  CREATE_REPORTS: 'reports/CREATE_REPORTS',
  UPDATE_REPORTS: 'reports/UPDATE_REPORTS',
  DELETE_REPORTS: 'reports/DELETE_REPORTS',
  RESET: 'reports/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IReports>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ReportsState = Readonly<typeof initialState>;

// Reducer

export default (state: ReportsState = initialState, action): ReportsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_REPORTS):
    case REQUEST(ACTION_TYPES.FETCH_REPORTS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_REPORTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_REPORTS):
    case REQUEST(ACTION_TYPES.UPDATE_REPORTS):
    case REQUEST(ACTION_TYPES.DELETE_REPORTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_REPORTS):
    case FAILURE(ACTION_TYPES.FETCH_REPORTS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_REPORTS):
    case FAILURE(ACTION_TYPES.CREATE_REPORTS):
    case FAILURE(ACTION_TYPES.UPDATE_REPORTS):
    case FAILURE(ACTION_TYPES.DELETE_REPORTS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_REPORTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPORTS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_REPORTS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_REPORTS):
    case SUCCESS(ACTION_TYPES.UPDATE_REPORTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_REPORTS):
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

const apiUrl = 'api/reports';
const apiSearchUrl = 'api/_search/reports';

// Actions

export const getSearchEntities: ICrudSearchAction<IReports> = query => ({
  type: ACTION_TYPES.SEARCH_REPORTS,
  payload: axios.get<IReports>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IReports> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_REPORTS_LIST,
  payload: axios.get<IReports>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IReports> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_REPORTS,
    payload: axios.get<IReports>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IReports> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_REPORTS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IReports> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_REPORTS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IReports> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_REPORTS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
