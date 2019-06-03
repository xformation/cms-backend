import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IStudentFacilityLink, defaultValue } from 'app/shared/model/student-facility-link.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTFACILITYLINKS: 'studentFacilityLink/SEARCH_STUDENTFACILITYLINKS',
  FETCH_STUDENTFACILITYLINK_LIST: 'studentFacilityLink/FETCH_STUDENTFACILITYLINK_LIST',
  FETCH_STUDENTFACILITYLINK: 'studentFacilityLink/FETCH_STUDENTFACILITYLINK',
  CREATE_STUDENTFACILITYLINK: 'studentFacilityLink/CREATE_STUDENTFACILITYLINK',
  UPDATE_STUDENTFACILITYLINK: 'studentFacilityLink/UPDATE_STUDENTFACILITYLINK',
  DELETE_STUDENTFACILITYLINK: 'studentFacilityLink/DELETE_STUDENTFACILITYLINK',
  RESET: 'studentFacilityLink/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentFacilityLink>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentFacilityLinkState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentFacilityLinkState = initialState, action): StudentFacilityLinkState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTFACILITYLINKS):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTFACILITYLINK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTFACILITYLINK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTFACILITYLINK):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTFACILITYLINK):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTFACILITYLINK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTFACILITYLINKS):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTFACILITYLINK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTFACILITYLINK):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTFACILITYLINK):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTFACILITYLINK):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTFACILITYLINK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTFACILITYLINKS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTFACILITYLINK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTFACILITYLINK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTFACILITYLINK):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTFACILITYLINK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTFACILITYLINK):
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

const apiUrl = SERVER_API_URL + '/api/student-facility-links';
const apiSearchUrl = SERVER_API_URL + '/api/_search/student-facility-links';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentFacilityLink> = query => ({
  type: ACTION_TYPES.SEARCH_STUDENTFACILITYLINKS,
  payload: axios.get<IStudentFacilityLink>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IStudentFacilityLink> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTFACILITYLINK_LIST,
  payload: axios.get<IStudentFacilityLink>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentFacilityLink> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTFACILITYLINK,
    payload: axios.get<IStudentFacilityLink>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentFacilityLink> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTFACILITYLINK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentFacilityLink> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTFACILITYLINK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentFacilityLink> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTFACILITYLINK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
