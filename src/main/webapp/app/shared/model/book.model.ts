import { Moment } from 'moment';

export const enum StatusEnum {
  AVAILABLE = 'AVAILABLE',
  RESERVED = 'RESERVED'
}

export interface IBook {
  id?: number;
  issueDate?: Moment;
  dueDate?: Moment;
  noOfCopiesAvailable?: number;
  status?: StatusEnum;
  receivedDate?: Moment;
  studentId?: number;
  libraryId?: number;
}

export const defaultValue: Readonly<IBook> = {};
