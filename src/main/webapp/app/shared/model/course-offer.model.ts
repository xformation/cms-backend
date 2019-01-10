export interface ICourseOffer {
  id?: number;
  desc?: string;
  collegeId?: number;
  departmentId?: number;
  subjectId?: number;
}

export const defaultValue: Readonly<ICourseOffer> = {};
