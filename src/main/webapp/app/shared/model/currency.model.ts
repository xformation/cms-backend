export interface ICurrency {
  id?: number;
  currencyName?: string;
  currencyCode?: string;
  currencySymbol?: string;
  countryId?: number;
}

export const defaultValue: Readonly<ICurrency> = {};
