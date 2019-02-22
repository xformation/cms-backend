import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITransportRoute, defaultValue } from 'app/shared/model/transport-route.model';

export const ACTION_TYPES = {
  SEARCH_TRANSPORTROUTES: 'transportRoute/SEARCH_TRANSPORTROUTES',
  FETCH_TRANSPORTROUTE_LIST: 'transportRoute/FETCH_TRANSPORTROUTE_LIST',
  FETCH_TRANSPORTROUTE: 'transportRoute/FETCH_TRANSPORTROUTE',
  CREATE_TRANSPORTROUTE: 'transportRoute/CREATE_TRANSPORTROUTE',
  UPDATE_TRANSPORTROUTE: 'transportRoute/UPDATE_TRANSPORTROUTE',
  DELETE_TRANSPORTROUTE: 'transportRoute/DELETE_TRANSPORTROUTE',
  RESET: 'transportRoute/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITransportRoute>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TransportRouteState = Readonly<typeof initialState>;

// Reducer

export default (state: TransportRouteState = initialState, action): TransportRouteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TRANSPORTROUTES):
    case REQUEST(ACTION_TYPES.FETCH_TRANSPORTROUTE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRANSPORTROUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRANSPORTROUTE):
    case REQUEST(ACTION_TYPES.UPDATE_TRANSPORTROUTE):
    case REQUEST(ACTION_TYPES.DELETE_TRANSPORTROUTE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TRANSPORTROUTES):
    case FAILURE(ACTION_TYPES.FETCH_TRANSPORTROUTE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRANSPORTROUTE):
    case FAILURE(ACTION_TYPES.CREATE_TRANSPORTROUTE):
    case FAILURE(ACTION_TYPES.UPDATE_TRANSPORTROUTE):
    case FAILURE(ACTION_TYPES.DELETE_TRANSPORTROUTE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TRANSPORTROUTES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSPORTROUTE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRANSPORTROUTE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRANSPORTROUTE):
    case SUCCESS(ACTION_TYPES.UPDATE_TRANSPORTROUTE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRANSPORTROUTE):
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

const apiUrl = 'api/transport-routes';
const apiSearchUrl = 'api/_search/transport-routes';

// Actions

export const getSearchEntities: ICrudSearchAction<ITransportRoute> = query => ({
  type: ACTION_TYPES.SEARCH_TRANSPORTROUTES,
  payload: axios.get<ITransportRoute>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITransportRoute> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRANSPORTROUTE_LIST,
  payload: axios.get<ITransportRoute>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITransportRoute> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRANSPORTROUTE,
    payload: axios.get<ITransportRoute>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITransportRoute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRANSPORTROUTE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITransportRoute> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRANSPORTROUTE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITransportRoute> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRANSPORTROUTE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
