export interface IAcademicSubject {
  id?: number;
  subjectName?: string;
  electiveSub?: boolean;
  academicDepartmentId?: number;
}

export const defaultValue: Readonly<IAcademicSubject> = {
  electiveSub: false
};
