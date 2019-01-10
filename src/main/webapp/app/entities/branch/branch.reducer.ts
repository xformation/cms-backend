import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBranch, defaultValue } from 'app/shared/model/branch.model';

export const ACTION_TYPES = {
  SEARCH_BRANCHES: 'branch/SEARCH_BRANCHES',
  FETCH_BRANCH_LIST: 'branch/FETCH_BRANCH_LIST',
  FETCH_BRANCH: 'branch/FETCH_BRANCH',
  CREATE_BRANCH: 'branch/CREATE_BRANCH',
  UPDATE_BRANCH: 'branch/UPDATE_BRANCH',
  DELETE_BRANCH: 'branch/DELETE_BRANCH',
  RESET: 'branch/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBranch>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BranchState = Readonly<typeof initialState>;

// Reducer

export default (state: BranchState = initialState, action): BranchState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_BRANCHES):
    case REQUEST(ACTION_TYPES.FETCH_BRANCH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BRANCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BRANCH):
    case REQUEST(ACTION_TYPES.UPDATE_BRANCH):
    case REQUEST(ACTION_TYPES.DELETE_BRANCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_BRANCHES):
    case FAILURE(ACTION_TYPES.FETCH_BRANCH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BRANCH):
    case FAILURE(ACTION_TYPES.CREATE_BRANCH):
    case FAILURE(ACTION_TYPES.UPDATE_BRANCH):
    case FAILURE(ACTION_TYPES.DELETE_BRANCH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_BRANCHES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BRANCH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BRANCH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BRANCH):
    case SUCCESS(ACTION_TYPES.UPDATE_BRANCH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BRANCH):
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

const apiUrl = 'api/branches';
const apiSearchUrl = 'api/_search/branches';

// Actions

export const getSearchEntities: ICrudSearchAction<IBranch> = query => ({
  type: ACTION_TYPES.SEARCH_BRANCHES,
  payload: axios.get<IBranch>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IBranch> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BRANCH_LIST,
  payload: axios.get<IBranch>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBranch> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BRANCH,
    payload: axios.get<IBranch>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBranch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BRANCH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBranch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BRANCH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBranch> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BRANCH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
