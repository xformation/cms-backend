import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFeeDetails, defaultValue } from 'app/shared/model/fee-details.model';

export const ACTION_TYPES = {
  SEARCH_FEEDETAILS: 'feeDetails/SEARCH_FEEDETAILS',
  FETCH_FEEDETAILS_LIST: 'feeDetails/FETCH_FEEDETAILS_LIST',
  FETCH_FEEDETAILS: 'feeDetails/FETCH_FEEDETAILS',
  CREATE_FEEDETAILS: 'feeDetails/CREATE_FEEDETAILS',
  UPDATE_FEEDETAILS: 'feeDetails/UPDATE_FEEDETAILS',
  DELETE_FEEDETAILS: 'feeDetails/DELETE_FEEDETAILS',
  RESET: 'feeDetails/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFeeDetails>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FeeDetailsState = Readonly<typeof initialState>;

// Reducer

export default (state: FeeDetailsState = initialState, action): FeeDetailsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_FEEDETAILS):
    case REQUEST(ACTION_TYPES.FETCH_FEEDETAILS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FEEDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FEEDETAILS):
    case REQUEST(ACTION_TYPES.UPDATE_FEEDETAILS):
    case REQUEST(ACTION_TYPES.DELETE_FEEDETAILS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_FEEDETAILS):
    case FAILURE(ACTION_TYPES.FETCH_FEEDETAILS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FEEDETAILS):
    case FAILURE(ACTION_TYPES.CREATE_FEEDETAILS):
    case FAILURE(ACTION_TYPES.UPDATE_FEEDETAILS):
    case FAILURE(ACTION_TYPES.DELETE_FEEDETAILS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_FEEDETAILS):
    case SUCCESS(ACTION_TYPES.FETCH_FEEDETAILS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FEEDETAILS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FEEDETAILS):
    case SUCCESS(ACTION_TYPES.UPDATE_FEEDETAILS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FEEDETAILS):
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

const apiUrl = 'api/fee-details';
const apiSearchUrl = 'api/_search/fee-details';

// Actions

export const getSearchEntities: ICrudSearchAction<IFeeDetails> = query => ({
  type: ACTION_TYPES.SEARCH_FEEDETAILS,
  payload: axios.get<IFeeDetails>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IFeeDetails> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FEEDETAILS_LIST,
  payload: axios.get<IFeeDetails>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFeeDetails> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FEEDETAILS,
    payload: axios.get<IFeeDetails>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFeeDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FEEDETAILS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFeeDetails> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FEEDETAILS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFeeDetails> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FEEDETAILS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
