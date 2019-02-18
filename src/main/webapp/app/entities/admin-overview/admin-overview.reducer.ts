import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdminOverview, defaultValue } from 'app/shared/model/admin-overview.model';

export const ACTION_TYPES = {
  SEARCH_ADMINOVERVIEWS: 'adminOverview/SEARCH_ADMINOVERVIEWS',
  FETCH_ADMINOVERVIEW_LIST: 'adminOverview/FETCH_ADMINOVERVIEW_LIST',
  FETCH_ADMINOVERVIEW: 'adminOverview/FETCH_ADMINOVERVIEW',
  CREATE_ADMINOVERVIEW: 'adminOverview/CREATE_ADMINOVERVIEW',
  UPDATE_ADMINOVERVIEW: 'adminOverview/UPDATE_ADMINOVERVIEW',
  DELETE_ADMINOVERVIEW: 'adminOverview/DELETE_ADMINOVERVIEW',
  RESET: 'adminOverview/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdminOverview>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AdminOverviewState = Readonly<typeof initialState>;

// Reducer

export default (state: AdminOverviewState = initialState, action): AdminOverviewState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ADMINOVERVIEWS):
    case REQUEST(ACTION_TYPES.FETCH_ADMINOVERVIEW_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADMINOVERVIEW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ADMINOVERVIEW):
    case REQUEST(ACTION_TYPES.UPDATE_ADMINOVERVIEW):
    case REQUEST(ACTION_TYPES.DELETE_ADMINOVERVIEW):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ADMINOVERVIEWS):
    case FAILURE(ACTION_TYPES.FETCH_ADMINOVERVIEW_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADMINOVERVIEW):
    case FAILURE(ACTION_TYPES.CREATE_ADMINOVERVIEW):
    case FAILURE(ACTION_TYPES.UPDATE_ADMINOVERVIEW):
    case FAILURE(ACTION_TYPES.DELETE_ADMINOVERVIEW):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ADMINOVERVIEWS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMINOVERVIEW_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADMINOVERVIEW):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADMINOVERVIEW):
    case SUCCESS(ACTION_TYPES.UPDATE_ADMINOVERVIEW):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADMINOVERVIEW):
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

const apiUrl = 'api/admin-overviews';
const apiSearchUrl = 'api/_search/admin-overviews';

// Actions

export const getSearchEntities: ICrudSearchAction<IAdminOverview> = query => ({
  type: ACTION_TYPES.SEARCH_ADMINOVERVIEWS,
  payload: axios.get<IAdminOverview>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAdminOverview> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADMINOVERVIEW_LIST,
  payload: axios.get<IAdminOverview>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAdminOverview> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADMINOVERVIEW,
    payload: axios.get<IAdminOverview>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAdminOverview> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADMINOVERVIEW,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdminOverview> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADMINOVERVIEW,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdminOverview> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADMINOVERVIEW,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
