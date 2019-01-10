import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IHoliday {
  id?: number;
  holidayDesc?: string;
  holidayDate?: Moment;
  holidayStatus?: Status;
  academicyearId?: number;
}

export const defaultValue: Readonly<IHoliday> = {};
