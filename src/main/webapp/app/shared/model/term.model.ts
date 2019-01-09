import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface ITerm {
  id?: number;
  termsDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  status?: Status;
  academicYearId?: number;
}

export const defaultValue: Readonly<ITerm> = {};
