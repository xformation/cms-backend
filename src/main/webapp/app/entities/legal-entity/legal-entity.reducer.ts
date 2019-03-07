import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILegalEntity, defaultValue } from 'app/shared/model/legal-entity.model';

export const ACTION_TYPES = {
  SEARCH_LEGALENTITIES: 'legalEntity/SEARCH_LEGALENTITIES',
  FETCH_LEGALENTITY_LIST: 'legalEntity/FETCH_LEGALENTITY_LIST',
  FETCH_LEGALENTITY: 'legalEntity/FETCH_LEGALENTITY',
  CREATE_LEGALENTITY: 'legalEntity/CREATE_LEGALENTITY',
  UPDATE_LEGALENTITY: 'legalEntity/UPDATE_LEGALENTITY',
  DELETE_LEGALENTITY: 'legalEntity/DELETE_LEGALENTITY',
  RESET: 'legalEntity/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILegalEntity>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LegalEntityState = Readonly<typeof initialState>;

// Reducer

export default (state: LegalEntityState = initialState, action): LegalEntityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LEGALENTITIES):
    case REQUEST(ACTION_TYPES.FETCH_LEGALENTITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LEGALENTITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LEGALENTITY):
    case REQUEST(ACTION_TYPES.UPDATE_LEGALENTITY):
    case REQUEST(ACTION_TYPES.DELETE_LEGALENTITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LEGALENTITIES):
    case FAILURE(ACTION_TYPES.FETCH_LEGALENTITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LEGALENTITY):
    case FAILURE(ACTION_TYPES.CREATE_LEGALENTITY):
    case FAILURE(ACTION_TYPES.UPDATE_LEGALENTITY):
    case FAILURE(ACTION_TYPES.DELETE_LEGALENTITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LEGALENTITIES):
    case SUCCESS(ACTION_TYPES.FETCH_LEGALENTITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LEGALENTITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LEGALENTITY):
    case SUCCESS(ACTION_TYPES.UPDATE_LEGALENTITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LEGALENTITY):
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

const apiUrl = 'api/legal-entities';
const apiSearchUrl = 'api/_search/legal-entities';

// Actions

export const getSearchEntities: ICrudSearchAction<ILegalEntity> = query => ({
  type: ACTION_TYPES.SEARCH_LEGALENTITIES,
  payload: axios.get<ILegalEntity>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILegalEntity> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LEGALENTITY_LIST,
  payload: axios.get<ILegalEntity>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILegalEntity> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LEGALENTITY,
    payload: axios.get<ILegalEntity>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILegalEntity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LEGALENTITY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILegalEntity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LEGALENTITY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILegalEntity> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LEGALENTITY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
