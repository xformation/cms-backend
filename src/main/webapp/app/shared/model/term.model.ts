import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface ITerm {
  id?: number;
  termsDesc?: string;
  startDate?: Moment;
  endDate?: Moment;
  termStatus?: Status;
  academicyearId?: number;
}

export const defaultValue: Readonly<ITerm> = {};
