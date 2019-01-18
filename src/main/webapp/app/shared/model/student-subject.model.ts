import { Moment } from 'moment';

export interface IStudentSubject {
  id?: number;
  comments?: string;
  lastupdatedDate?: Moment;
  studentId?: number;
  subjectId?: number;
}

export const defaultValue: Readonly<IStudentSubject> = {};
