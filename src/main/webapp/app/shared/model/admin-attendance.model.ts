import { Moment } from 'moment';

export interface IAdminAttendance {
  id?: number;
  updatedOn?: Moment;
  updatedBy?: string;
  lectureId?: number;
  branchId?: number;
  collegeId?: number;
  departmentId?: number;
  academicyearId?: number;
  sectionId?: number;
  studentId?: number;
}

export const defaultValue: Readonly<IAdminAttendance> = {};
