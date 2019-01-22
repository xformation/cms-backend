export interface IAttendanceMaster {
  id?: number;
  desc?: string;
  teachId?: number;
  sectionId?: number;
  batchId?: number;
}

export const defaultValue: Readonly<IAttendanceMaster> = {};
