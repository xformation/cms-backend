export interface IAcademicDepartment {
  id?: number;
  departmentName?: string;
  university?: string;
}

export const defaultValue: Readonly<IAcademicDepartment> = {};
