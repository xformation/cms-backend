import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITeacher, defaultValue } from 'app/shared/model/teacher.model';

export const ACTION_TYPES = {
  SEARCH_TEACHERS: 'teacher/SEARCH_TEACHERS',
  FETCH_TEACHER_LIST: 'teacher/FETCH_TEACHER_LIST',
  FETCH_TEACHER: 'teacher/FETCH_TEACHER',
  CREATE_TEACHER: 'teacher/CREATE_TEACHER',
  UPDATE_TEACHER: 'teacher/UPDATE_TEACHER',
  DELETE_TEACHER: 'teacher/DELETE_TEACHER',
  RESET: 'teacher/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITeacher>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TeacherState = Readonly<typeof initialState>;

// Reducer

export default (state: TeacherState = initialState, action): TeacherState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TEACHERS):
    case REQUEST(ACTION_TYPES.FETCH_TEACHER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEACHER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEACHER):
    case REQUEST(ACTION_TYPES.UPDATE_TEACHER):
    case REQUEST(ACTION_TYPES.DELETE_TEACHER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TEACHERS):
    case FAILURE(ACTION_TYPES.FETCH_TEACHER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEACHER):
    case FAILURE(ACTION_TYPES.CREATE_TEACHER):
    case FAILURE(ACTION_TYPES.UPDATE_TEACHER):
    case FAILURE(ACTION_TYPES.DELETE_TEACHER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TEACHERS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEACHER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEACHER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEACHER):
    case SUCCESS(ACTION_TYPES.UPDATE_TEACHER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEACHER):
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

const apiUrl = 'api/teachers';
const apiSearchUrl = 'api/_search/teachers';

// Actions

export const getSearchEntities: ICrudSearchAction<ITeacher> = query => ({
  type: ACTION_TYPES.SEARCH_TEACHERS,
  payload: axios.get<ITeacher>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITeacher> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TEACHER_LIST,
  payload: axios.get<ITeacher>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITeacher> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEACHER,
    payload: axios.get<ITeacher>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITeacher> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEACHER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITeacher> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEACHER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITeacher> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEACHER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
