import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICurrency, defaultValue } from 'app/shared/model/currency.model';

export const ACTION_TYPES = {
  SEARCH_CURRENCIES: 'currency/SEARCH_CURRENCIES',
  FETCH_CURRENCY_LIST: 'currency/FETCH_CURRENCY_LIST',
  FETCH_CURRENCY: 'currency/FETCH_CURRENCY',
  CREATE_CURRENCY: 'currency/CREATE_CURRENCY',
  UPDATE_CURRENCY: 'currency/UPDATE_CURRENCY',
  DELETE_CURRENCY: 'currency/DELETE_CURRENCY',
  RESET: 'currency/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICurrency>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CurrencyState = Readonly<typeof initialState>;

// Reducer

export default (state: CurrencyState = initialState, action): CurrencyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_CURRENCIES):
    case REQUEST(ACTION_TYPES.FETCH_CURRENCY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CURRENCY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CURRENCY):
    case REQUEST(ACTION_TYPES.UPDATE_CURRENCY):
    case REQUEST(ACTION_TYPES.DELETE_CURRENCY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_CURRENCIES):
    case FAILURE(ACTION_TYPES.FETCH_CURRENCY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CURRENCY):
    case FAILURE(ACTION_TYPES.CREATE_CURRENCY):
    case FAILURE(ACTION_TYPES.UPDATE_CURRENCY):
    case FAILURE(ACTION_TYPES.DELETE_CURRENCY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_CURRENCIES):
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CURRENCY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CURRENCY):
    case SUCCESS(ACTION_TYPES.UPDATE_CURRENCY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CURRENCY):
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

const apiUrl = 'api/currencies';
const apiSearchUrl = 'api/_search/currencies';

// Actions

export const getSearchEntities: ICrudSearchAction<ICurrency> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_CURRENCIES,
  payload: axios.get<ICurrency>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<ICurrency> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CURRENCY_LIST,
  payload: axios.get<ICurrency>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICurrency> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CURRENCY,
    payload: axios.get<ICurrency>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICurrency> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CURRENCY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICurrency> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CURRENCY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICurrency> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CURRENCY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
