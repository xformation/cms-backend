export const enum GradesEnum {
  Aplus = 'Aplus',
  A = 'A',
  Bplus = 'Bplus',
  B = 'B',
  Cplus = 'Cplus',
  C = 'C'
}

export interface ITypeOfGrading {
  id?: number;
  minMarks?: number;
  maxMarks?: number;
  grades?: GradesEnum;
  academicExamSettingId?: number;
}

export const defaultValue: Readonly<ITypeOfGrading> = {};
