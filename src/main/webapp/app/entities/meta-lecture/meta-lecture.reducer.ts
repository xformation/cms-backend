import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import { SERVER_API_URL } from 'app/config/constants';

import { IMetaLecture, defaultValue } from 'app/shared/model/meta-lecture.model';

export const ACTION_TYPES = {
  SEARCH_METALECTURES: 'metaLecture/SEARCH_METALECTURES',
  FETCH_METALECTURE_LIST: 'metaLecture/FETCH_METALECTURE_LIST',
  FETCH_METALECTURE: 'metaLecture/FETCH_METALECTURE',
  CREATE_METALECTURE: 'metaLecture/CREATE_METALECTURE',
  UPDATE_METALECTURE: 'metaLecture/UPDATE_METALECTURE',
  DELETE_METALECTURE: 'metaLecture/DELETE_METALECTURE',
  RESET: 'metaLecture/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMetaLecture>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MetaLectureState = Readonly<typeof initialState>;

// Reducer

export default (state: MetaLectureState = initialState, action): MetaLectureState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_METALECTURES):
    case REQUEST(ACTION_TYPES.FETCH_METALECTURE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_METALECTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_METALECTURE):
    case REQUEST(ACTION_TYPES.UPDATE_METALECTURE):
    case REQUEST(ACTION_TYPES.DELETE_METALECTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_METALECTURES):
    case FAILURE(ACTION_TYPES.FETCH_METALECTURE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_METALECTURE):
    case FAILURE(ACTION_TYPES.CREATE_METALECTURE):
    case FAILURE(ACTION_TYPES.UPDATE_METALECTURE):
    case FAILURE(ACTION_TYPES.DELETE_METALECTURE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_METALECTURES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_METALECTURE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_METALECTURE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_METALECTURE):
    case SUCCESS(ACTION_TYPES.UPDATE_METALECTURE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_METALECTURE):
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

const apiUrl = SERVER_API_URL + '/api/meta-lectures';
const apiSearchUrl = SERVER_API_URL + '/api/_search/meta-lectures';

// Actions

export const getSearchEntities: ICrudSearchAction<IMetaLecture> = query => ({
  type: ACTION_TYPES.SEARCH_METALECTURES,
  payload: axios.get<IMetaLecture>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IMetaLecture> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_METALECTURE_LIST,
  payload: axios.get<IMetaLecture>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMetaLecture> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_METALECTURE,
    payload: axios.get<IMetaLecture>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMetaLecture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_METALECTURE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMetaLecture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_METALECTURE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMetaLecture> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_METALECTURE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
