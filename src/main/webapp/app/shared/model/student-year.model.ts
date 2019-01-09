export const enum SYear {
  I = 'I',
  II = 'II',
  III = 'III',
  IV = 'IV'
}

export interface IStudentYear {
  id?: number;
  yearDesc?: SYear;
}

export const defaultValue: Readonly<IStudentYear> = {};
