import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILibrary, defaultValue } from 'app/shared/model/library.model';

export const ACTION_TYPES = {
  SEARCH_LIBRARIES: 'library/SEARCH_LIBRARIES',
  FETCH_LIBRARY_LIST: 'library/FETCH_LIBRARY_LIST',
  FETCH_LIBRARY: 'library/FETCH_LIBRARY',
  CREATE_LIBRARY: 'library/CREATE_LIBRARY',
  UPDATE_LIBRARY: 'library/UPDATE_LIBRARY',
  DELETE_LIBRARY: 'library/DELETE_LIBRARY',
  RESET: 'library/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILibrary>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LibraryState = Readonly<typeof initialState>;

// Reducer

export default (state: LibraryState = initialState, action): LibraryState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LIBRARIES):
    case REQUEST(ACTION_TYPES.FETCH_LIBRARY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LIBRARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LIBRARY):
    case REQUEST(ACTION_TYPES.UPDATE_LIBRARY):
    case REQUEST(ACTION_TYPES.DELETE_LIBRARY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LIBRARIES):
    case FAILURE(ACTION_TYPES.FETCH_LIBRARY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LIBRARY):
    case FAILURE(ACTION_TYPES.CREATE_LIBRARY):
    case FAILURE(ACTION_TYPES.UPDATE_LIBRARY):
    case FAILURE(ACTION_TYPES.DELETE_LIBRARY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LIBRARIES):
    case SUCCESS(ACTION_TYPES.FETCH_LIBRARY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIBRARY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LIBRARY):
    case SUCCESS(ACTION_TYPES.UPDATE_LIBRARY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LIBRARY):
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

const apiUrl = 'api/libraries';
const apiSearchUrl = 'api/_search/libraries';

// Actions

export const getSearchEntities: ICrudSearchAction<ILibrary> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_LIBRARIES,
  payload: axios.get<ILibrary>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<ILibrary> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LIBRARY_LIST,
  payload: axios.get<ILibrary>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILibrary> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LIBRARY,
    payload: axios.get<ILibrary>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILibrary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LIBRARY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILibrary> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LIBRARY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILibrary> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LIBRARY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
