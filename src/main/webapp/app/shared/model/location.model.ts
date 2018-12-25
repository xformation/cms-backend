export interface ILocation {
  id?: number;
  name?: string;
  address?: string;
  appliesTo?: string;
}

export const defaultValue: Readonly<ILocation> = {};
