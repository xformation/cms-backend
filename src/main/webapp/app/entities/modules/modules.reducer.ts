import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IModules, defaultValue } from 'app/shared/model/modules.model';

export const ACTION_TYPES = {
  SEARCH_MODULES: 'modules/SEARCH_MODULES',
  FETCH_MODULES_LIST: 'modules/FETCH_MODULES_LIST',
  FETCH_MODULES: 'modules/FETCH_MODULES',
  CREATE_MODULES: 'modules/CREATE_MODULES',
  UPDATE_MODULES: 'modules/UPDATE_MODULES',
  DELETE_MODULES: 'modules/DELETE_MODULES',
  RESET: 'modules/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IModules>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ModulesState = Readonly<typeof initialState>;

// Reducer

export default (state: ModulesState = initialState, action): ModulesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MODULES):
    case REQUEST(ACTION_TYPES.FETCH_MODULES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MODULES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MODULES):
    case REQUEST(ACTION_TYPES.UPDATE_MODULES):
    case REQUEST(ACTION_TYPES.DELETE_MODULES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_MODULES):
    case FAILURE(ACTION_TYPES.FETCH_MODULES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MODULES):
    case FAILURE(ACTION_TYPES.CREATE_MODULES):
    case FAILURE(ACTION_TYPES.UPDATE_MODULES):
    case FAILURE(ACTION_TYPES.DELETE_MODULES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MODULES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MODULES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MODULES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MODULES):
    case SUCCESS(ACTION_TYPES.UPDATE_MODULES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MODULES):
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

const apiUrl = 'api/modules';
const apiSearchUrl = 'api/_search/modules';

// Actions

export const getSearchEntities: ICrudSearchAction<IModules> = query => ({
  type: ACTION_TYPES.SEARCH_MODULES,
  payload: axios.get<IModules>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IModules> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MODULES_LIST,
  payload: axios.get<IModules>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IModules> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MODULES,
    payload: axios.get<IModules>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IModules> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MODULES,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IModules> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MODULES,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IModules> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MODULES,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
