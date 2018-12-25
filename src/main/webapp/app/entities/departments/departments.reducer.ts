import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDepartments, defaultValue } from 'app/shared/model/departments.model';

export const ACTION_TYPES = {
  SEARCH_DEPARTMENTS: 'departments/SEARCH_DEPARTMENTS',
  FETCH_DEPARTMENTS_LIST: 'departments/FETCH_DEPARTMENTS_LIST',
  FETCH_DEPARTMENTS: 'departments/FETCH_DEPARTMENTS',
  CREATE_DEPARTMENTS: 'departments/CREATE_DEPARTMENTS',
  UPDATE_DEPARTMENTS: 'departments/UPDATE_DEPARTMENTS',
  DELETE_DEPARTMENTS: 'departments/DELETE_DEPARTMENTS',
  RESET: 'departments/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDepartments>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DepartmentsState = Readonly<typeof initialState>;

// Reducer

export default (state: DepartmentsState = initialState, action): DepartmentsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DEPARTMENTS):
    case REQUEST(ACTION_TYPES.FETCH_DEPARTMENTS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEPARTMENTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DEPARTMENTS):
    case REQUEST(ACTION_TYPES.UPDATE_DEPARTMENTS):
    case REQUEST(ACTION_TYPES.DELETE_DEPARTMENTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DEPARTMENTS):
    case FAILURE(ACTION_TYPES.FETCH_DEPARTMENTS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEPARTMENTS):
    case FAILURE(ACTION_TYPES.CREATE_DEPARTMENTS):
    case FAILURE(ACTION_TYPES.UPDATE_DEPARTMENTS):
    case FAILURE(ACTION_TYPES.DELETE_DEPARTMENTS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DEPARTMENTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEPARTMENTS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEPARTMENTS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEPARTMENTS):
    case SUCCESS(ACTION_TYPES.UPDATE_DEPARTMENTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEPARTMENTS):
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

const apiUrl = 'api/departments';
const apiSearchUrl = 'api/_search/departments';

// Actions

export const getSearchEntities: ICrudSearchAction<IDepartments> = query => ({
  type: ACTION_TYPES.SEARCH_DEPARTMENTS,
  payload: axios.get<IDepartments>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IDepartments> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEPARTMENTS_LIST,
  payload: axios.get<IDepartments>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDepartments> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEPARTMENTS,
    payload: axios.get<IDepartments>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDepartments> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEPARTMENTS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDepartments> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEPARTMENTS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDepartments> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEPARTMENTS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
