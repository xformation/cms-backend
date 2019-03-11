import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFacility, defaultValue } from 'app/shared/model/facility.model';

export const ACTION_TYPES = {
  SEARCH_FACILITIES: 'facility/SEARCH_FACILITIES',
  FETCH_FACILITY_LIST: 'facility/FETCH_FACILITY_LIST',
  FETCH_FACILITY: 'facility/FETCH_FACILITY',
  CREATE_FACILITY: 'facility/CREATE_FACILITY',
  UPDATE_FACILITY: 'facility/UPDATE_FACILITY',
  DELETE_FACILITY: 'facility/DELETE_FACILITY',
  RESET: 'facility/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFacility>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FacilityState = Readonly<typeof initialState>;

// Reducer

export default (state: FacilityState = initialState, action): FacilityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_FACILITIES):
    case REQUEST(ACTION_TYPES.FETCH_FACILITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FACILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FACILITY):
    case REQUEST(ACTION_TYPES.UPDATE_FACILITY):
    case REQUEST(ACTION_TYPES.DELETE_FACILITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_FACILITIES):
    case FAILURE(ACTION_TYPES.FETCH_FACILITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FACILITY):
    case FAILURE(ACTION_TYPES.CREATE_FACILITY):
    case FAILURE(ACTION_TYPES.UPDATE_FACILITY):
    case FAILURE(ACTION_TYPES.DELETE_FACILITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_FACILITIES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACILITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FACILITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FACILITY):
    case SUCCESS(ACTION_TYPES.UPDATE_FACILITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FACILITY):
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

const apiUrl = 'api/facilities';
const apiSearchUrl = 'api/_search/facilities';

// Actions

export const getSearchEntities: ICrudSearchAction<IFacility> = query => ({
  type: ACTION_TYPES.SEARCH_FACILITIES,
  payload: axios.get<IFacility>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IFacility> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FACILITY_LIST,
  payload: axios.get<IFacility>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFacility> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FACILITY,
    payload: axios.get<IFacility>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFacility> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FACILITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFacility> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FACILITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFacility> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FACILITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
