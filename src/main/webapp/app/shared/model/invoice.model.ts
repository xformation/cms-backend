import { Moment } from 'moment';

export const enum ModeOfPayment {
  CARD = 'CARD',
  CASH = 'CASH',
  NETBANKING = 'NETBANKING',
  CHEQUE = 'CHEQUE',
  DEMANDDRAFT = 'DEMANDDRAFT'
}

export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IInvoice {
  id?: number;
  invoiceNumber?: string;
  amountPaid?: number;
  paymentDate?: Moment;
  nextPaymentDate?: Moment;
  outStandingAmount?: number;
  modeOfPayment?: ModeOfPayment;
  chequeNumber?: number;
  demandDraftNumber?: number;
  onlineTxnRefNumber?: string;
  paymentStatus?: Status;
  comments?: string;
  updatedBy?: string;
  updatedOn?: Moment;
  feeCategoryId?: number;
  feeDetailsId?: number;
  dueDateId?: number;
  paymentRemainderId?: number;
  collegeId?: number;
  branchId?: number;
  studentId?: number;
  academicYearId?: number;
}

export const defaultValue: Readonly<IInvoice> = {};
