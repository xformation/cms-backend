export const enum BatchEnum {
  FIRSTYEAR = 'FIRSTYEAR',
  SECONDYEAR = 'SECONDYEAR',
  THIRDYEAR = 'THIRDYEAR',
  FOURTHYEAR = 'FOURTHYEAR'
}

export interface IBatch {
  id?: number;
  batch?: BatchEnum;
  departmentId?: number;
}

export const defaultValue: Readonly<IBatch> = {};
