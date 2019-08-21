import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBook, defaultValue } from 'app/shared/model/book.model';

export const ACTION_TYPES = {
  SEARCH_BOOKS: 'book/SEARCH_BOOKS',
  FETCH_BOOK_LIST: 'book/FETCH_BOOK_LIST',
  FETCH_BOOK: 'book/FETCH_BOOK',
  CREATE_BOOK: 'book/CREATE_BOOK',
  UPDATE_BOOK: 'book/UPDATE_BOOK',
  DELETE_BOOK: 'book/DELETE_BOOK',
  RESET: 'book/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBook>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BookState = Readonly<typeof initialState>;

// Reducer

export default (state: BookState = initialState, action): BookState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_BOOKS):
    case REQUEST(ACTION_TYPES.FETCH_BOOK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BOOK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BOOK):
    case REQUEST(ACTION_TYPES.UPDATE_BOOK):
    case REQUEST(ACTION_TYPES.DELETE_BOOK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_BOOKS):
    case FAILURE(ACTION_TYPES.FETCH_BOOK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BOOK):
    case FAILURE(ACTION_TYPES.CREATE_BOOK):
    case FAILURE(ACTION_TYPES.UPDATE_BOOK):
    case FAILURE(ACTION_TYPES.DELETE_BOOK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_BOOKS):
    case SUCCESS(ACTION_TYPES.FETCH_BOOK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BOOK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BOOK):
    case SUCCESS(ACTION_TYPES.UPDATE_BOOK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BOOK):
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

const apiUrl = 'api/books';
const apiSearchUrl = 'api/_search/books';

// Actions

export const getSearchEntities: ICrudSearchAction<IBook> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_BOOKS,
  payload: axios.get<IBook>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IBook> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BOOK_LIST,
  payload: axios.get<IBook>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBook> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BOOK,
    payload: axios.get<IBook>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBook> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BOOK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBook> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BOOK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBook> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BOOK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
