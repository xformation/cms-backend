import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISignatoryLink, defaultValue } from 'app/shared/model/signatory-link.model';

export const ACTION_TYPES = {
  SEARCH_SIGNATORYLINKS: 'signatoryLink/SEARCH_SIGNATORYLINKS',
  FETCH_SIGNATORYLINK_LIST: 'signatoryLink/FETCH_SIGNATORYLINK_LIST',
  FETCH_SIGNATORYLINK: 'signatoryLink/FETCH_SIGNATORYLINK',
  CREATE_SIGNATORYLINK: 'signatoryLink/CREATE_SIGNATORYLINK',
  UPDATE_SIGNATORYLINK: 'signatoryLink/UPDATE_SIGNATORYLINK',
  DELETE_SIGNATORYLINK: 'signatoryLink/DELETE_SIGNATORYLINK',
  RESET: 'signatoryLink/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISignatoryLink>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SignatoryLinkState = Readonly<typeof initialState>;

// Reducer

export default (state: SignatoryLinkState = initialState, action): SignatoryLinkState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_SIGNATORYLINKS):
    case REQUEST(ACTION_TYPES.FETCH_SIGNATORYLINK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SIGNATORYLINK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SIGNATORYLINK):
    case REQUEST(ACTION_TYPES.UPDATE_SIGNATORYLINK):
    case REQUEST(ACTION_TYPES.DELETE_SIGNATORYLINK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_SIGNATORYLINKS):
    case FAILURE(ACTION_TYPES.FETCH_SIGNATORYLINK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SIGNATORYLINK):
    case FAILURE(ACTION_TYPES.CREATE_SIGNATORYLINK):
    case FAILURE(ACTION_TYPES.UPDATE_SIGNATORYLINK):
    case FAILURE(ACTION_TYPES.DELETE_SIGNATORYLINK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_SIGNATORYLINKS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SIGNATORYLINK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SIGNATORYLINK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SIGNATORYLINK):
    case SUCCESS(ACTION_TYPES.UPDATE_SIGNATORYLINK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SIGNATORYLINK):
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

const apiUrl = 'api/signatory-links';
const apiSearchUrl = 'api/_search/signatory-links';

// Actions

export const getSearchEntities: ICrudSearchAction<ISignatoryLink> = query => ({
  type: ACTION_TYPES.SEARCH_SIGNATORYLINKS,
  payload: axios.get<ISignatoryLink>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ISignatoryLink> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SIGNATORYLINK_LIST,
  payload: axios.get<ISignatoryLink>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISignatoryLink> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SIGNATORYLINK,
    payload: axios.get<ISignatoryLink>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISignatoryLink> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SIGNATORYLINK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISignatoryLink> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SIGNATORYLINK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISignatoryLink> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SIGNATORYLINK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
