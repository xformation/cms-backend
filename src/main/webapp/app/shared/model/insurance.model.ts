import { Moment } from 'moment';

export const enum TypeOfInsurance {
  LIABILITY = 'LIABILITY',
  COLLISION = 'COLLISION',
  COMPREHENSIVE = 'COMPREHENSIVE'
}

export interface IInsurance {
  id?: number;
  insuranceCompany?: string;
  typeOfInsurance?: TypeOfInsurance;
  dateOfInsurance?: Moment;
  validTill?: Moment;
  vehicleId?: number;
}

export const defaultValue: Readonly<IInsurance> = {};
