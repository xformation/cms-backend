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
  pfRegistrationDate?: Moment;
  pfSignatory?: string;
  pfSignatoryDesignation?: string;
  pfSignatoryFatherName?: string;
  esiNumber?: number;
  esiRegistrationDate?: Moment;
  esiSignatory?: string;
  esiSignatoryDesignation?: string;
  esiSignatoryFatherName?: string;
  ptNumber?: number;
  ptRegistrationDate?: Moment;
  ptSignatory?: string;
}

export const defaultValue: Readonly<ILegalEntity> = {};
