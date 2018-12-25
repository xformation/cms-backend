import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudentYear, defaultValue } from 'app/shared/model/student-year.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTYEARS: 'studentYear/SEARCH_STUDENTYEARS',
  FETCH_STUDENTYEAR_LIST: 'studentYear/FETCH_STUDENTYEAR_LIST',
  FETCH_STUDENTYEAR: 'studentYear/FETCH_STUDENTYEAR',
  CREATE_STUDENTYEAR: 'studentYear/CREATE_STUDENTYEAR',
  UPDATE_STUDENTYEAR: 'studentYear/UPDATE_STUDENTYEAR',
  DELETE_STUDENTYEAR: 'studentYear/DELETE_STUDENTYEAR',
  RESET: 'studentYear/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentYear>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentYearState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentYearState = initialState, action): StudentYearState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTYEARS):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTYEAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTYEAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTYEAR):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTYEAR):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTYEAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTYEARS):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTYEAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTYEAR):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTYEAR):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTYEAR):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTYEAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTYEARS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTYEAR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTYEAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTYEAR):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTYEAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTYEAR):
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

const apiUrl = 'api/student-years';
const apiSearchUrl = 'api/_search/student-years';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentYear> = query => ({
  type: ACTION_TYPES.SEARCH_STUDENTYEARS,
  payload: axios.get<IStudentYear>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IStudentYear> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTYEAR_LIST,
  payload: axios.get<IStudentYear>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentYear> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTYEAR,
    payload: axios.get<IStudentYear>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentYear> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTYEAR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentYear> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTYEAR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentYear> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTYEAR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
