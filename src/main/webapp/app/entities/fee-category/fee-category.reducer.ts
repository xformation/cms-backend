import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFeeCategory, defaultValue } from 'app/shared/model/fee-category.model';

export const ACTION_TYPES = {
  SEARCH_FEECATEGORIES: 'feeCategory/SEARCH_FEECATEGORIES',
  FETCH_FEECATEGORY_LIST: 'feeCategory/FETCH_FEECATEGORY_LIST',
  FETCH_FEECATEGORY: 'feeCategory/FETCH_FEECATEGORY',
  CREATE_FEECATEGORY: 'feeCategory/CREATE_FEECATEGORY',
  UPDATE_FEECATEGORY: 'feeCategory/UPDATE_FEECATEGORY',
  DELETE_FEECATEGORY: 'feeCategory/DELETE_FEECATEGORY',
  RESET: 'feeCategory/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFeeCategory>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FeeCategoryState = Readonly<typeof initialState>;

// Reducer

export default (state: FeeCategoryState = initialState, action): FeeCategoryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_FEECATEGORIES):
    case REQUEST(ACTION_TYPES.FETCH_FEECATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FEECATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FEECATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_FEECATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_FEECATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_FEECATEGORIES):
    case FAILURE(ACTION_TYPES.FETCH_FEECATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FEECATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_FEECATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_FEECATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_FEECATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_FEECATEGORIES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FEECATEGORY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FEECATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FEECATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_FEECATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FEECATEGORY):
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

const apiUrl = 'api/fee-categories';
const apiSearchUrl = 'api/_search/fee-categories';

// Actions

export const getSearchEntities: ICrudSearchAction<IFeeCategory> = query => ({
  type: ACTION_TYPES.SEARCH_FEECATEGORIES,
  payload: axios.get<IFeeCategory>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IFeeCategory> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FEECATEGORY_LIST,
  payload: axios.get<IFeeCategory>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFeeCategory> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FEECATEGORY,
    payload: axios.get<IFeeCategory>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFeeCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FEECATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFeeCategory> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FEECATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFeeCategory> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FEECATEGORY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
