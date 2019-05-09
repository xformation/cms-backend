export interface IMetaLecture {
  id?: number;
  weekDay?: string;
  startTime?: string;
  endTime?: string;
  branchId?: number;
  departmentId?: number;
  subjectId?: number;
  teacherId?: number;
  termId?: number;
  academicyearId?: number;
  sectionId?: number;
  batchId?: number;
}

export const defaultValue: Readonly<IMetaLecture> = {};
