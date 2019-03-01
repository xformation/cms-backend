import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISection, defaultValue } from 'app/shared/model/section.model';

export const ACTION_TYPES = {
  SEARCH_SECTIONS: 'section/SEARCH_SECTIONS',
  FETCH_SECTION_LIST: 'section/FETCH_SECTION_LIST',
  FETCH_SECTION: 'section/FETCH_SECTION',
  CREATE_SECTION: 'section/CREATE_SECTION',
  UPDATE_SECTION: 'section/UPDATE_SECTION',
  DELETE_SECTION: 'section/DELETE_SECTION',
  RESET: 'section/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISection>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SectionState = Readonly<typeof initialState>;

// Reducer

export default (state: SectionState = initialState, action): SectionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_SECTIONS):
    case REQUEST(ACTION_TYPES.FETCH_SECTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SECTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SECTION):
    case REQUEST(ACTION_TYPES.UPDATE_SECTION):
    case REQUEST(ACTION_TYPES.DELETE_SECTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_SECTIONS):
    case FAILURE(ACTION_TYPES.FETCH_SECTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SECTION):
    case FAILURE(ACTION_TYPES.CREATE_SECTION):
    case FAILURE(ACTION_TYPES.UPDATE_SECTION):
    case FAILURE(ACTION_TYPES.DELETE_SECTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_SECTIONS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SECTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SECTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SECTION):
    case SUCCESS(ACTION_TYPES.UPDATE_SECTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SECTION):
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

const apiUrl = 'api/sections';
const apiSearchUrl = 'api/_search/sections';

// Actions

export const getSearchEntities: ICrudSearchAction<ISection> = query => ({
  type: ACTION_TYPES.SEARCH_SECTIONS,
  payload: axios.get<ISection>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ISection> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SECTION_LIST,
  payload: axios.get<ISection>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISection> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SECTION,
    payload: axios.get<ISection>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISection> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SECTION,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISection> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SECTION,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISection> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SECTION,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
