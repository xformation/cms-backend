import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILateFee, defaultValue } from 'app/shared/model/late-fee.model';

export const ACTION_TYPES = {
  SEARCH_LATEFEES: 'lateFee/SEARCH_LATEFEES',
  FETCH_LATEFEE_LIST: 'lateFee/FETCH_LATEFEE_LIST',
  FETCH_LATEFEE: 'lateFee/FETCH_LATEFEE',
  CREATE_LATEFEE: 'lateFee/CREATE_LATEFEE',
  UPDATE_LATEFEE: 'lateFee/UPDATE_LATEFEE',
  DELETE_LATEFEE: 'lateFee/DELETE_LATEFEE',
  RESET: 'lateFee/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILateFee>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LateFeeState = Readonly<typeof initialState>;

// Reducer

export default (state: LateFeeState = initialState, action): LateFeeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LATEFEES):
    case REQUEST(ACTION_TYPES.FETCH_LATEFEE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LATEFEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LATEFEE):
    case REQUEST(ACTION_TYPES.UPDATE_LATEFEE):
    case REQUEST(ACTION_TYPES.DELETE_LATEFEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LATEFEES):
    case FAILURE(ACTION_TYPES.FETCH_LATEFEE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LATEFEE):
    case FAILURE(ACTION_TYPES.CREATE_LATEFEE):
    case FAILURE(ACTION_TYPES.UPDATE_LATEFEE):
    case FAILURE(ACTION_TYPES.DELETE_LATEFEE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LATEFEES):
    case SUCCESS(ACTION_TYPES.FETCH_LATEFEE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LATEFEE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LATEFEE):
    case SUCCESS(ACTION_TYPES.UPDATE_LATEFEE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LATEFEE):
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

const apiUrl = 'api/late-fees';
const apiSearchUrl = 'api/_search/late-fees';

// Actions

export const getSearchEntities: ICrudSearchAction<ILateFee> = query => ({
  type: ACTION_TYPES.SEARCH_LATEFEES,
  payload: axios.get<ILateFee>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILateFee> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LATEFEE_LIST,
  payload: axios.get<ILateFee>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILateFee> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LATEFEE,
    payload: axios.get<ILateFee>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILateFee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LATEFEE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILateFee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LATEFEE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILateFee> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LATEFEE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
