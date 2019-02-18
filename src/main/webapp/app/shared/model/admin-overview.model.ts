import { Moment } from 'moment';

export const enum SectionEnum {
  A = 'A',
  B = 'B',
  C = 'C',
  D = 'D'
}

export const enum LectureAdminEnum {
  MARKED = 'MARKED',
  UNMARKED = 'UNMARKED'
}

export interface IAdminOverview {
  id?: number;
  chooseDate?: Moment;
  section?: SectionEnum;
  lectureOne?: LectureAdminEnum;
  lectureTwo?: LectureAdminEnum;
  lectureThree?: LectureAdminEnum;
  lectureFour?: LectureAdminEnum;
  lectureFive?: LectureAdminEnum;
  lectureSix?: LectureAdminEnum;
  lectureSeven?: LectureAdminEnum;
  lectureEight?: LectureAdminEnum;
  departmentId?: number;
  batchId?: number;
}

export const defaultValue: Readonly<IAdminOverview> = {};
