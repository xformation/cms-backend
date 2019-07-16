import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export const enum CourseEnum {
  BTECH = 'BTECH',
  MTECH = 'MTECH',
  BBA = 'BBA',
  MBA = 'MBA'
}

export const enum ModeOfEnquiry {
  INPERSON = 'INPERSON',
  TELEPHONE = 'TELEPHONE',
  EMAIL = 'EMAIL'
}

export const enum EnquiryStatus {
  FOLLOWUP = 'FOLLOWUP',
  DECLINED = 'DECLINED',
  CONVERTED = 'CONVERTED'
}

export interface IAdmissionEnquiry {
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
  contactNumber?: string;
  alternateMobileNumber?: string;
  dateOfBirth?: Moment;
  email?: string;
  sex?: Gender;
  comments?: string;
  courseApplyingFor?: CourseEnum;
  highestQualification?: string;
  modeOfEnquiry?: ModeOfEnquiry;
  status?: EnquiryStatus;
  description?: string;
  enquiryDate?: Moment;
  updatedOn?: Moment;
  updatedBy?: string;
  branchId?: number;
  departmentId?: number;
  batchId?: number;
  stateId?: number;
  cityId?: number;
  countryId?: number;
}

export const defaultValue: Readonly<IAdmissionEnquiry> = {};
