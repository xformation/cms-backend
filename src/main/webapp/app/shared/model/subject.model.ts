export const enum SubTypeEnum {
  COMMON = 'COMMON',
  ELECTIVE = 'ELECTIVE'
}

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface ISubject {
  id?: number;
  subjectCode?: string;
  subjectType?: SubTypeEnum;
  subjectDesc?: string;
  status?: Status;
  departmentId?: number;
  batchId?: number;
}

export const defaultValue: Readonly<ISubject> = {};
