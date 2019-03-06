import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITeach, defaultValue } from 'app/shared/model/teach.model';

export const ACTION_TYPES = {
  SEARCH_TEACHES: 'teach/SEARCH_TEACHES',
  FETCH_TEACH_LIST: 'teach/FETCH_TEACH_LIST',
  FETCH_TEACH: 'teach/FETCH_TEACH',
  CREATE_TEACH: 'teach/CREATE_TEACH',
  UPDATE_TEACH: 'teach/UPDATE_TEACH',
  DELETE_TEACH: 'teach/DELETE_TEACH',
  RESET: 'teach/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITeach>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TeachState = Readonly<typeof initialState>;

// Reducer

export default (state: TeachState = initialState, action): TeachState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TEACHES):
    case REQUEST(ACTION_TYPES.FETCH_TEACH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TEACH):
    case REQUEST(ACTION_TYPES.UPDATE_TEACH):
    case REQUEST(ACTION_TYPES.DELETE_TEACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TEACHES):
    case FAILURE(ACTION_TYPES.FETCH_TEACH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEACH):
    case FAILURE(ACTION_TYPES.CREATE_TEACH):
    case FAILURE(ACTION_TYPES.UPDATE_TEACH):
    case FAILURE(ACTION_TYPES.DELETE_TEACH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TEACHES):
    case SUCCESS(ACTION_TYPES.FETCH_TEACH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEACH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEACH):
    case SUCCESS(ACTION_TYPES.UPDATE_TEACH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEACH):
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

const apiUrl = 'api/teaches';
const apiSearchUrl = 'api/_search/teaches';

// Actions

export const getSearchEntities: ICrudSearchAction<ITeach> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_TEACHES,
  payload: axios.get<ITeach>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<ITeach> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TEACH_LIST,
  payload: axios.get<ITeach>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITeach> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEACH,
    payload: axios.get<ITeach>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITeach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEACH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITeach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEACH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITeach> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEACH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
