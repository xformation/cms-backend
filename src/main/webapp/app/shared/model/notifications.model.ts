import { Moment } from 'moment';

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE',
  DRAFT = 'DRAFT'
}

export interface INotifications {
  id?: number;
  messageCode?: string;
  message?: string;
  status?: Status;
  createdBy?: string;
  createdOn?: Moment;
  updatedBy?: string;
  updatedOn?: Moment;
  academicYearId?: number;
}

export const defaultValue: Readonly<INotifications> = {};
