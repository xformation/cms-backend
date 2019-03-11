import { Moment } from 'moment';

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
  RECEIVED = 'RECEIVED',
  FOLLOWUP = 'FOLLOWUP',
  DECLINED = 'DECLINED',
  CONVERTED = 'CONVERTED'
}

export interface IAdmissionEnquiry {
  id?: number;
  studentName?: string;
  mobileNumber?: string;
  alternateMobileNumber?: string;
  email?: string;
  courseApplyingFor?: CourseEnum;
  modeOfEnquiry?: ModeOfEnquiry;
  status?: EnquiryStatus;
  description?: string;
  enquiryDate?: Moment;
  updatedOn?: Moment;
  updatedBy?: string;
  branchId?: number;
  admissionApplicationId?: number;
}

export const defaultValue: Readonly<IAdmissionEnquiry> = {};
