import { Moment } from 'moment';

export interface IStudentExamReport {
  id?: number;
  marksObtained?: number;
  comments?: string;
  createdOn?: Moment;
  createdBy?: string;
  updatedOn?: Moment;
  updatedBy?: string;
  academicExamSettingId?: number;
  studentId?: number;
  typeOfGradingId?: number;
  batchId?: number;
  academicyearId?: number;
}

export const defaultValue: Readonly<IStudentExamReport> = {};
