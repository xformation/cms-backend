export interface IAuthorizedSignatory {
  id?: number;
  signatoryName?: string;
  signatoryFatherName?: string;
  signatoryDesignation?: string;
  address?: string;
  email?: string;
  panCardNumber?: string;
  legalentityId?: number;
}

export const defaultValue: Readonly<IAuthorizedSignatory> = {};
