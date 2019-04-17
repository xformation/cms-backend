import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdminAttendance, defaultValue } from 'app/shared/model/admin-attendance.model';

export const ACTION_TYPES = {
  SEARCH_ADMINATTENDANCES: 'adminAttendance/SEARCH_ADMINATTENDANCES',
  FETCH_ADMINATTENDANCE_LIST: 'adminAttendance/FETCH_ADMINATTENDANCE_LIST',
  FETCH_ADMINATTENDANCE: 'adminAttendance/FETCH_ADMINATTENDANCE',
  CREATE_ADMINATTENDANCE: 'adminAttendance/CREATE_ADMINATTENDANCE',
  UPDATE_ADMINATTENDANCE: 'adminAttendance/UPDATE_ADMINATTENDANCE',
  DELETE_ADMINATTENDANCE: 'adminAttendance/DELETE_ADMINATTENDANCE',
  RESET: 'adminAttendance/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdminAttendance>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AdminAttendanceState = Readonly<typeof initialState>;

// Reducer

export default (state: AdminAttendanceState = initialState, action): AdminAttendanceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ADMINATTENDANCES):
    case REQUEST(ACTION_TYPES.FETCH_ADMINATTENDANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADMINATTENDANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ADMINATTENDANCE):
    case REQUEST(ACTION_TYPES.UPDATE_ADMINATTENDANCE):
    case REQUEST(ACTION_TYPES.DELETE_ADMINATTENDANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ADMINATTENDANCES):
    case FAILURE(ACTION_TYPES.FETCH_ADMINATTENDANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADMINATTENDANCE):
    case FAILURE(ACTION_TYPES.CREATE_ADMINATTENDANCE):
    case FAILURE(ACTION_TYPES.UPDATE_ADMINATTENDANCE):
    case FAILURE(ACTION_TYPES.DELETE_ADMINATTENDANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ADMINATTENDANCES):
    case SUCCESS(ACTION_TYPES.FETCH_ADMINATTENDANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMINATTENDANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADMINATTENDANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_ADMINATTENDANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADMINATTENDANCE):
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

const apiUrl = 'api/admin-attendances';
const apiSearchUrl = 'api/_search/admin-attendances';

// Actions

export const getSearchEntities: ICrudSearchAction<IAdminAttendance> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_ADMINATTENDANCES,
  payload: axios.get<IAdminAttendance>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IAdminAttendance> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADMINATTENDANCE_LIST,
  payload: axios.get<IAdminAttendance>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAdminAttendance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADMINATTENDANCE,
    payload: axios.get<IAdminAttendance>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAdminAttendance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADMINATTENDANCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdminAttendance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADMINATTENDANCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdminAttendance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADMINATTENDANCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
