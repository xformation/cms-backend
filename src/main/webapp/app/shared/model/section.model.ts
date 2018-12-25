export const enum ClassSection {
  A = 'A',
  B = 'B'
}

export interface ISection {
  id?: number;
  section?: ClassSection;
  studentyearId?: number;
}

export const defaultValue: Readonly<ISection> = {};
