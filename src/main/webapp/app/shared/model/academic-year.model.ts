import { Moment } from 'moment';

export interface IAcademicYear {
  id?: number;
  year?: number;
  startDate?: Moment;
  endDate?: Moment;
}

export const defaultValue: Readonly<IAcademicYear> = {};
