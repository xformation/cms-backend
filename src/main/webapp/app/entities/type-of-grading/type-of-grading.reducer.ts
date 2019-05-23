import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypeOfGrading, defaultValue } from 'app/shared/model/type-of-grading.model';

export const ACTION_TYPES = {
  SEARCH_TYPEOFGRADINGS: 'typeOfGrading/SEARCH_TYPEOFGRADINGS',
  FETCH_TYPEOFGRADING_LIST: 'typeOfGrading/FETCH_TYPEOFGRADING_LIST',
  FETCH_TYPEOFGRADING: 'typeOfGrading/FETCH_TYPEOFGRADING',
  CREATE_TYPEOFGRADING: 'typeOfGrading/CREATE_TYPEOFGRADING',
  UPDATE_TYPEOFGRADING: 'typeOfGrading/UPDATE_TYPEOFGRADING',
  DELETE_TYPEOFGRADING: 'typeOfGrading/DELETE_TYPEOFGRADING',
  RESET: 'typeOfGrading/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeOfGrading>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TypeOfGradingState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeOfGradingState = initialState, action): TypeOfGradingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TYPEOFGRADINGS):
    case REQUEST(ACTION_TYPES.FETCH_TYPEOFGRADING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEOFGRADING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEOFGRADING):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEOFGRADING):
    case REQUEST(ACTION_TYPES.DELETE_TYPEOFGRADING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TYPEOFGRADINGS):
    case FAILURE(ACTION_TYPES.FETCH_TYPEOFGRADING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEOFGRADING):
    case FAILURE(ACTION_TYPES.CREATE_TYPEOFGRADING):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEOFGRADING):
    case FAILURE(ACTION_TYPES.DELETE_TYPEOFGRADING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TYPEOFGRADINGS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEOFGRADING_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEOFGRADING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEOFGRADING):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEOFGRADING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEOFGRADING):
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

const apiUrl = 'api/type-of-gradings';
const apiSearchUrl = 'api/_search/type-of-gradings';

// Actions

export const getSearchEntities: ICrudSearchAction<ITypeOfGrading> = query => ({
  type: ACTION_TYPES.SEARCH_TYPEOFGRADINGS,
  payload: axios.get<ITypeOfGrading>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITypeOfGrading> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEOFGRADING_LIST,
  payload: axios.get<ITypeOfGrading>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITypeOfGrading> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEOFGRADING,
    payload: axios.get<ITypeOfGrading>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITypeOfGrading> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEOFGRADING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeOfGrading> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEOFGRADING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeOfGrading> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEOFGRADING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
