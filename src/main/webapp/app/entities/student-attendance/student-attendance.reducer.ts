import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudentAttendance, defaultValue } from 'app/shared/model/student-attendance.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTATTENDANCES: 'studentAttendance/SEARCH_STUDENTATTENDANCES',
  FETCH_STUDENTATTENDANCE_LIST: 'studentAttendance/FETCH_STUDENTATTENDANCE_LIST',
  FETCH_STUDENTATTENDANCE: 'studentAttendance/FETCH_STUDENTATTENDANCE',
  CREATE_STUDENTATTENDANCE: 'studentAttendance/CREATE_STUDENTATTENDANCE',
  UPDATE_STUDENTATTENDANCE: 'studentAttendance/UPDATE_STUDENTATTENDANCE',
  DELETE_STUDENTATTENDANCE: 'studentAttendance/DELETE_STUDENTATTENDANCE',
  RESET: 'studentAttendance/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentAttendance>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentAttendanceState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentAttendanceState = initialState, action): StudentAttendanceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTATTENDANCES):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTATTENDANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTATTENDANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTATTENDANCE):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTATTENDANCE):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTATTENDANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTATTENDANCES):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTATTENDANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTATTENDANCE):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTATTENDANCE):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTATTENDANCE):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTATTENDANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTATTENDANCES):
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTATTENDANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTATTENDANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTATTENDANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTATTENDANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTATTENDANCE):
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

const apiUrl = 'api/student-attendances';
const apiSearchUrl = 'api/_search/student-attendances';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentAttendance> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_STUDENTATTENDANCES,
  payload: axios.get<IStudentAttendance>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IStudentAttendance> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTATTENDANCE_LIST,
  payload: axios.get<IStudentAttendance>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentAttendance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTATTENDANCE,
    payload: axios.get<IStudentAttendance>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentAttendance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTATTENDANCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentAttendance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTATTENDANCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentAttendance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTATTENDANCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
