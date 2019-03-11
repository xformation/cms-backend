import { Moment } from 'moment';

export const enum AdmissionStatusEnum {
  RECEIVED = 'RECEIVED',
  INPROCESS = 'INPROCESS',
  DECLINED = 'DECLINED',
  ACCEPTED = 'ACCEPTED'
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
  course?: CourseEnum;
  date?: Moment;
  comments?: string;
  studentId?: number;
}

export const defaultValue: Readonly<IAdmissionApplication> = {};
