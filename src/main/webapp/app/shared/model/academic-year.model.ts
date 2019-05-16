import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IAcademicYear {
  id?: number;
  year?: string;
  startDate?: Moment;
  endDate?: Moment;
  status?: Status;
}

export const defaultValue: Readonly<IAcademicYear> = {};
