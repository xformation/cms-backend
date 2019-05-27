import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudentExamReport, defaultValue } from 'app/shared/model/student-exam-report.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTEXAMREPORTS: 'studentExamReport/SEARCH_STUDENTEXAMREPORTS',
  FETCH_STUDENTEXAMREPORT_LIST: 'studentExamReport/FETCH_STUDENTEXAMREPORT_LIST',
  FETCH_STUDENTEXAMREPORT: 'studentExamReport/FETCH_STUDENTEXAMREPORT',
  CREATE_STUDENTEXAMREPORT: 'studentExamReport/CREATE_STUDENTEXAMREPORT',
  UPDATE_STUDENTEXAMREPORT: 'studentExamReport/UPDATE_STUDENTEXAMREPORT',
  DELETE_STUDENTEXAMREPORT: 'studentExamReport/DELETE_STUDENTEXAMREPORT',
  RESET: 'studentExamReport/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentExamReport>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentExamReportState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentExamReportState = initialState, action): StudentExamReportState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTEXAMREPORTS):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTEXAMREPORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTEXAMREPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTEXAMREPORT):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTEXAMREPORT):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTEXAMREPORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTEXAMREPORTS):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTEXAMREPORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTEXAMREPORT):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTEXAMREPORT):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTEXAMREPORT):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTEXAMREPORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTEXAMREPORTS):
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTEXAMREPORT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTEXAMREPORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTEXAMREPORT):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTEXAMREPORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTEXAMREPORT):
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

const apiUrl = 'api/student-exam-reports';
const apiSearchUrl = 'api/_search/student-exam-reports';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentExamReport> = (query, page, size, sort) => ({
  type: ACTION_TYPES.SEARCH_STUDENTEXAMREPORTS,
  payload: axios.get<IStudentExamReport>(`${apiSearchUrl}?query=${query}`)
});

export const getEntities: ICrudGetAllAction<IStudentExamReport> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTEXAMREPORT_LIST,
  payload: axios.get<IStudentExamReport>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentExamReport> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTEXAMREPORT,
    payload: axios.get<IStudentExamReport>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentExamReport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTEXAMREPORT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentExamReport> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTEXAMREPORT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentExamReport> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTEXAMREPORT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
