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

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
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
  studentContactNumber?: number;
  alternateContactNumber?: number;
  studentEmailAddress?: string;
  alternateEmailAddress?: string;
  relationWithStudent?: RelationWithStudentEnum;
  name?: string;
  middleName?: string;
  lastName?: string;
  contactNo?: number;
  emailAddress?: string;
  transport?: Status;
  mess?: Status;
  gym?: Status;
  culturalClass?: Status;
  library?: Status;
  sports?: Status;
  swimming?: Status;
  extraClass?: Status;
  handicrafts?: Status;
  add?: Status;
  uploadPhoto?: number;
  admissionNo?: number;
  rollNo?: string;
  studentType?: StudentTypeEnum;
  departmentId?: number;
  batchId?: number;
  sectionId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IStudent> = {};
