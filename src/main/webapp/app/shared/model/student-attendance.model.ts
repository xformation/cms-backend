import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT',
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IStudentAttendance {
  id?: number;
  attendanceDate?: Moment;
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
