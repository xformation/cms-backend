export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export const enum Frequency {
  WEEKLY = 'WEEKLY',
  MONTHLY = 'MONTHLY',
  QUARTERLY = 'QUARTERLY',
  HALFYEARLY = 'HALFYEARLY',
  ANNUALLY = 'ANNUALLY'
}

export interface ILateFee {
  id?: number;
  assignLateFee?: Status;
  lateFeeDays?: number;
  fixed?: Status;
  percentage?: Status;
  fixedCharges?: number;
  percentCharges?: number;
  lateFeeAssignmentFrequency?: Frequency;
  collegeId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<ILateFee> = {};
