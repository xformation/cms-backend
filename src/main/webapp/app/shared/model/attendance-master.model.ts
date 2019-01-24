export interface IAttendanceMaster {
  id?: number;
  desc?: string;
  teachId?: number;
  batchId?: number;
  sectionId?: number;
}

export const defaultValue: Readonly<IAttendanceMaster> = {};
