export interface IDepartment {
  id?: number;
  name?: string;
  description?: string;
  deptHead?: string;
  studentId?: number;
  collegeId?: number;
  academicyearId?: number;
}

export const defaultValue: Readonly<IDepartment> = {};
