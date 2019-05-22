import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICompetitiveExam, defaultValue } from 'app/shared/model/competitive-exam.model';

export const ACTION_TYPES = {
  SEARCH_COMPETITIVEEXAMS: 'competitiveExam/SEARCH_COMPETITIVEEXAMS',
  FETCH_COMPETITIVEEXAM_LIST: 'competitiveExam/FETCH_COMPETITIVEEXAM_LIST',
  FETCH_COMPETITIVEEXAM: 'competitiveExam/FETCH_COMPETITIVEEXAM',
  CREATE_COMPETITIVEEXAM: 'competitiveExam/CREATE_COMPETITIVEEXAM',
  UPDATE_COMPETITIVEEXAM: 'competitiveExam/UPDATE_COMPETITIVEEXAM',
  DELETE_COMPETITIVEEXAM: 'competitiveExam/DELETE_COMPETITIVEEXAM',
  RESET: 'competitiveExam/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICompetitiveExam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CompetitiveExamState = Readonly<typeof initialState>;

// Reducer

export default (state: CompetitiveExamState = initialState, action): CompetitiveExamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_COMPETITIVEEXAMS):
    case REQUEST(ACTION_TYPES.FETCH_COMPETITIVEEXAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMPETITIVEEXAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_COMPETITIVEEXAM):
    case REQUEST(ACTION_TYPES.UPDATE_COMPETITIVEEXAM):
    case REQUEST(ACTION_TYPES.DELETE_COMPETITIVEEXAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_COMPETITIVEEXAMS):
    case FAILURE(ACTION_TYPES.FETCH_COMPETITIVEEXAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMPETITIVEEXAM):
    case FAILURE(ACTION_TYPES.CREATE_COMPETITIVEEXAM):
    case FAILURE(ACTION_TYPES.UPDATE_COMPETITIVEEXAM):
    case FAILURE(ACTION_TYPES.DELETE_COMPETITIVEEXAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_COMPETITIVEEXAMS):
    case SUCCESS(ACTION_TYPES.FETCH_COMPETITIVEEXAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMPETITIVEEXAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMPETITIVEEXAM):
    case SUCCESS(ACTION_TYPES.UPDATE_COMPETITIVEEXAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMPETITIVEEXAM):
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

const apiUrl = 'api/competitive-exams';
const apiSearchUrl = 'api/_search/competitive-exams';

// Actions

export const getSearchEntities: ICrudSearchAction<ICompetitiveExam> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_COMPETITIVEEXAMS,
  payload: axios.get<ICompetitiveExam>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<ICompetitiveExam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COMPETITIVEEXAM_LIST,
  payload: axios.get<ICompetitiveExam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICompetitiveExam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMPETITIVEEXAM,
    payload: axios.get<ICompetitiveExam>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICompetitiveExam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMPETITIVEEXAM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICompetitiveExam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMPETITIVEEXAM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICompetitiveExam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMPETITIVEEXAM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
