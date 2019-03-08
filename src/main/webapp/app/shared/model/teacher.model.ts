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

export const enum StaffType {
  TEACHING = 'TEACHING',
  NONTEACHING = 'NONTEACHING',
  GUEST = 'GUEST'
}

export interface ITeacher {
  id?: number;
  teacherName?: string;
  teacherMiddleName?: string;
  teacherLastName?: string;
  fatherName?: string;
  fatherMiddleName?: string;
  fatherLastName?: string;
  spouseName?: string;
  spouseMiddleName?: string;
  spouseLastName?: string;
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
  teacherContactNumber?: string;
  alternateContactNumber?: string;
  teacherEmailAddress?: string;
  alternateEmailAddress?: string;
  relationWithStaff?: RelationWithStudentEnum;
  emergencyContactName?: string;
  emergencyContactMiddleName?: string;
  emergencyContactLastName?: string;
  emergencyContactNo?: string;
  emergencyContactEmailAddress?: string;
  uploadPhoto?: string;
  status?: Status;
  employeeId?: number;
  designation?: string;
  staffType?: StaffType;
  departmentId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<ITeacher> = {};
