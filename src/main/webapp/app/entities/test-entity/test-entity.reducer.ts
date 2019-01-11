import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITestEntity, defaultValue } from 'app/shared/model/test-entity.model';

export const ACTION_TYPES = {
  SEARCH_TESTENTITIES: 'testEntity/SEARCH_TESTENTITIES',
  FETCH_TESTENTITY_LIST: 'testEntity/FETCH_TESTENTITY_LIST',
  FETCH_TESTENTITY: 'testEntity/FETCH_TESTENTITY',
  CREATE_TESTENTITY: 'testEntity/CREATE_TESTENTITY',
  UPDATE_TESTENTITY: 'testEntity/UPDATE_TESTENTITY',
  DELETE_TESTENTITY: 'testEntity/DELETE_TESTENTITY',
  RESET: 'testEntity/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITestEntity>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TestEntityState = Readonly<typeof initialState>;

// Reducer

export default (state: TestEntityState = initialState, action): TestEntityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TESTENTITIES):
    case REQUEST(ACTION_TYPES.FETCH_TESTENTITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TESTENTITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TESTENTITY):
    case REQUEST(ACTION_TYPES.UPDATE_TESTENTITY):
    case REQUEST(ACTION_TYPES.DELETE_TESTENTITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TESTENTITIES):
    case FAILURE(ACTION_TYPES.FETCH_TESTENTITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TESTENTITY):
    case FAILURE(ACTION_TYPES.CREATE_TESTENTITY):
    case FAILURE(ACTION_TYPES.UPDATE_TESTENTITY):
    case FAILURE(ACTION_TYPES.DELETE_TESTENTITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TESTENTITIES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTENTITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTENTITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TESTENTITY):
    case SUCCESS(ACTION_TYPES.UPDATE_TESTENTITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TESTENTITY):
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

const apiUrl = 'api/test-entities';
const apiSearchUrl = 'api/_search/test-entities';

// Actions

export const getSearchEntities: ICrudSearchAction<ITestEntity> = query => ({
  type: ACTION_TYPES.SEARCH_TESTENTITIES,
  payload: axios.get<ITestEntity>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITestEntity> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TESTENTITY_LIST,
  payload: axios.get<ITestEntity>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITestEntity> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TESTENTITY,
    payload: axios.get<ITestEntity>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITestEntity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TESTENTITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITestEntity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TESTENTITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITestEntity> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TESTENTITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
