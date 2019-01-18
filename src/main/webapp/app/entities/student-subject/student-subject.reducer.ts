import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudentSubject, defaultValue } from 'app/shared/model/student-subject.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTSUBJECTS: 'studentSubject/SEARCH_STUDENTSUBJECTS',
  FETCH_STUDENTSUBJECT_LIST: 'studentSubject/FETCH_STUDENTSUBJECT_LIST',
  FETCH_STUDENTSUBJECT: 'studentSubject/FETCH_STUDENTSUBJECT',
  CREATE_STUDENTSUBJECT: 'studentSubject/CREATE_STUDENTSUBJECT',
  UPDATE_STUDENTSUBJECT: 'studentSubject/UPDATE_STUDENTSUBJECT',
  DELETE_STUDENTSUBJECT: 'studentSubject/DELETE_STUDENTSUBJECT',
  RESET: 'studentSubject/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentSubject>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentSubjectState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentSubjectState = initialState, action): StudentSubjectState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTSUBJECTS):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTSUBJECT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTSUBJECT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTSUBJECT):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTSUBJECT):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTSUBJECT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTSUBJECTS):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTSUBJECT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTSUBJECT):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTSUBJECT):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTSUBJECT):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTSUBJECT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTSUBJECTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTSUBJECT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTSUBJECT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTSUBJECT):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTSUBJECT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTSUBJECT):
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

const apiUrl = 'api/student-subjects';
const apiSearchUrl = 'api/_search/student-subjects';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentSubject> = query => ({
  type: ACTION_TYPES.SEARCH_STUDENTSUBJECTS,
  payload: axios.get<IStudentSubject>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IStudentSubject> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTSUBJECT_LIST,
  payload: axios.get<IStudentSubject>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentSubject> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTSUBJECT,
    payload: axios.get<IStudentSubject>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentSubject> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTSUBJECT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentSubject> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTSUBJECT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentSubject> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTSUBJECT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
