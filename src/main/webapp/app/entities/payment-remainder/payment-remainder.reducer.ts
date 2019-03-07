import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPaymentRemainder, defaultValue } from 'app/shared/model/payment-remainder.model';

export const ACTION_TYPES = {
  SEARCH_PAYMENTREMAINDERS: 'paymentRemainder/SEARCH_PAYMENTREMAINDERS',
  FETCH_PAYMENTREMAINDER_LIST: 'paymentRemainder/FETCH_PAYMENTREMAINDER_LIST',
  FETCH_PAYMENTREMAINDER: 'paymentRemainder/FETCH_PAYMENTREMAINDER',
  CREATE_PAYMENTREMAINDER: 'paymentRemainder/CREATE_PAYMENTREMAINDER',
  UPDATE_PAYMENTREMAINDER: 'paymentRemainder/UPDATE_PAYMENTREMAINDER',
  DELETE_PAYMENTREMAINDER: 'paymentRemainder/DELETE_PAYMENTREMAINDER',
  RESET: 'paymentRemainder/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPaymentRemainder>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PaymentRemainderState = Readonly<typeof initialState>;

// Reducer

export default (state: PaymentRemainderState = initialState, action): PaymentRemainderState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PAYMENTREMAINDERS):
    case REQUEST(ACTION_TYPES.FETCH_PAYMENTREMAINDER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PAYMENTREMAINDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PAYMENTREMAINDER):
    case REQUEST(ACTION_TYPES.UPDATE_PAYMENTREMAINDER):
    case REQUEST(ACTION_TYPES.DELETE_PAYMENTREMAINDER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PAYMENTREMAINDERS):
    case FAILURE(ACTION_TYPES.FETCH_PAYMENTREMAINDER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PAYMENTREMAINDER):
    case FAILURE(ACTION_TYPES.CREATE_PAYMENTREMAINDER):
    case FAILURE(ACTION_TYPES.UPDATE_PAYMENTREMAINDER):
    case FAILURE(ACTION_TYPES.DELETE_PAYMENTREMAINDER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PAYMENTREMAINDERS):
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENTREMAINDER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PAYMENTREMAINDER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PAYMENTREMAINDER):
    case SUCCESS(ACTION_TYPES.UPDATE_PAYMENTREMAINDER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PAYMENTREMAINDER):
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

const apiUrl = 'api/payment-remainders';
const apiSearchUrl = 'api/_search/payment-remainders';

// Actions

export const getSearchEntities: ICrudSearchAction<IPaymentRemainder> = query => ({
  type: ACTION_TYPES.SEARCH_PAYMENTREMAINDERS,
  payload: axios.get<IPaymentRemainder>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPaymentRemainder> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PAYMENTREMAINDER_LIST,
  payload: axios.get<IPaymentRemainder>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPaymentRemainder> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PAYMENTREMAINDER,
    payload: axios.get<IPaymentRemainder>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPaymentRemainder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PAYMENTREMAINDER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPaymentRemainder> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PAYMENTREMAINDER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPaymentRemainder> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PAYMENTREMAINDER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
