import { Moment } from 'moment';

export const enum Religion {
  HINDU = 'HINDU',
  MUSLIM = 'MUSLIM',
  CHRISTIAN = 'CHRISTIAN'
}

export const enum Caste {
  OC = 'OC',
  BC = 'BC',
  SC = 'SC',
  ST = 'ST'
}

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export const enum Bloodgroup {
  ABPOSITIVE = 'ABPOSITIVE',
  ABNEGATIVE = 'ABNEGATIVE',
  OPOSITIVE = 'OPOSITIVE',
  BPOSITIVE = 'BPOSITIVE',
  BNEGATIVE = 'BNEGATIVE'
}

export const enum RelationWithStudentEnum {
  FATHER = 'FATHER',
  MOTHER = 'MOTHER',
  GUARDIAN = 'GUARDIAN'
}

export const enum StudentTypeEnum {
  REGULAR = 'REGULAR',
  STAFF_CONCESSION = 'STAFF_CONCESSION',
  BENEFITS = 'BENEFITS',
  SCHOLARSHIP = 'SCHOLARSHIP',
  OTHER_BENEFITS = 'OTHER_BENEFITS'
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
  religion?: Religion;
  caste?: Caste;
  subCaste?: string;
  age?: number;
  sex?: Gender;
  bloodGroup?: Bloodgroup;
  addressLineOne?: string;
  addressLineTwo?: string;
  addressLineThree?: string;
  town?: string;
  state?: string;
  country?: string;
  pincode?: number;
  studentContactNumber?: string;
  alternateContactNumber?: string;
  studentEmailAddress?: string;
  alternateEmailAddress?: string;
  relationWithStudent?: RelationWithStudentEnum;
  emergencyContactName?: string;
  emergencyContactMiddleName?: string;
  emergencyContactLastName?: string;
  emergencyContactNo?: string;
  emergencyContactEmailAddress?: string;
  uploadPhoto?: string;
  admissionNo?: number;
  rollNo?: string;
  studentType?: StudentTypeEnum;
  departmentId?: number;
  batchId?: number;
  sectionId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IStudent> = {};
