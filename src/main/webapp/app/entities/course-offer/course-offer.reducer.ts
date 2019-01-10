import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICourseOffer, defaultValue } from 'app/shared/model/course-offer.model';

export const ACTION_TYPES = {
  SEARCH_COURSEOFFERS: 'courseOffer/SEARCH_COURSEOFFERS',
  FETCH_COURSEOFFER_LIST: 'courseOffer/FETCH_COURSEOFFER_LIST',
  FETCH_COURSEOFFER: 'courseOffer/FETCH_COURSEOFFER',
  CREATE_COURSEOFFER: 'courseOffer/CREATE_COURSEOFFER',
  UPDATE_COURSEOFFER: 'courseOffer/UPDATE_COURSEOFFER',
  DELETE_COURSEOFFER: 'courseOffer/DELETE_COURSEOFFER',
  RESET: 'courseOffer/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICourseOffer>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CourseOfferState = Readonly<typeof initialState>;

// Reducer

export default (state: CourseOfferState = initialState, action): CourseOfferState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_COURSEOFFERS):
    case REQUEST(ACTION_TYPES.FETCH_COURSEOFFER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COURSEOFFER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COURSEOFFER):
    case REQUEST(ACTION_TYPES.UPDATE_COURSEOFFER):
    case REQUEST(ACTION_TYPES.DELETE_COURSEOFFER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_COURSEOFFERS):
    case FAILURE(ACTION_TYPES.FETCH_COURSEOFFER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COURSEOFFER):
    case FAILURE(ACTION_TYPES.CREATE_COURSEOFFER):
    case FAILURE(ACTION_TYPES.UPDATE_COURSEOFFER):
    case FAILURE(ACTION_TYPES.DELETE_COURSEOFFER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_COURSEOFFERS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COURSEOFFER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COURSEOFFER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COURSEOFFER):
    case SUCCESS(ACTION_TYPES.UPDATE_COURSEOFFER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COURSEOFFER):
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

const apiUrl = 'api/course-offers';
const apiSearchUrl = 'api/_search/course-offers';

// Actions

export const getSearchEntities: ICrudSearchAction<ICourseOffer> = query => ({
  type: ACTION_TYPES.SEARCH_COURSEOFFERS,
  payload: axios.get<ICourseOffer>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ICourseOffer> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COURSEOFFER_LIST,
  payload: axios.get<ICourseOffer>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICourseOffer> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COURSEOFFER,
    payload: axios.get<ICourseOffer>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICourseOffer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COURSEOFFER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICourseOffer> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COURSEOFFER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICourseOffer> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COURSEOFFER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
