import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITerm, defaultValue } from 'app/shared/model/term.model';

export const ACTION_TYPES = {
  SEARCH_TERMS: 'term/SEARCH_TERMS',
  FETCH_TERM_LIST: 'term/FETCH_TERM_LIST',
  FETCH_TERM: 'term/FETCH_TERM',
  CREATE_TERM: 'term/CREATE_TERM',
  UPDATE_TERM: 'term/UPDATE_TERM',
  DELETE_TERM: 'term/DELETE_TERM',
  RESET: 'term/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITerm>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TermState = Readonly<typeof initialState>;

// Reducer

export default (state: TermState = initialState, action): TermState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TERMS):
    case REQUEST(ACTION_TYPES.FETCH_TERM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TERM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TERM):
    case REQUEST(ACTION_TYPES.UPDATE_TERM):
    case REQUEST(ACTION_TYPES.DELETE_TERM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TERMS):
    case FAILURE(ACTION_TYPES.FETCH_TERM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TERM):
    case FAILURE(ACTION_TYPES.CREATE_TERM):
    case FAILURE(ACTION_TYPES.UPDATE_TERM):
    case FAILURE(ACTION_TYPES.DELETE_TERM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TERMS):
    case SUCCESS(ACTION_TYPES.FETCH_TERM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TERM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TERM):
    case SUCCESS(ACTION_TYPES.UPDATE_TERM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TERM):
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

const apiUrl = 'api/terms';
const apiSearchUrl = 'api/_search/terms';

// Actions

export const getSearchEntities: ICrudSearchAction<ITerm> = query => ({
  type: ACTION_TYPES.SEARCH_TERMS,
  payload: axios.get<ITerm>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITerm> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TERM_LIST,
  payload: axios.get<ITerm>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITerm> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TERM,
    payload: axios.get<ITerm>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITerm> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TERM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITerm> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TERM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITerm> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TERM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
