export interface IPaymentRemainder {
  id?: number;
  isAutoRemainder?: string;
  isFirstPaymentRemainder?: string;
  firstPaymentRemainderDays?: number;
  isSecondPaymentRemainder?: string;
  secondPaymentRemainderDays?: number;
  isOverDuePaymentRemainder?: string;
  overDuePaymentRemainderAfterDueDateOrUntilPaid?: string;
  overDuePaymentRemainderDays?: number;
  isRemainderRecipients?: string;
  remainderRecipients?: string;
  collegeId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<IPaymentRemainder> = {};
