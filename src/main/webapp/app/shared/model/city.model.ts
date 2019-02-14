export interface ICity {
  id?: number;
  cityName?: string;
  cityCode?: string;
  stdCode?: string;
  stateId?: number;
}

export const defaultValue: Readonly<ICity> = {};
