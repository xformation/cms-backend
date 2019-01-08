import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface ITerm {
  id?: number;
  srNo?: number;
  aTerms?: string;
  startDate?: Moment;
  endDate?: Moment;
  status?: Status;
}

export const defaultValue: Readonly<ITerm> = {};
