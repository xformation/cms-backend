import { Moment } from 'moment';

export const enum TypeOfOwnerShip {
  COMPANYOWNED = 'COMPANYOWNED',
  CONTRACTUAL = 'CONTRACTUAL'
}

export interface IContract {
  id?: number;
  vendorName?: string;
  typeOfOwnerShip?: TypeOfOwnerShip;
  durationOfContract?: string;
  startDate?: Moment;
  endDate?: Moment;
}

export const defaultValue: Readonly<IContract> = {};
