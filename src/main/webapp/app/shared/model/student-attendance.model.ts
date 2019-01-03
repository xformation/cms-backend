import { Moment } from 'moment';

export const enum Status {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT'
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
