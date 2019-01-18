export const enum SubTypeEnum {
  COMMON = 'COMMON',
  ELECTIVE = 'ELECTIVE'
}

export interface ISubject {
  id?: number;
  subjectCode?: string;
  subjectType?: SubTypeEnum;
  subjectDesc?: string;
  departmentId?: number;
  teacherId?: number;
}

export const defaultValue: Readonly<ISubject> = {};
