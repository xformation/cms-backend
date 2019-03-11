import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBatch, defaultValue } from 'app/shared/model/batch.model';

export const ACTION_TYPES = {
  SEARCH_BATCHES: 'batch/SEARCH_BATCHES',
  FETCH_BATCH_LIST: 'batch/FETCH_BATCH_LIST',
  FETCH_BATCH: 'batch/FETCH_BATCH',
  CREATE_BATCH: 'batch/CREATE_BATCH',
  UPDATE_BATCH: 'batch/UPDATE_BATCH',
  DELETE_BATCH: 'batch/DELETE_BATCH',
  RESET: 'batch/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBatch>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BatchState = Readonly<typeof initialState>;

// Reducer

export default (state: BatchState = initialState, action): BatchState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_BATCHES):
    case REQUEST(ACTION_TYPES.FETCH_BATCH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BATCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BATCH):
    case REQUEST(ACTION_TYPES.UPDATE_BATCH):
    case REQUEST(ACTION_TYPES.DELETE_BATCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_BATCHES):
    case FAILURE(ACTION_TYPES.FETCH_BATCH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BATCH):
    case FAILURE(ACTION_TYPES.CREATE_BATCH):
    case FAILURE(ACTION_TYPES.UPDATE_BATCH):
    case FAILURE(ACTION_TYPES.DELETE_BATCH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_BATCHES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BATCH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BATCH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BATCH):
    case SUCCESS(ACTION_TYPES.UPDATE_BATCH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BATCH):
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

const apiUrl = 'api/batches';
const apiSearchUrl = 'api/_search/batches';

// Actions

export const getSearchEntities: ICrudSearchAction<IBatch> = query => ({
  type: ACTION_TYPES.SEARCH_BATCHES,
  payload: axios.get<IBatch>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IBatch> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BATCH_LIST,
  payload: axios.get<IBatch>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBatch> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BATCH,
    payload: axios.get<IBatch>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBatch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BATCH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBatch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BATCH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBatch> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BATCH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
