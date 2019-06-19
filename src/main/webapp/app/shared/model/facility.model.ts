export const enum Status {
  ACTIVE = 'ACTIVE',
  DEACTIVE = 'DEACTIVE'
}

export interface IFacility {
  id?: number;
  transport?: Status;
  mess?: Status;
  gym?: Status;
  culturalClass?: Status;
  library?: Status;
  sports?: Status;
  swimming?: Status;
  extraClass?: Status;
  handicrafts?: Status;
  academicYearId?: number;
  branchId?: number;
  studentId?: number;
}

export const defaultValue: Readonly<IFacility> = {};
