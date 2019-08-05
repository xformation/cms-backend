import { Moment } from 'moment';

export const enum Disability {
  YES = 'YES',
  NO = 'NO'
}

export interface IEmployee {
  id?: number;
  employeeName?: string;
  designation?: string;
  joiningDate?: Moment;
  jobEndDate?: Moment;
  resignationDate?: Moment;
  resignationAcceptanceDate?: Moment;
  aadharNo?: string;
  panNo?: string;
  passportNo?: string;
  primaryContactNo?: string;
  secondaryContactNo?: string;
  employeeFatherName?: string;
  employeeMotherName?: string;
  primaryAddress?: string;
  secondaryAddress?: string;
  employeeAddress?: string;
  personalMailId?: string;
  officialMailId?: string;
  disability?: Disability;
  drivingLicenceNo?: string;
  drivingLicenceValidity?: Moment;
  gender?: string;
  vehicleId?: number;
}

export const defaultValue: Readonly<IEmployee> = {};
