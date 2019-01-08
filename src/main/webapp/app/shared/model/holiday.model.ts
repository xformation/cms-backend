import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IHoliday {
  id?: number;
  srNo?: number;
  sHoliday?: string;
  aDate?: Moment;
  status?: Status;
}

export const defaultValue: Readonly<IHoliday> = {};
