export interface IState {
  id?: number;
  stateName?: string;
  divisionType?: string;
  stateCode?: string;
  countryId?: number;
}

export const defaultValue: Readonly<IState> = {};
