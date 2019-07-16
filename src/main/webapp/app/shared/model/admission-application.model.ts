import { Moment } from 'moment';

export const enum AdmissionStatusEnum {
  INPROCESS = 'INPROCESS',
  DECLINED = 'DECLINED',
  ACCEPTED = 'ACCEPTED'
}

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

export interface IAdmissionApplication {
  id?: number;
  admissionStatus?: AdmissionStatusEnum;
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
  applicationId?: number;
  uploadPhoto?: string;
  course?: CourseEnum;
  admissionDate?: Moment;
  admissionEnquiryId?: number;
  academicHistoryId?: number;
  documentsId?: number;
  branchId?: number;
  batchId?: number;
  stateId?: number;
  cityId?: number;
  countryId?: number;
  departmentId?: number;
  academicyearId?: number;
}

export const defaultValue: Readonly<IAdmissionApplication> = {};
