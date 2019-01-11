export interface ICollege {
  id?: number;
  shortName?: string;
  logo?: number;
  backgroundImage?: number;
  instructionInformation?: string;
  branchId?: number;
}

export const defaultValue: Readonly<ICollege> = {};
