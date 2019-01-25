export interface ITeacher {
  id?: number;
  teacherName?: string;
  departmentId?: number;
  branchId?: number;
}

export const defaultValue: Readonly<ITeacher> = {};
