import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IIdCard, defaultValue } from 'app/shared/model/id-card.model';

export const ACTION_TYPES = {
  SEARCH_IDCARDS: 'idCard/SEARCH_IDCARDS',
  FETCH_IDCARD_LIST: 'idCard/FETCH_IDCARD_LIST',
  FETCH_IDCARD: 'idCard/FETCH_IDCARD',
  CREATE_IDCARD: 'idCard/CREATE_IDCARD',
  UPDATE_IDCARD: 'idCard/UPDATE_IDCARD',
  DELETE_IDCARD: 'idCard/DELETE_IDCARD',
  RESET: 'idCard/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IIdCard>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type IdCardState = Readonly<typeof initialState>;

// Reducer

export default (state: IdCardState = initialState, action): IdCardState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_IDCARDS):
    case REQUEST(ACTION_TYPES.FETCH_IDCARD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IDCARD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_IDCARD):
    case REQUEST(ACTION_TYPES.UPDATE_IDCARD):
    case REQUEST(ACTION_TYPES.DELETE_IDCARD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_IDCARDS):
    case FAILURE(ACTION_TYPES.FETCH_IDCARD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IDCARD):
    case FAILURE(ACTION_TYPES.CREATE_IDCARD):
    case FAILURE(ACTION_TYPES.UPDATE_IDCARD):
    case FAILURE(ACTION_TYPES.DELETE_IDCARD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_IDCARDS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDCARD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_IDCARD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_IDCARD):
    case SUCCESS(ACTION_TYPES.UPDATE_IDCARD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_IDCARD):
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

const apiUrl = 'api/id-cards';
const apiSearchUrl = 'api/_search/id-cards';

// Actions

export const getSearchEntities: ICrudSearchAction<IIdCard> = query => ({
  type: ACTION_TYPES.SEARCH_IDCARDS,
  payload: axios.get<IIdCard>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IIdCard> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_IDCARD_LIST,
  payload: axios.get<IIdCard>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IIdCard> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IDCARD,
    payload: axios.get<IIdCard>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IIdCard> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IDCARD,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IIdCard> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IDCARD,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IIdCard> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IDCARD,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
