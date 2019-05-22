import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDueDate, defaultValue } from 'app/shared/model/due-date.model';

export const ACTION_TYPES = {
  SEARCH_DUEDATES: 'dueDate/SEARCH_DUEDATES',
  FETCH_DUEDATE_LIST: 'dueDate/FETCH_DUEDATE_LIST',
  FETCH_DUEDATE: 'dueDate/FETCH_DUEDATE',
  CREATE_DUEDATE: 'dueDate/CREATE_DUEDATE',
  UPDATE_DUEDATE: 'dueDate/UPDATE_DUEDATE',
  DELETE_DUEDATE: 'dueDate/DELETE_DUEDATE',
  RESET: 'dueDate/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDueDate>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DueDateState = Readonly<typeof initialState>;

// Reducer

export default (state: DueDateState = initialState, action): DueDateState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DUEDATES):
    case REQUEST(ACTION_TYPES.FETCH_DUEDATE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DUEDATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DUEDATE):
    case REQUEST(ACTION_TYPES.UPDATE_DUEDATE):
    case REQUEST(ACTION_TYPES.DELETE_DUEDATE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DUEDATES):
    case FAILURE(ACTION_TYPES.FETCH_DUEDATE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DUEDATE):
    case FAILURE(ACTION_TYPES.CREATE_DUEDATE):
    case FAILURE(ACTION_TYPES.UPDATE_DUEDATE):
    case FAILURE(ACTION_TYPES.DELETE_DUEDATE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DUEDATES):
    case SUCCESS(ACTION_TYPES.FETCH_DUEDATE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DUEDATE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DUEDATE):
    case SUCCESS(ACTION_TYPES.UPDATE_DUEDATE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DUEDATE):
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

const apiUrl = 'api/due-dates';
const apiSearchUrl = 'api/_search/due-dates';

// Actions

export const getSearchEntities: ICrudSearchAction<IDueDate> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_DUEDATES,
  payload: axios.get<IDueDate>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IDueDate> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DUEDATE_LIST,
  payload: axios.get<IDueDate>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDueDate> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DUEDATE,
    payload: axios.get<IDueDate>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDueDate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DUEDATE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDueDate> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DUEDATE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDueDate> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DUEDATE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
