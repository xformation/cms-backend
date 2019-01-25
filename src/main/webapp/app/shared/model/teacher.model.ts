export interface ITeacher {
  id?: number;
  teacherName?: string;
  branchId?: number;
  departmentId?: number;
}

export const defaultValue: Readonly<ITeacher> = {};
