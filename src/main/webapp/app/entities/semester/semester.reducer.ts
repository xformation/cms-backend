import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISemester, defaultValue } from 'app/shared/model/semester.model';

export const ACTION_TYPES = {
  SEARCH_SEMESTERS: 'semester/SEARCH_SEMESTERS',
  FETCH_SEMESTER_LIST: 'semester/FETCH_SEMESTER_LIST',
  FETCH_SEMESTER: 'semester/FETCH_SEMESTER',
  CREATE_SEMESTER: 'semester/CREATE_SEMESTER',
  UPDATE_SEMESTER: 'semester/UPDATE_SEMESTER',
  DELETE_SEMESTER: 'semester/DELETE_SEMESTER',
  RESET: 'semester/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISemester>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SemesterState = Readonly<typeof initialState>;

// Reducer

export default (state: SemesterState = initialState, action): SemesterState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_SEMESTERS):
    case REQUEST(ACTION_TYPES.FETCH_SEMESTER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SEMESTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SEMESTER):
    case REQUEST(ACTION_TYPES.UPDATE_SEMESTER):
    case REQUEST(ACTION_TYPES.DELETE_SEMESTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_SEMESTERS):
    case FAILURE(ACTION_TYPES.FETCH_SEMESTER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SEMESTER):
    case FAILURE(ACTION_TYPES.CREATE_SEMESTER):
    case FAILURE(ACTION_TYPES.UPDATE_SEMESTER):
    case FAILURE(ACTION_TYPES.DELETE_SEMESTER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_SEMESTERS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEMESTER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEMESTER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SEMESTER):
    case SUCCESS(ACTION_TYPES.UPDATE_SEMESTER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SEMESTER):
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

const apiUrl = 'api/semesters';
const apiSearchUrl = 'api/_search/semesters';

// Actions

export const getSearchEntities: ICrudSearchAction<ISemester> = query => ({
  type: ACTION_TYPES.SEARCH_SEMESTERS,
  payload: axios.get<ISemester>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ISemester> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SEMESTER_LIST,
  payload: axios.get<ISemester>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISemester> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SEMESTER,
    payload: axios.get<ISemester>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISemester> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SEMESTER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISemester> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SEMESTER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISemester> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SEMESTER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
