import { Moment } from 'moment';

export interface IAcademicYear {
  id?: number;
  year?: string;
  startDate?: Moment;
  endDate?: Moment;
}

export const defaultValue: Readonly<IAcademicYear> = {};
