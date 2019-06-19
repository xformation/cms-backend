export const enum Frequency {
  WEEKLY = 'WEEKLY',
  MONTHLY = 'MONTHLY',
  QUARTERLY = 'QUARTERLY',
  HALFYEARLY = 'HALFYEARLY',
  ANNUALLY = 'ANNUALLY'
}

export interface IDueDate {
  id?: number;
  paymentMethod?: string;
  installments?: number;
  dayDesc?: string;
  paymentDay?: number;
  frequency?: Frequency;
  collegeId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IDueDate> = {};
