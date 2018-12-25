export interface ICollege {
  id?: number;
  shortName?: string;
  logo?: number;
  backgroundImage?: number;
  instructionInformation?: string;
}

export const defaultValue: Readonly<ICollege> = {};
