export interface IAuthorizedSignatory {
  id?: number;
  signatoryName?: string;
  signatoryFatherName?: string;
  signatoryDesignation?: string;
  address?: string;
  email?: string;
  panCardNumber?: string;
  branchId?: number;
  collegeId?: number;
}

export const defaultValue: Readonly<IAuthorizedSignatory> = {};
