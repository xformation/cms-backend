import { Moment } from 'moment';

export const enum LecStatusEnum {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE',
  CANCELLED = 'CANCELLED'
}

export interface ILecture {
  id?: number;
  lecDate?: Moment;
  lastUpdatedOn?: Moment;
  lastUpdatedBy?: string;
  lecStatus?: LecStatusEnum;
  desc?: string;
  attendancemasterId?: number;
}

export const defaultValue: Readonly<ILecture> = {};
