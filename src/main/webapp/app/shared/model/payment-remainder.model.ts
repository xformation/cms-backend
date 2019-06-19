export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IPaymentRemainder {
  id?: number;
  feeRemainder?: Status;
  noticeDay?: number;
  overDueRemainder?: Status;
  remainderRecipients?: string;
  dueDateId?: number;
  collegeId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IPaymentRemainder> = {};
