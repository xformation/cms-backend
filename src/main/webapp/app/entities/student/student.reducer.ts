import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudent, defaultValue } from 'app/shared/model/student.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTS: 'student/SEARCH_STUDENTS',
  FETCH_STUDENT_LIST: 'student/FETCH_STUDENT_LIST',
  FETCH_STUDENT: 'student/FETCH_STUDENT',
  CREATE_STUDENT: 'student/CREATE_STUDENT',
  UPDATE_STUDENT: 'student/UPDATE_STUDENT',
  DELETE_STUDENT: 'student/DELETE_STUDENT',
  RESET: 'student/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudent>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentState = initialState, action): StudentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTS):
    case REQUEST(ACTION_TYPES.FETCH_STUDENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENT):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENT):
    case REQUEST(ACTION_TYPES.DELETE_STUDENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTS):
    case FAILURE(ACTION_TYPES.FETCH_STUDENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENT):
    case FAILURE(ACTION_TYPES.CREATE_STUDENT):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENT):
    case FAILURE(ACTION_TYPES.DELETE_STUDENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENT):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENT):
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

const apiUrl = 'api/students';
const apiSearchUrl = 'api/_search/students';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudent> = query => ({
  type: ACTION_TYPES.SEARCH_STUDENTS,
  payload: axios.get<IStudent>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IStudent> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENT_LIST,
  payload: axios.get<IStudent>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudent> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENT,
    payload: axios.get<IStudent>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudent> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudent> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
