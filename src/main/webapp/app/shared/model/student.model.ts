export const enum Elective {
  JAVA = 'JAVA',
  C = 'C'
}

export interface IStudent {
  id?: number;
  sName?: string;
  attendance?: boolean;
  electiveSub?: Elective;
  teacherId?: number;
}

export const defaultValue: Readonly<IStudent> = {
  attendance: false
};