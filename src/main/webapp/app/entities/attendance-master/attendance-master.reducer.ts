import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAttendanceMaster, defaultValue } from 'app/shared/model/attendance-master.model';

export const ACTION_TYPES = {
  SEARCH_ATTENDANCEMASTERS: 'attendanceMaster/SEARCH_ATTENDANCEMASTERS',
  FETCH_ATTENDANCEMASTER_LIST: 'attendanceMaster/FETCH_ATTENDANCEMASTER_LIST',
  FETCH_ATTENDANCEMASTER: 'attendanceMaster/FETCH_ATTENDANCEMASTER',
  CREATE_ATTENDANCEMASTER: 'attendanceMaster/CREATE_ATTENDANCEMASTER',
  UPDATE_ATTENDANCEMASTER: 'attendanceMaster/UPDATE_ATTENDANCEMASTER',
  DELETE_ATTENDANCEMASTER: 'attendanceMaster/DELETE_ATTENDANCEMASTER',
  RESET: 'attendanceMaster/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAttendanceMaster>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AttendanceMasterState = Readonly<typeof initialState>;

// Reducer

export default (state: AttendanceMasterState = initialState, action): AttendanceMasterState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_ATTENDANCEMASTERS):
    case REQUEST(ACTION_TYPES.FETCH_ATTENDANCEMASTER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ATTENDANCEMASTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ATTENDANCEMASTER):
    case REQUEST(ACTION_TYPES.UPDATE_ATTENDANCEMASTER):
    case REQUEST(ACTION_TYPES.DELETE_ATTENDANCEMASTER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_ATTENDANCEMASTERS):
    case FAILURE(ACTION_TYPES.FETCH_ATTENDANCEMASTER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ATTENDANCEMASTER):
    case FAILURE(ACTION_TYPES.CREATE_ATTENDANCEMASTER):
    case FAILURE(ACTION_TYPES.UPDATE_ATTENDANCEMASTER):
    case FAILURE(ACTION_TYPES.DELETE_ATTENDANCEMASTER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_ATTENDANCEMASTERS):
    case SUCCESS(ACTION_TYPES.FETCH_ATTENDANCEMASTER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ATTENDANCEMASTER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ATTENDANCEMASTER):
    case SUCCESS(ACTION_TYPES.UPDATE_ATTENDANCEMASTER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ATTENDANCEMASTER):
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

const apiUrl = 'api/attendance-masters';
const apiSearchUrl = 'api/_search/attendance-masters';

// Actions

export const getSearchEntities: ICrudSearchAction<IAttendanceMaster> = query => ({
  type: ACTION_TYPES.SEARCH_ATTENDANCEMASTERS,
  payload: axios.get<IAttendanceMaster>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IAttendanceMaster> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ATTENDANCEMASTER_LIST,
  payload: axios.get<IAttendanceMaster>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAttendanceMaster> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ATTENDANCEMASTER,
    payload: axios.get<IAttendanceMaster>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAttendanceMaster> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ATTENDANCEMASTER,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAttendanceMaster> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ATTENDANCEMASTER,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAttendanceMaster> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ATTENDANCEMASTER,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
