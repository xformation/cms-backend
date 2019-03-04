export interface IFacility {
  id?: number;
  facilityName?: string;
  academicYearId?: number;
  branchId?: number;
  studentId?: number;
}

export const defaultValue: Readonly<IFacility> = {};
