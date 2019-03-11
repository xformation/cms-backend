export interface IAcademicHistory {
  id?: number;
  qualification?: string;
  yearOfPassing?: string;
  institution?: string;
  university?: string;
  enrollmentNo?: number;
  score?: number;
  percentage?: number;
  studentId?: number;
}

export const defaultValue: Readonly<IAcademicHistory> = {};
