import { Moment } from 'moment';

export interface IStudentFee {
  id?: number;
  totalFee?: string;
  feesPaid?: string;
  feesDue?: string;
  dueDate?: Moment;
  totalRemaining?: string;
}

export const defaultValue: Readonly<IStudentFee> = {};
