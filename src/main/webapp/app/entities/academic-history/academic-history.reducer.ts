import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAcademicHistory, defaultValue } from 'app/shared/model/academic-history.model';

export const ACTION_TYPES = {
  SEARCH_ACADEMICHISTORIES: 'academicHistory/SEARCH_ACADEMICHISTORIES',
  FETCH_ACADEMICHISTORY_LIST: 'academicHistory/FETCH_ACADEMICHISTORY_LIST',
  FETCH_ACADEMICHISTORY: 'academicHistory/FETCH_ACADEMICHISTORY',
  CREATE_ACADEMICHISTORY: 'academicHistory/CREATE_ACADEMICHISTORY',
  UPDATE_ACADEMICHISTORY: 'academicHistory/UPDATE_ACADEMICHISTORY',
  DELETE_ACADEMICHISTORY: 'academicHistory/DELETE_ACADEMICHISTORY',
  RESET: 'academicHistory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAcademicHistory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AcademicHistoryState = Readonly<typeof initialState>;

// Reducer

export default (state: AcademicHistoryState = initialState, action): AcademicHistoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ACADEMICHISTORIES):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICHISTORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACADEMICHISTORY):
    case REQUEST(ACTION_TYPES.UPDATE_ACADEMICHISTORY):
    case REQUEST(ACTION_TYPES.DELETE_ACADEMICHISTORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ACADEMICHISTORIES):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICHISTORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICHISTORY):
    case FAILURE(ACTION_TYPES.CREATE_ACADEMICHISTORY):
    case FAILURE(ACTION_TYPES.UPDATE_ACADEMICHISTORY):
    case FAILURE(ACTION_TYPES.DELETE_ACADEMICHISTORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ACADEMICHISTORIES):
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICHISTORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICHISTORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACADEMICHISTORY):
    case SUCCESS(ACTION_TYPES.UPDATE_ACADEMICHISTORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACADEMICHISTORY):
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

const apiUrl = 'api/academic-histories';
const apiSearchUrl = 'api/_search/academic-histories';

// Actions

export const getSearchEntities: ICrudSearchAction<IAcademicHistory> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_ACADEMICHISTORIES,
  payload: axios.get<IAcademicHistory>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IAcademicHistory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACADEMICHISTORY_LIST,
  payload: axios.get<IAcademicHistory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAcademicHistory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACADEMICHISTORY,
    payload: axios.get<IAcademicHistory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAcademicHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACADEMICHISTORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAcademicHistory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACADEMICHISTORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAcademicHistory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACADEMICHISTORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
