export const enum ClassPeriods {
  ONE = 'ONE',
  TWO = 'TWO',
  THREE = 'THREE',
  FOUR = 'FOUR',
  FIVE = 'FIVE'
}

export interface IPeriods {
  id?: number;
  periods?: ClassPeriods;
  sectionId?: number;
  teacherId?: number;
}

export const defaultValue: Readonly<IPeriods> = {};
