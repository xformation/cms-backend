export interface IAttendanceMaster {
  id?: number;
  desc?: string;
  batchId?: number;
  sectionId?: number;
  teachId?: number;
}

export const defaultValue: Readonly<IAttendanceMaster> = {};
