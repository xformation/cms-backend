import { Moment } from 'moment';

export interface IAcademicYear {
  id?: number;
  year?: number;
  startDate?: Moment;
  endDate?: Moment;
  desc?: string;
}

export const defaultValue: Readonly<IAcademicYear> = {};
