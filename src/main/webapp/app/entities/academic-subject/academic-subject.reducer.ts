import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAcademicSubject, defaultValue } from 'app/shared/model/academic-subject.model';

export const ACTION_TYPES = {
  SEARCH_ACADEMICSUBJECTS: 'academicSubject/SEARCH_ACADEMICSUBJECTS',
  FETCH_ACADEMICSUBJECT_LIST: 'academicSubject/FETCH_ACADEMICSUBJECT_LIST',
  FETCH_ACADEMICSUBJECT: 'academicSubject/FETCH_ACADEMICSUBJECT',
  CREATE_ACADEMICSUBJECT: 'academicSubject/CREATE_ACADEMICSUBJECT',
  UPDATE_ACADEMICSUBJECT: 'academicSubject/UPDATE_ACADEMICSUBJECT',
  DELETE_ACADEMICSUBJECT: 'academicSubject/DELETE_ACADEMICSUBJECT',
  RESET: 'academicSubject/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAcademicSubject>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AcademicSubjectState = Readonly<typeof initialState>;

// Reducer

export default (state: AcademicSubjectState = initialState, action): AcademicSubjectState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ACADEMICSUBJECTS):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICSUBJECT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICSUBJECT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACADEMICSUBJECT):
    case REQUEST(ACTION_TYPES.UPDATE_ACADEMICSUBJECT):
    case REQUEST(ACTION_TYPES.DELETE_ACADEMICSUBJECT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ACADEMICSUBJECTS):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICSUBJECT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICSUBJECT):
    case FAILURE(ACTION_TYPES.CREATE_ACADEMICSUBJECT):
    case FAILURE(ACTION_TYPES.UPDATE_ACADEMICSUBJECT):
    case FAILURE(ACTION_TYPES.DELETE_ACADEMICSUBJECT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ACADEMICSUBJECTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICSUBJECT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICSUBJECT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACADEMICSUBJECT):
    case SUCCESS(ACTION_TYPES.UPDATE_ACADEMICSUBJECT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACADEMICSUBJECT):
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

const apiUrl = 'api/academic-subjects';
const apiSearchUrl = 'api/_search/academic-subjects';

// Actions

export const getSearchEntities: ICrudSearchAction<IAcademicSubject> = query => ({
  type: ACTION_TYPES.SEARCH_ACADEMICSUBJECTS,
  payload: axios.get<IAcademicSubject>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAcademicSubject> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACADEMICSUBJECT_LIST,
  payload: axios.get<IAcademicSubject>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAcademicSubject> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACADEMICSUBJECT,
    payload: axios.get<IAcademicSubject>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAcademicSubject> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACADEMICSUBJECT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAcademicSubject> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACADEMICSUBJECT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAcademicSubject> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACADEMICSUBJECT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
