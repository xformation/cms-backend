export interface IAuthorizedSignatory {
  id?: number;
  signatoryName?: string;
  signatoryFatherName?: string;
  signatoryDesignation?: string;
  address1?: string;
  address2?: string;
  address3?: string;
  address4?: string;
  address5?: string;
  email?: string;
  panCardNumber?: string;
  branchId?: number;
  collegeId?: number;
}

export const defaultValue: Readonly<IAuthorizedSignatory> = {};
