import { Moment } from 'moment';

export interface ILecture {
  id?: number;
  lecDate?: Moment;
  lastUpdatedOn?: Moment;
  lastUpdatedBy?: string;
  startTime?: string;
  endTime?: string;
  attendancemasterId?: number;
}

export const defaultValue: Readonly<ILecture> = {};
