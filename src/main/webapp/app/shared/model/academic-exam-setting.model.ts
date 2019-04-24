import { Moment } from 'moment';

export const enum SemesterEnum {
  SEMESTER1 = 'SEMESTER1',
  SEMESTER2 = 'SEMESTER2',
  SEMESTER3 = 'SEMESTER3',
  SEMESTER4 = 'SEMESTER4',
  SEMESTER5 = 'SEMESTER5',
  SEMESTER6 = 'SEMESTER6',
  SEMESTER7 = 'SEMESTER7',
  SEMESTER8 = 'SEMESTER8'
}

export interface IAcademicExamSetting {
  id?: number;
  examType?: string;
  semester?: SemesterEnum;
  subject?: string;
  date?: Moment;
  day?: string;
  duration?: string;
  startTime?: string;
  endTime?: string;
  total?: number;
  passing?: number;
  actions?: string;
  departmentId?: number;
  academicYearId?: number;
  sectionId?: number;
}

export const defaultValue: Readonly<IAcademicExamSetting> = {};
