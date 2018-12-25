export const enum Common {
  MATHS = 'MATHS',
  PHYSICS = 'PHYSICS',
  CHEMISTRY = 'CHEMISTRY',
  DBMS = 'DBMS'
}

export const enum Elective {
  JAVA = 'JAVA',
  C = 'C'
}

export interface ISubject {
  id?: number;
  commonSub?: Common;
  electiveSub?: Elective;
  periodsId?: number;
  studentId?: number;
  teacherId?: number;
}

export const defaultValue: Readonly<ISubject> = {};
