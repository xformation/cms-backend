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
  registeredOfficeAddress?: string;
  collegeIdentificationNumber?: string;
  pan?: string;
  tan?: string;
  tanCircleNumber?: string;
  citTdsLocation?: string;
  formSignatory?: string;
  pfNumber?: string;
  registrationDate?: Moment;
  esiNumber?: number;
  ptRegistrationDate?: Moment;
  ptSignatory?: string;
  ptNumber?: number;
}

export const defaultValue: Readonly<ILegalEntity> = {};
