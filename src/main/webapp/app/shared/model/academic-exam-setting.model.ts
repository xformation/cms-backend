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

export const enum GradeType {
  PERCENTAGE = 'PERCENTAGE',
  GRADE = 'GRADE'
}

export interface IAcademicExamSetting {
  id?: number;
  examType?: string;
  semester?: SemesterEnum;
  subject?: string;
  examDate?: Moment;
  day?: string;
  duration?: string;
  startTime?: string;
  endTime?: string;
  gradeType?: GradeType;
  total?: number;
  passing?: number;
  actions?: string;
  startDate?: Moment;
  endDate?: Moment;
  branchId?: number;
  departmentId?: number;
  academicyearId?: number;
  sectionId?: number;
  batchId?: number;
}

export const defaultValue: Readonly<IAcademicExamSetting> = {};
