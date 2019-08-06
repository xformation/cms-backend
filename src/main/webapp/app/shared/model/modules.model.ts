export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE',
  DRAFT = 'DRAFT'
}

export interface IModules {
  id?: number;
  moduleName?: string;
  subModuleName?: string;
  url?: string;
  status?: Status;
}

export const defaultValue: Readonly<IModules> = {};
