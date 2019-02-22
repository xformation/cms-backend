export const enum StudentTypeEnum {
  REGULAR = 'REGULAR',
  STAFF_CONCESSION = 'STAFF_CONCESSION',
  BENEFITS = 'BENEFITS',
  SCHOLARSHIP = 'SCHOLARSHIP',
  OTHER_BENEFITS = 'OTHER_BENEFITS'
}

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE',
  OTHER = 'OTHER'
}

export interface IFeeDetails {
  id?: number;
  feeParticularsName?: string;
  feeParticularDesc?: string;
  studentType?: StudentTypeEnum;
  gender?: Gender;
  amount?: number;
  feeCategoryId?: number;
  batchId?: number;
  facilityId?: number;
  transportRouteId?: number;
  collegeId?: number;
  departmentId?: number;
  branchId?: number;
  academicYearId?: number;
}

export const defaultValue: Readonly<IFeeDetails> = {};
