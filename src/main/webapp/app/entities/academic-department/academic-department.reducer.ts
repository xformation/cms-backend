import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAcademicDepartment, defaultValue } from 'app/shared/model/academic-department.model';

export const ACTION_TYPES = {
  SEARCH_ACADEMICDEPARTMENTS: 'academicDepartment/SEARCH_ACADEMICDEPARTMENTS',
  FETCH_ACADEMICDEPARTMENT_LIST: 'academicDepartment/FETCH_ACADEMICDEPARTMENT_LIST',
  FETCH_ACADEMICDEPARTMENT: 'academicDepartment/FETCH_ACADEMICDEPARTMENT',
  CREATE_ACADEMICDEPARTMENT: 'academicDepartment/CREATE_ACADEMICDEPARTMENT',
  UPDATE_ACADEMICDEPARTMENT: 'academicDepartment/UPDATE_ACADEMICDEPARTMENT',
  DELETE_ACADEMICDEPARTMENT: 'academicDepartment/DELETE_ACADEMICDEPARTMENT',
  RESET: 'academicDepartment/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAcademicDepartment>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AcademicDepartmentState = Readonly<typeof initialState>;

// Reducer

export default (state: AcademicDepartmentState = initialState, action): AcademicDepartmentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ACADEMICDEPARTMENTS):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACADEMICDEPARTMENT):
    case REQUEST(ACTION_TYPES.UPDATE_ACADEMICDEPARTMENT):
    case REQUEST(ACTION_TYPES.DELETE_ACADEMICDEPARTMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ACADEMICDEPARTMENTS):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT):
    case FAILURE(ACTION_TYPES.CREATE_ACADEMICDEPARTMENT):
    case FAILURE(ACTION_TYPES.UPDATE_ACADEMICDEPARTMENT):
    case FAILURE(ACTION_TYPES.DELETE_ACADEMICDEPARTMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ACADEMICDEPARTMENTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICDEPARTMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACADEMICDEPARTMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_ACADEMICDEPARTMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACADEMICDEPARTMENT):
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

const apiUrl = 'api/academic-departments';
const apiSearchUrl = 'api/_search/academic-departments';

// Actions

export const getSearchEntities: ICrudSearchAction<IAcademicDepartment> = query => ({
  type: ACTION_TYPES.SEARCH_ACADEMICDEPARTMENTS,
  payload: axios.get<IAcademicDepartment>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAcademicDepartment> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACADEMICDEPARTMENT_LIST,
  payload: axios.get<IAcademicDepartment>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAcademicDepartment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACADEMICDEPARTMENT,
    payload: axios.get<IAcademicDepartment>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAcademicDepartment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACADEMICDEPARTMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAcademicDepartment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACADEMICDEPARTMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAcademicDepartment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACADEMICDEPARTMENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
