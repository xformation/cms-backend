export interface IAttendanceMaster {
  id?: number;
  desc?: string;
  teachId?: number;
  sectionId?: number;
  academicyearId?: number;
}

export const defaultValue: Readonly<IAttendanceMaster> = {};
