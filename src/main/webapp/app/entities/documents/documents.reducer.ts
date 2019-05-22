import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDocuments, defaultValue } from 'app/shared/model/documents.model';

export const ACTION_TYPES = {
  SEARCH_DOCUMENTS: 'documents/SEARCH_DOCUMENTS',
  FETCH_DOCUMENTS_LIST: 'documents/FETCH_DOCUMENTS_LIST',
  FETCH_DOCUMENTS: 'documents/FETCH_DOCUMENTS',
  CREATE_DOCUMENTS: 'documents/CREATE_DOCUMENTS',
  UPDATE_DOCUMENTS: 'documents/UPDATE_DOCUMENTS',
  DELETE_DOCUMENTS: 'documents/DELETE_DOCUMENTS',
  RESET: 'documents/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDocuments>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DocumentsState = Readonly<typeof initialState>;

// Reducer

export default (state: DocumentsState = initialState, action): DocumentsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DOCUMENTS):
    case REQUEST(ACTION_TYPES.FETCH_DOCUMENTS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DOCUMENTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DOCUMENTS):
    case REQUEST(ACTION_TYPES.UPDATE_DOCUMENTS):
    case REQUEST(ACTION_TYPES.DELETE_DOCUMENTS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DOCUMENTS):
    case FAILURE(ACTION_TYPES.FETCH_DOCUMENTS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DOCUMENTS):
    case FAILURE(ACTION_TYPES.CREATE_DOCUMENTS):
    case FAILURE(ACTION_TYPES.UPDATE_DOCUMENTS):
    case FAILURE(ACTION_TYPES.DELETE_DOCUMENTS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DOCUMENTS):
    case SUCCESS(ACTION_TYPES.FETCH_DOCUMENTS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DOCUMENTS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DOCUMENTS):
    case SUCCESS(ACTION_TYPES.UPDATE_DOCUMENTS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DOCUMENTS):
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

const apiUrl = 'api/documents';
const apiSearchUrl = 'api/_search/documents';

// Actions

export const getSearchEntities: ICrudSearchAction<IDocuments> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_DOCUMENTS,
  payload: axios.get<IDocuments>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IDocuments> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DOCUMENTS_LIST,
  payload: axios.get<IDocuments>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDocuments> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DOCUMENTS,
    payload: axios.get<IDocuments>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDocuments> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DOCUMENTS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDocuments> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DOCUMENTS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDocuments> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DOCUMENTS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
