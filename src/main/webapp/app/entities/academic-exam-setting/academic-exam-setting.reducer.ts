import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAcademicExamSetting, defaultValue } from 'app/shared/model/academic-exam-setting.model';

export const ACTION_TYPES = {
  SEARCH_ACADEMICEXAMSETTINGS: 'academicExamSetting/SEARCH_ACADEMICEXAMSETTINGS',
  FETCH_ACADEMICEXAMSETTING_LIST: 'academicExamSetting/FETCH_ACADEMICEXAMSETTING_LIST',
  FETCH_ACADEMICEXAMSETTING: 'academicExamSetting/FETCH_ACADEMICEXAMSETTING',
  CREATE_ACADEMICEXAMSETTING: 'academicExamSetting/CREATE_ACADEMICEXAMSETTING',
  UPDATE_ACADEMICEXAMSETTING: 'academicExamSetting/UPDATE_ACADEMICEXAMSETTING',
  DELETE_ACADEMICEXAMSETTING: 'academicExamSetting/DELETE_ACADEMICEXAMSETTING',
  RESET: 'academicExamSetting/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAcademicExamSetting>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AcademicExamSettingState = Readonly<typeof initialState>;

// Reducer

export default (state: AcademicExamSettingState = initialState, action): AcademicExamSettingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ACADEMICEXAMSETTINGS):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACADEMICEXAMSETTING):
    case REQUEST(ACTION_TYPES.UPDATE_ACADEMICEXAMSETTING):
    case REQUEST(ACTION_TYPES.DELETE_ACADEMICEXAMSETTING):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ACADEMICEXAMSETTINGS):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING):
    case FAILURE(ACTION_TYPES.CREATE_ACADEMICEXAMSETTING):
    case FAILURE(ACTION_TYPES.UPDATE_ACADEMICEXAMSETTING):
    case FAILURE(ACTION_TYPES.DELETE_ACADEMICEXAMSETTING):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ACADEMICEXAMSETTINGS):
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ACADEMICEXAMSETTING):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACADEMICEXAMSETTING):
    case SUCCESS(ACTION_TYPES.UPDATE_ACADEMICEXAMSETTING):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ACADEMICEXAMSETTING):
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

const apiUrl = 'api/academic-exam-settings';
const apiSearchUrl = 'api/_search/academic-exam-settings';

// Actions

export const getSearchEntities: ICrudSearchAction<IAcademicExamSetting> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_ACADEMICEXAMSETTINGS,
  payload: axios.get<IAcademicExamSetting>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IAcademicExamSetting> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ACADEMICEXAMSETTING_LIST,
  payload: axios.get<IAcademicExamSetting>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAcademicExamSetting> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ACADEMICEXAMSETTING,
    payload: axios.get<IAcademicExamSetting>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAcademicExamSetting> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ACADEMICEXAMSETTING,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAcademicExamSetting> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ACADEMICEXAMSETTING,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAcademicExamSetting> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ACADEMICEXAMSETTING,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
