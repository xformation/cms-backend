import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IStudentAttendance {
  id?: number;
  attendanceDate?: Moment;
  sName?: string;
  status?: Status;
  comments?: string;
  studentYearId?: number;
  departmentsId?: number;
  subjectId?: number;
  semesterId?: number;
  sectionId?: number;
  periodsId?: number;
  studentId?: number;
}

export const defaultValue: Readonly<IStudentAttendance> = {};
