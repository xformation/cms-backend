export interface IStudentFacilityLink {
  id?: number;
  linkDesc?: string;
  studentId?: number;
  facilityId?: number;
}

export const defaultValue: Readonly<IStudentFacilityLink> = {};
