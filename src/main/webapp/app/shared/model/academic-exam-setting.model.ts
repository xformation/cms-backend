import { Moment } from 'moment';

export interface IAcademicExamSetting {
  id?: number;
  examType?: string;
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
  attendanceMasterId?: number;
  sectionId?: number;
}

export const defaultValue: Readonly<IAcademicExamSetting> = {};
