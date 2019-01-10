export const enum AttendanceStatusEnum {
  PRESENT = 'PRESENT',
  ABSENT = 'ABSENT'
}

export interface IStudentAttendance {
  id?: number;
  attendanceStatus?: AttendanceStatusEnum;
  comments?: string;
  studentId?: number;
  lectureId?: number;
}

export const defaultValue: Readonly<IStudentAttendance> = {};
