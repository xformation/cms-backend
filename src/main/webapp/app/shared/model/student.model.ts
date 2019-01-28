import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export interface IStudent {
  id?: number;
  studentName?: string;
  studentMiddleName?: string;
  studentLastName?: string;
  fatherName?: string;
  fatherMiddleName?: string;
  fatherLastName?: string;
  motherName?: string;
  motherMiddleName?: string;
  motherLastName?: string;
  aadharNo?: number;
  dateOfBirth?: Moment;
  placeOfBirth?: string;
  religion?: string;
  caste?: string;
  subCaste?: string;
  age?: number;
  sex?: Gender;
  bloodGroup?: string;
  addressLineOne?: string;
  addressLineTwo?: string;
  addressLineThree?: string;
  town?: string;
  state?: string;
  country?: string;
  pincode?: number;
  studentContactNumber?: number;
  alternateContactNumber?: number;
  studentEmailAddress?: string;
  alternateEmailAddress?: string;
  relationWithStudent?: string;
  name?: string;
  middleName?: string;
  lastName?: string;
  contactNo?: number;
  emailAddress?: string;
  uploadPhoto?: number;
  admissionNo?: number;
  rollNo?: string;
  studentType?: string;
  departmentId?: number;
  batchId?: number;
  sectionId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IStudent> = {};
