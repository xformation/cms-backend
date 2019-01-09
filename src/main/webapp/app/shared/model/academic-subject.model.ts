export interface IAcademicSubject {
  id?: number;
  subjectName?: string;
  electiveSub?: boolean;
  departmentId?: number;
}

export const defaultValue: Readonly<IAcademicSubject> = {
  electiveSub: false
};
