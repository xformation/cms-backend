import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAuthorizedSignatory, defaultValue } from 'app/shared/model/authorized-signatory.model';

export const ACTION_TYPES = {
  SEARCH_AUTHORIZEDSIGNATORIES: 'authorizedSignatory/SEARCH_AUTHORIZEDSIGNATORIES',
  FETCH_AUTHORIZEDSIGNATORY_LIST: 'authorizedSignatory/FETCH_AUTHORIZEDSIGNATORY_LIST',
  FETCH_AUTHORIZEDSIGNATORY: 'authorizedSignatory/FETCH_AUTHORIZEDSIGNATORY',
  CREATE_AUTHORIZEDSIGNATORY: 'authorizedSignatory/CREATE_AUTHORIZEDSIGNATORY',
  UPDATE_AUTHORIZEDSIGNATORY: 'authorizedSignatory/UPDATE_AUTHORIZEDSIGNATORY',
  DELETE_AUTHORIZEDSIGNATORY: 'authorizedSignatory/DELETE_AUTHORIZEDSIGNATORY',
  RESET: 'authorizedSignatory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAuthorizedSignatory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AuthorizedSignatoryState = Readonly<typeof initialState>;

// Reducer

export default (state: AuthorizedSignatoryState = initialState, action): AuthorizedSignatoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_AUTHORIZEDSIGNATORIES):
    case REQUEST(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_AUTHORIZEDSIGNATORY):
    case REQUEST(ACTION_TYPES.UPDATE_AUTHORIZEDSIGNATORY):
    case REQUEST(ACTION_TYPES.DELETE_AUTHORIZEDSIGNATORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_AUTHORIZEDSIGNATORIES):
    case FAILURE(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY):
    case FAILURE(ACTION_TYPES.CREATE_AUTHORIZEDSIGNATORY):
    case FAILURE(ACTION_TYPES.UPDATE_AUTHORIZEDSIGNATORY):
    case FAILURE(ACTION_TYPES.DELETE_AUTHORIZEDSIGNATORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_AUTHORIZEDSIGNATORIES):
    case SUCCESS(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_AUTHORIZEDSIGNATORY):
    case SUCCESS(ACTION_TYPES.UPDATE_AUTHORIZEDSIGNATORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_AUTHORIZEDSIGNATORY):
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

const apiUrl = 'api/authorized-signatories';
const apiSearchUrl = 'api/_search/authorized-signatories';

// Actions

export const getSearchEntities: ICrudSearchAction<IAuthorizedSignatory> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_AUTHORIZEDSIGNATORIES,
  payload: axios.get<IAuthorizedSignatory>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IAuthorizedSignatory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY_LIST,
  payload: axios.get<IAuthorizedSignatory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAuthorizedSignatory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AUTHORIZEDSIGNATORY,
    payload: axios.get<IAuthorizedSignatory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAuthorizedSignatory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AUTHORIZEDSIGNATORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAuthorizedSignatory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AUTHORIZEDSIGNATORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAuthorizedSignatory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AUTHORIZEDSIGNATORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
