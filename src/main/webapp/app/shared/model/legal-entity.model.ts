import { Moment } from 'moment';

export const enum TypeOfCollege {
  PRIVATE = 'PRIVATE',
  PUBLIC = 'PUBLIC'
}

export interface ILegalEntity {
  id?: number;
  logo?: number;
  legalNameOfTheCollege?: string;
  typeOfCollege?: TypeOfCollege;
  dateOfIncorporation?: Moment;
  registeredOfficeAddress1?: string;
  registeredOfficeAddress2?: string;
  registeredOfficeAddress3?: string;
  registeredOfficeAddress4?: string;
  registeredOfficeAddress5?: string;
  collegeIdentificationNumber?: string;
  pan?: string;
  tan?: string;
  tanCircleNumber?: string;
  citTdsLocation?: string;
  formSignatory?: number;
  pfNumber?: string;
  pfRegistrationDate?: Moment;
  pfSignatory?: number;
  esiNumber?: number;
  esiRegistrationDate?: Moment;
  esiSignatory?: number;
  ptNumber?: number;
  ptRegistrationDate?: Moment;
  ptSignatory?: number;
  branchId?: number;
  collegeId?: number;
  stateId?: number;
  cityId?: number;
}

export const defaultValue: Readonly<ILegalEntity> = {};
