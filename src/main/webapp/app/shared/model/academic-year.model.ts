import { Moment } from 'moment';

export interface IAcademicYear {
  id?: number;
  year?: number;
  startDate?: Moment;
  endDate?: Moment;
  holidayId?: number;
  termId?: number;
}

export const defaultValue: Readonly<IAcademicYear> = {};
