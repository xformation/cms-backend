export const enum SectionEnum {
  A = 'A',
  B = 'B',
  C = 'C',
  D = 'D'
}

export interface ISection {
  id?: number;
  section?: SectionEnum;
  batchId?: number;
}

export const defaultValue: Readonly<ISection> = {};
