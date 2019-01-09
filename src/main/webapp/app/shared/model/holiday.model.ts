import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IHoliday {
  id?: number;
  holidayDesc?: string;
  holidayDate?: Moment;
  status?: Status;
  academicYearId?: number;
}

export const defaultValue: Readonly<IHoliday> = {};
