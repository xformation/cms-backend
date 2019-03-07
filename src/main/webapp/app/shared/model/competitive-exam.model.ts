export interface ICompetitiveExam {
  id?: number;
  testName?: string;
  testScore?: number;
  enrollmentNo?: number;
  rank?: number;
  studentId?: number;
}

export const defaultValue: Readonly<ICompetitiveExam> = {};
