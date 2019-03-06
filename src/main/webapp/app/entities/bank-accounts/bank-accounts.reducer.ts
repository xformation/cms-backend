import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBankAccounts, defaultValue } from 'app/shared/model/bank-accounts.model';

export const ACTION_TYPES = {
  SEARCH_BANKACCOUNTS: 'bankAccounts/SEARCH_BANKACCOUNTS',
  FETCH_BANKACCOUNTS_LIST: 'bankAccounts/FETCH_BANKACCOUNTS_LIST',
  FETCH_BANKACCOUNTS: 'bankAccounts/FETCH_BANKACCOUNTS',
  CREATE_BANKACCOUNTS: 'bankAccounts/CREATE_BANKACCOUNTS',
  UPDATE_BANKACCOUNTS: 'bankAccounts/UPDATE_BANKACCOUNTS',
  DELETE_BANKACCOUNTS: 'bankAccounts/DELETE_BANKACCOUNTS',
  RESET: 'bankAccounts/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBankAccounts>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BankAccountsState = Readonly<typeof initialState>;

// Reducer

export default (state: BankAccountsState = initialState, action): BankAccountsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_BANKACCOUNTS):
    case REQUEST(ACTION_TYPES.FETCH_BANKACCOUNTS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BANKACCOUNTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BANKACCOUNTS):
    case REQUEST(ACTION_TYPES.UPDATE_BANKACCOUNTS):
    case REQUEST(ACTION_TYPES.DELETE_BANKACCOUNTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_BANKACCOUNTS):
    case FAILURE(ACTION_TYPES.FETCH_BANKACCOUNTS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BANKACCOUNTS):
    case FAILURE(ACTION_TYPES.CREATE_BANKACCOUNTS):
    case FAILURE(ACTION_TYPES.UPDATE_BANKACCOUNTS):
    case FAILURE(ACTION_TYPES.DELETE_BANKACCOUNTS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_BANKACCOUNTS):
    case SUCCESS(ACTION_TYPES.FETCH_BANKACCOUNTS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BANKACCOUNTS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BANKACCOUNTS):
    case SUCCESS(ACTION_TYPES.UPDATE_BANKACCOUNTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BANKACCOUNTS):
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

const apiUrl = 'api/bank-accounts';
const apiSearchUrl = 'api/_search/bank-accounts';

// Actions

export const getSearchEntities: ICrudSearchAction<IBankAccounts> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_BANKACCOUNTS,
  payload: axios.get<IBankAccounts>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IBankAccounts> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BANKACCOUNTS_LIST,
  payload: axios.get<IBankAccounts>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBankAccounts> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BANKACCOUNTS,
    payload: axios.get<IBankAccounts>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBankAccounts> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BANKACCOUNTS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBankAccounts> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BANKACCOUNTS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBankAccounts> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BANKACCOUNTS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
