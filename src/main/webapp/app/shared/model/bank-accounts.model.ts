export const enum NameOfBank {
  HDFC = 'HDFC',
  SBI = 'SBI',
  ICICI = 'ICICI',
  ANDHRABANK = 'ANDHRABANK'
}

export interface IBankAccounts {
  id?: number;
  nameOfBank?: NameOfBank;
  accountNumber?: number;
  typeOfAccount?: string;
  ifscCode?: string;
  branchAddress?: string;
  corporateId?: number;
  branchId?: number;
  collegeId?: number;
}

export const defaultValue: Readonly<IBankAccounts> = {};
