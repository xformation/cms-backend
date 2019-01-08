import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAcademicYear, defaultValue } from 'app/shared/model/academic-year.model';

export const ACTION_TYPES = {
  SEARCH_ACADEMICYEARS: 'academicYear/SEARCH_ACADEMICYEARS',
  FETCH_ACADEMICYEAR_LIST: 'academicYear/FETCH_ACADEMICYEAR_LIST',
  FETCH_ACADEMICYEAR: 'academicYear/FETCH_ACADEMICYEAR',
  CREATE_ACADEMICYEAR: 'academicYear/CREATE_ACADEMICYEAR',
  UPDATE_ACADEMICYEAR: 'academicYear/UPDATE_ACADEMICYEAR',
  DELETE_ACADEMICYEAR: 'academicYear/DELETE_ACADEMICYEAR',
  RESET: 'academicYear/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAcademicYear>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AcademicYearState = Readonly<typeof initialState>;

// Reducer

export default (state: AcademicYearState = initialState, action): AcademicYearState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ACADEMICYEARS):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICYEAR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICYEAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACADEMICYEAR):
    case REQUEST(ACTION_TYPES.UPDATE_ACADEMICYEAR):
    case REQUEST(ACTION_TYPES.DELETE_ACADEMICYEAR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ACADEMICYEARS):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICYEAR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICYEAR):
    case FAILURE(ACTION_TYPES.CREATE_ACADEMICYEAR):
    case FAILURE(ACTION_TYPES.UPDATE_ACADEMICYEAR):
    case FAILURE(ACTION_TYPES.DELETE_ACADEMICYEAR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ACADEMICYEARS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICYEAR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICYEAR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACADEMICYEAR):
    case SUCCESS(ACTION_TYPES.UPDATE_ACADEMICYEAR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACADEMICYEAR):
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

const apiUrl = 'api/academic-years';
const apiSearchUrl = 'api/_search/academic-years';

// Actions

export const getSearchEntities: ICrudSearchAction<IAcademicYear> = query => ({
  type: ACTION_TYPES.SEARCH_ACADEMICYEARS,
  payload: axios.get<IAcademicYear>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAcademicYear> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACADEMICYEAR_LIST,
  payload: axios.get<IAcademicYear>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAcademicYear> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACADEMICYEAR,
    payload: axios.get<IAcademicYear>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAcademicYear> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACADEMICYEAR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAcademicYear> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACADEMICYEAR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAcademicYear> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACADEMICYEAR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
