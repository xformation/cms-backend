import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IInsurance, defaultValue } from 'app/shared/model/insurance.model';

export const ACTION_TYPES = {
  SEARCH_INSURANCES: 'insurance/SEARCH_INSURANCES',
  FETCH_INSURANCE_LIST: 'insurance/FETCH_INSURANCE_LIST',
  FETCH_INSURANCE: 'insurance/FETCH_INSURANCE',
  CREATE_INSURANCE: 'insurance/CREATE_INSURANCE',
  UPDATE_INSURANCE: 'insurance/UPDATE_INSURANCE',
  DELETE_INSURANCE: 'insurance/DELETE_INSURANCE',
  RESET: 'insurance/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IInsurance>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type InsuranceState = Readonly<typeof initialState>;

// Reducer

export default (state: InsuranceState = initialState, action): InsuranceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_INSURANCES):
    case REQUEST(ACTION_TYPES.FETCH_INSURANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_INSURANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_INSURANCE):
    case REQUEST(ACTION_TYPES.UPDATE_INSURANCE):
    case REQUEST(ACTION_TYPES.DELETE_INSURANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_INSURANCES):
    case FAILURE(ACTION_TYPES.FETCH_INSURANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_INSURANCE):
    case FAILURE(ACTION_TYPES.CREATE_INSURANCE):
    case FAILURE(ACTION_TYPES.UPDATE_INSURANCE):
    case FAILURE(ACTION_TYPES.DELETE_INSURANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_INSURANCES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSURANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_INSURANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_INSURANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_INSURANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_INSURANCE):
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

const apiUrl = 'api/insurances';
const apiSearchUrl = 'api/_search/insurances';

// Actions

export const getSearchEntities: ICrudSearchAction<IInsurance> = query => ({
  type: ACTION_TYPES.SEARCH_INSURANCES,
  payload: axios.get<IInsurance>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IInsurance> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_INSURANCE_LIST,
  payload: axios.get<IInsurance>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IInsurance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_INSURANCE,
    payload: axios.get<IInsurance>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IInsurance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_INSURANCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IInsurance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_INSURANCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IInsurance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_INSURANCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
