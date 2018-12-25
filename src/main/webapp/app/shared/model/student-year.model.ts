export const enum SYear {
  I = 'I',
  II = 'II',
  III = 'III',
  IV = 'IV'
}

export interface IStudentYear {
  id?: number;
  sYear?: SYear;
}

export const defaultValue: Readonly<IStudentYear> = {};
