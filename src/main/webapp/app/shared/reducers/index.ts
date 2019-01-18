import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import college, {
  CollegeState
} from 'app/entities/college/college.reducer';
// prettier-ignore
import branch, {
  BranchState
} from 'app/entities/branch/branch.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import batch, {
  BatchState
} from 'app/entities/batch/batch.reducer';
// prettier-ignore
import subject, {
  SubjectState
} from 'app/entities/subject/subject.reducer';
// prettier-ignore
import section, {
  SectionState
} from 'app/entities/section/section.reducer';
// prettier-ignore
import term, {
  TermState
} from 'app/entities/term/term.reducer';
// prettier-ignore
import student, {
  StudentState
} from 'app/entities/student/student.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import academicYear, {
  AcademicYearState
} from 'app/entities/academic-year/academic-year.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
// prettier-ignore
import courseOffer, {
  CourseOfferState
} from 'app/entities/course-offer/course-offer.reducer';
// prettier-ignore
import attendanceMaster, {
  AttendanceMasterState
} from 'app/entities/attendance-master/attendance-master.reducer';
// prettier-ignore
import lecture, {
  LectureState
} from 'app/entities/lecture/lecture.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import legalEntity, {
  LegalEntityState
} from 'app/entities/legal-entity/legal-entity.reducer';
// prettier-ignore
import authorizedSignatory, {
  AuthorizedSignatoryState
} from 'app/entities/authorized-signatory/authorized-signatory.reducer';
// prettier-ignore
import bankAccounts, {
  BankAccountsState
} from 'app/entities/bank-accounts/bank-accounts.reducer';
// prettier-ignore
import testEntity, {
  TestEntityState
} from 'app/entities/test-entity/test-entity.reducer';
// prettier-ignore
import branch, {
  BranchState
} from 'app/entities/branch/branch.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import batch, {
  BatchState
} from 'app/entities/batch/batch.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
// prettier-ignore
import courseOffer, {
  CourseOfferState
} from 'app/entities/course-offer/course-offer.reducer';
// prettier-ignore
import attendanceMaster, {
  AttendanceMasterState
} from 'app/entities/attendance-master/attendance-master.reducer';
// prettier-ignore
import lecture, {
  LectureState
} from 'app/entities/lecture/lecture.reducer';
// prettier-ignore
import authorizedSignatory, {
  AuthorizedSignatoryState
} from 'app/entities/authorized-signatory/authorized-signatory.reducer';
// prettier-ignore
import college, {
  CollegeState
} from 'app/entities/college/college.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import subject, {
  SubjectState
} from 'app/entities/subject/subject.reducer';
// prettier-ignore
import student, {
  StudentState
} from 'app/entities/student/student.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import studentSubject, {
  StudentSubjectState
} from 'app/entities/student-subject/student-subject.reducer';
// prettier-ignore
import college, {
  CollegeState
} from 'app/entities/college/college.reducer';
// prettier-ignore
import branch, {
  BranchState
} from 'app/entities/branch/branch.reducer';
// prettier-ignore
import department, {
  DepartmentState
} from 'app/entities/department/department.reducer';
// prettier-ignore
import batch, {
  BatchState
} from 'app/entities/batch/batch.reducer';
// prettier-ignore
import subject, {
  SubjectState
} from 'app/entities/subject/subject.reducer';
// prettier-ignore
import section, {
  SectionState
} from 'app/entities/section/section.reducer';
// prettier-ignore
import term, {
  TermState
} from 'app/entities/term/term.reducer';
// prettier-ignore
import student, {
  StudentState
} from 'app/entities/student/student.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import academicYear, {
  AcademicYearState
} from 'app/entities/academic-year/academic-year.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
// prettier-ignore
import courseOffer, {
  CourseOfferState
} from 'app/entities/course-offer/course-offer.reducer';
// prettier-ignore
import attendanceMaster, {
  AttendanceMasterState
} from 'app/entities/attendance-master/attendance-master.reducer';
// prettier-ignore
import lecture, {
  LectureState
} from 'app/entities/lecture/lecture.reducer';
// prettier-ignore
import studentAttendance, {
  StudentAttendanceState
} from 'app/entities/student-attendance/student-attendance.reducer';
// prettier-ignore
import studentSubject, {
  StudentSubjectState
} from 'app/entities/student-subject/student-subject.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
import legalEntity, {
  LegalEntityState
} from 'app/entities/legal-entity/legal-entity.reducer';
// prettier-ignore
import authorizedSignatory, {
  AuthorizedSignatoryState
} from 'app/entities/authorized-signatory/authorized-signatory.reducer';
// prettier-ignore
import bankAccounts, {
  BankAccountsState
} from 'app/entities/bank-accounts/bank-accounts.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly college: CollegeState;
  readonly branch: BranchState;
  readonly department: DepartmentState;
  readonly batch: BatchState;
  readonly subject: SubjectState;
  readonly section: SectionState;
  readonly term: TermState;
  readonly student: StudentState;
  readonly teacher: TeacherState;
  readonly academicYear: AcademicYearState;
  readonly holiday: HolidayState;
  readonly teach: TeachState;
  readonly courseOffer: CourseOfferState;
  readonly attendanceMaster: AttendanceMasterState;
  readonly lecture: LectureState;
  readonly studentAttendance: StudentAttendanceState;
  readonly location: LocationState;
  readonly legalEntity: LegalEntityState;
  readonly authorizedSignatory: AuthorizedSignatoryState;
  readonly bankAccounts: BankAccountsState;
  readonly testEntity: TestEntityState;
  readonly studentSubject: StudentSubjectState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  college,
  branch,
  department,
  batch,
  subject,
  section,
  term,
  student,
  teacher,
  academicYear,
  holiday,
  teach,
  courseOffer,
  attendanceMaster,
  lecture,
  studentAttendance,
  location,
  legalEntity,
  authorizedSignatory,
  bankAccounts,
  testEntity,
  studentSubject,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
