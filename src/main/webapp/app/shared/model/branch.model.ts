export interface IBranch {
  id?: number;
  branchName?: string;
  description?: string;
  collegeHead?: string;
  collegeId?: number;
}

export const defaultValue: Readonly<IBranch> = {};
