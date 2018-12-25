export const enum Sem {
  SEMI = 'SEMI',
  SEMII = 'SEMII'
}

export interface ISemester {
  id?: number;
  sem?: Sem;
}

export const defaultValue: Readonly<ISemester> = {};
