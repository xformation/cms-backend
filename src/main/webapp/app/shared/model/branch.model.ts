export interface IBranch {
  id?: number;
  branchName?: string;
  address1?: string;
  address2?: string;
  branchHead?: string;
  collegeId?: number;
  cityId?: number;
  stateId?: number;
}

export const defaultValue: Readonly<IBranch> = {};
