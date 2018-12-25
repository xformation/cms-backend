import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPeriods, defaultValue } from 'app/shared/model/periods.model';

export const ACTION_TYPES = {
  SEARCH_PERIODS: 'periods/SEARCH_PERIODS',
  FETCH_PERIODS_LIST: 'periods/FETCH_PERIODS_LIST',
  FETCH_PERIODS: 'periods/FETCH_PERIODS',
  CREATE_PERIODS: 'periods/CREATE_PERIODS',
  UPDATE_PERIODS: 'periods/UPDATE_PERIODS',
  DELETE_PERIODS: 'periods/DELETE_PERIODS',
  RESET: 'periods/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPeriods>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PeriodsState = Readonly<typeof initialState>;

// Reducer

export default (state: PeriodsState = initialState, action): PeriodsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PERIODS):
    case REQUEST(ACTION_TYPES.FETCH_PERIODS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PERIODS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PERIODS):
    case REQUEST(ACTION_TYPES.UPDATE_PERIODS):
    case REQUEST(ACTION_TYPES.DELETE_PERIODS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PERIODS):
    case FAILURE(ACTION_TYPES.FETCH_PERIODS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PERIODS):
    case FAILURE(ACTION_TYPES.CREATE_PERIODS):
    case FAILURE(ACTION_TYPES.UPDATE_PERIODS):
    case FAILURE(ACTION_TYPES.DELETE_PERIODS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PERIODS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERIODS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PERIODS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PERIODS):
    case SUCCESS(ACTION_TYPES.UPDATE_PERIODS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PERIODS):
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

const apiUrl = 'api/periods';
const apiSearchUrl = 'api/_search/periods';

// Actions

export const getSearchEntities: ICrudSearchAction<IPeriods> = query => ({
  type: ACTION_TYPES.SEARCH_PERIODS,
  payload: axios.get<IPeriods>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPeriods> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PERIODS_LIST,
  payload: axios.get<IPeriods>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPeriods> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PERIODS,
    payload: axios.get<IPeriods>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPeriods> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PERIODS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPeriods> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PERIODS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPeriods> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PERIODS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
