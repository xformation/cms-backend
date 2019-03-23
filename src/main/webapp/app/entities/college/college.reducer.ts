import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { ICollege, defaultValue } from 'app/shared/model/college.model';

export const ACTION_TYPES = {
  SEARCH_COLLEGES: 'college/SEARCH_COLLEGES',
  FETCH_COLLEGE_LIST: 'college/FETCH_COLLEGE_LIST',
  FETCH_COLLEGE: 'college/FETCH_COLLEGE',
  CREATE_COLLEGE: 'college/CREATE_COLLEGE',
  UPDATE_COLLEGE: 'college/UPDATE_COLLEGE',
  DELETE_COLLEGE: 'college/DELETE_COLLEGE',
  RESET: 'college/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICollege>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CollegeState = Readonly<typeof initialState>;

// Reducer

export default (state: CollegeState = initialState, action): CollegeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_COLLEGES):
    case REQUEST(ACTION_TYPES.FETCH_COLLEGE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COLLEGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COLLEGE):
    case REQUEST(ACTION_TYPES.UPDATE_COLLEGE):
    case REQUEST(ACTION_TYPES.DELETE_COLLEGE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_COLLEGES):
    case FAILURE(ACTION_TYPES.FETCH_COLLEGE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COLLEGE):
    case FAILURE(ACTION_TYPES.CREATE_COLLEGE):
    case FAILURE(ACTION_TYPES.UPDATE_COLLEGE):
    case FAILURE(ACTION_TYPES.DELETE_COLLEGE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_COLLEGES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COLLEGE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COLLEGE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COLLEGE):
    case SUCCESS(ACTION_TYPES.UPDATE_COLLEGE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COLLEGE):
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

const apiUrl = SERVER_API_URL + '/api/colleges';
const apiSearchUrl = SERVER_API_URL + '/api/_search/colleges';

// Actions

export const getSearchEntities: ICrudSearchAction<ICollege> = query => ({
  type: ACTION_TYPES.SEARCH_COLLEGES,
  payload: axios.get<ICollege>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ICollege> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COLLEGE_LIST,
  payload: axios.get<ICollege>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICollege> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COLLEGE,
    payload: axios.get<ICollege>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICollege> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COLLEGE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICollege> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COLLEGE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICollege> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COLLEGE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
