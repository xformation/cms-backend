export interface ICollege {
  id?: number;
  shortName?: string;
  logoPath?: string;
  backgroundImagePath?: string;
  instructionInformation?: string;
}

export const defaultValue: Readonly<ICollege> = {};
