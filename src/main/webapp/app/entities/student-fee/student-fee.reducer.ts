import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IStudentFee, defaultValue } from 'app/shared/model/student-fee.model';

export const ACTION_TYPES = {
  SEARCH_STUDENTFEES: 'studentFee/SEARCH_STUDENTFEES',
  FETCH_STUDENTFEE_LIST: 'studentFee/FETCH_STUDENTFEE_LIST',
  FETCH_STUDENTFEE: 'studentFee/FETCH_STUDENTFEE',
  CREATE_STUDENTFEE: 'studentFee/CREATE_STUDENTFEE',
  UPDATE_STUDENTFEE: 'studentFee/UPDATE_STUDENTFEE',
  DELETE_STUDENTFEE: 'studentFee/DELETE_STUDENTFEE',
  RESET: 'studentFee/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IStudentFee>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type StudentFeeState = Readonly<typeof initialState>;

// Reducer

export default (state: StudentFeeState = initialState, action): StudentFeeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_STUDENTFEES):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTFEE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_STUDENTFEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_STUDENTFEE):
    case REQUEST(ACTION_TYPES.UPDATE_STUDENTFEE):
    case REQUEST(ACTION_TYPES.DELETE_STUDENTFEE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_STUDENTFEES):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTFEE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_STUDENTFEE):
    case FAILURE(ACTION_TYPES.CREATE_STUDENTFEE):
    case FAILURE(ACTION_TYPES.UPDATE_STUDENTFEE):
    case FAILURE(ACTION_TYPES.DELETE_STUDENTFEE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_STUDENTFEES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTFEE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_STUDENTFEE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_STUDENTFEE):
    case SUCCESS(ACTION_TYPES.UPDATE_STUDENTFEE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_STUDENTFEE):
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

const apiUrl = 'api/student-fees';
const apiSearchUrl = 'api/_search/student-fees';

// Actions

export const getSearchEntities: ICrudSearchAction<IStudentFee> = query => ({
  type: ACTION_TYPES.SEARCH_STUDENTFEES,
  payload: axios.get<IStudentFee>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IStudentFee> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_STUDENTFEE_LIST,
  payload: axios.get<IStudentFee>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IStudentFee> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_STUDENTFEE,
    payload: axios.get<IStudentFee>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IStudentFee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_STUDENTFEE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IStudentFee> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_STUDENTFEE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IStudentFee> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_STUDENTFEE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
