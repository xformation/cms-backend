import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICity, defaultValue } from 'app/shared/model/city.model';

export const ACTION_TYPES = {
  SEARCH_CITIES: 'city/SEARCH_CITIES',
  FETCH_CITY_LIST: 'city/FETCH_CITY_LIST',
  FETCH_CITY: 'city/FETCH_CITY',
  CREATE_CITY: 'city/CREATE_CITY',
  UPDATE_CITY: 'city/UPDATE_CITY',
  DELETE_CITY: 'city/DELETE_CITY',
  RESET: 'city/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICity>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CityState = Readonly<typeof initialState>;

// Reducer

export default (state: CityState = initialState, action): CityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_CITIES):
    case REQUEST(ACTION_TYPES.FETCH_CITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CITY):
    case REQUEST(ACTION_TYPES.UPDATE_CITY):
    case REQUEST(ACTION_TYPES.DELETE_CITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_CITIES):
    case FAILURE(ACTION_TYPES.FETCH_CITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CITY):
    case FAILURE(ACTION_TYPES.CREATE_CITY):
    case FAILURE(ACTION_TYPES.UPDATE_CITY):
    case FAILURE(ACTION_TYPES.DELETE_CITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_CITIES):
    case SUCCESS(ACTION_TYPES.FETCH_CITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CITY):
    case SUCCESS(ACTION_TYPES.UPDATE_CITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CITY):
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

const apiUrl = 'api/cities';
const apiSearchUrl = 'api/_search/cities';

// Actions

export const getSearchEntities: ICrudSearchAction<ICity> = query => ({
  type: ACTION_TYPES.SEARCH_CITIES,
  payload: axios.get<ICity>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ICity> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CITY_LIST,
  payload: axios.get<ICity>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICity> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CITY,
    payload: axios.get<ICity>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICity> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
