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
  ifsCode?: string;
  branch?: string;
  corporateId?: number;
}

export const defaultValue: Readonly<IBankAccounts> = {};
