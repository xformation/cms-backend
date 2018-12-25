import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICollegeBranches, defaultValue } from 'app/shared/model/college-branches.model';

export const ACTION_TYPES = {
  SEARCH_COLLEGEBRANCHES: 'collegeBranches/SEARCH_COLLEGEBRANCHES',
  FETCH_COLLEGEBRANCHES_LIST: 'collegeBranches/FETCH_COLLEGEBRANCHES_LIST',
  FETCH_COLLEGEBRANCHES: 'collegeBranches/FETCH_COLLEGEBRANCHES',
  CREATE_COLLEGEBRANCHES: 'collegeBranches/CREATE_COLLEGEBRANCHES',
  UPDATE_COLLEGEBRANCHES: 'collegeBranches/UPDATE_COLLEGEBRANCHES',
  DELETE_COLLEGEBRANCHES: 'collegeBranches/DELETE_COLLEGEBRANCHES',
  RESET: 'collegeBranches/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICollegeBranches>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CollegeBranchesState = Readonly<typeof initialState>;

// Reducer

export default (state: CollegeBranchesState = initialState, action): CollegeBranchesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_COLLEGEBRANCHES):
    case REQUEST(ACTION_TYPES.FETCH_COLLEGEBRANCHES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COLLEGEBRANCHES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COLLEGEBRANCHES):
    case REQUEST(ACTION_TYPES.UPDATE_COLLEGEBRANCHES):
    case REQUEST(ACTION_TYPES.DELETE_COLLEGEBRANCHES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_COLLEGEBRANCHES):
    case FAILURE(ACTION_TYPES.FETCH_COLLEGEBRANCHES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COLLEGEBRANCHES):
    case FAILURE(ACTION_TYPES.CREATE_COLLEGEBRANCHES):
    case FAILURE(ACTION_TYPES.UPDATE_COLLEGEBRANCHES):
    case FAILURE(ACTION_TYPES.DELETE_COLLEGEBRANCHES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_COLLEGEBRANCHES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COLLEGEBRANCHES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COLLEGEBRANCHES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COLLEGEBRANCHES):
    case SUCCESS(ACTION_TYPES.UPDATE_COLLEGEBRANCHES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COLLEGEBRANCHES):
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

const apiUrl = 'api/college-branches';
const apiSearchUrl = 'api/_search/college-branches';

// Actions

export const getSearchEntities: ICrudSearchAction<ICollegeBranches> = query => ({
  type: ACTION_TYPES.SEARCH_COLLEGEBRANCHES,
  payload: axios.get<ICollegeBranches>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ICollegeBranches> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COLLEGEBRANCHES_LIST,
  payload: axios.get<ICollegeBranches>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICollegeBranches> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COLLEGEBRANCHES,
    payload: axios.get<ICollegeBranches>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICollegeBranches> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COLLEGEBRANCHES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICollegeBranches> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COLLEGEBRANCHES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICollegeBranches> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COLLEGEBRANCHES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
