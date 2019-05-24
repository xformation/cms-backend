import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILecture, defaultValue } from 'app/shared/model/lecture.model';

export const ACTION_TYPES = {
  SEARCH_LECTURES: 'lecture/SEARCH_LECTURES',
  FETCH_LECTURE_LIST: 'lecture/FETCH_LECTURE_LIST',
  FETCH_LECTURE: 'lecture/FETCH_LECTURE',
  CREATE_LECTURE: 'lecture/CREATE_LECTURE',
  UPDATE_LECTURE: 'lecture/UPDATE_LECTURE',
  DELETE_LECTURE: 'lecture/DELETE_LECTURE',
  RESET: 'lecture/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILecture>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LectureState = Readonly<typeof initialState>;

// Reducer

export default (state: LectureState = initialState, action): LectureState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LECTURES):
    case REQUEST(ACTION_TYPES.FETCH_LECTURE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LECTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LECTURE):
    case REQUEST(ACTION_TYPES.UPDATE_LECTURE):
    case REQUEST(ACTION_TYPES.DELETE_LECTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LECTURES):
    case FAILURE(ACTION_TYPES.FETCH_LECTURE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LECTURE):
    case FAILURE(ACTION_TYPES.CREATE_LECTURE):
    case FAILURE(ACTION_TYPES.UPDATE_LECTURE):
    case FAILURE(ACTION_TYPES.DELETE_LECTURE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LECTURES):
    case SUCCESS(ACTION_TYPES.FETCH_LECTURE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LECTURE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LECTURE):
    case SUCCESS(ACTION_TYPES.UPDATE_LECTURE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LECTURE):
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

const apiUrl = 'api/lectures';
const apiSearchUrl = 'api/_search/lectures';

// Actions

export const getSearchEntities: ICrudSearchAction<ILecture> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_LECTURES,
  payload: axios.get<ILecture>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<ILecture> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LECTURE_LIST,
  payload: axios.get<ILecture>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILecture> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LECTURE,
    payload: axios.get<ILecture>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILecture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LECTURE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILecture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LECTURE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILecture> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LECTURE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
