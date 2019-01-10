export interface IStudent {
  id?: number;
  studentName?: string;
  departmentId?: number;
  batchId?: number;
  sectionId?: number;
  branchId?: number;
  departmentId?: number;
}

export const defaultValue: Readonly<IStudent> = {};
