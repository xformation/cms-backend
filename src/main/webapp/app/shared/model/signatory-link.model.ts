export interface ISignatoryLink {
  id?: number;
  desc?: string;
  authorizedSignatoryId?: number;
  legalEntityId?: number;
}

export const defaultValue: Readonly<ISignatoryLink> = {};
