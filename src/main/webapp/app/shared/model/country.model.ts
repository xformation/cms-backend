export interface ICountry {
  id?: number;
  countryName?: string;
  countryCode?: string;
  isdCode?: string;
}

export const defaultValue: Readonly<ICountry> = {};
