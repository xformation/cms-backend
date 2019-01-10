export const enum CommonSubEnum {
  MATHS = 'MATHS',
  PHYSICS = 'PHYSICS',
  CHEMISTRY = 'CHEMISTRY',
  DBMS = 'DBMS'
}

export const enum ElectiveEnum {
  JAVA = 'JAVA',
  C = 'C'
}

export interface ISubject {
  id?: number;
  commonSub?: CommonSubEnum;
  electiveSub?: ElectiveEnum;
  departmentId?: number;
  studentId?: number;
  teacherId?: number;
}

export const defaultValue: Readonly<ISubject> = {};
