export interface ILateFee {
  id?: number;
  isAutoLateFee?: string;
  lateFeeDays?: number;
  chargeType?: string;
  fixedCharges?: number;
  percentCharges?: string;
  lateFeeFrequency?: string;
  lateFeeRepeatDays?: number;
  collegeId?: number;
  branchId?: number;
  academicYearId?: number;
  termId?: number;
}

export const defaultValue: Readonly<ILateFee> = {};
