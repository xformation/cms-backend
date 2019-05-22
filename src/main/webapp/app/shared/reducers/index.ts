import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

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
// @ts-ignore
import college, {
  CollegeState
} from 'app/entities/college/college.reducer';
// prettier-ignore
import courseOffer, {
  CourseOfferState
} from 'app/entities/course-offer/course-offer.reducer';
// prettier-ignore
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';
// prettier-ignore
// prettier-ignore
// prettier-ignore
import adminOverview, {
  AdminOverviewState
} from 'app/entities/admin-overview/admin-overview.reducer';
// prettier-ignore
import adminAttendance, {
  AdminAttendanceState
} from 'app/entities/admin-attendance/admin-attendance.reducer';
// prettier-ignore
// prettier-ignore
import studentFee, {
  StudentFeeState
} from 'app/entities/student-fee/student-fee.reducer';
// prettier-ignore
import reports, {
  ReportsState
} from 'app/entities/reports/reports.reducer';
// prettier-ignore
import idCard, {
  IdCardState
} from 'app/entities/id-card/id-card.reducer';
// prettier-ignore

// prettier-ignore
// prettier-ignore

// prettier-ignore
// prettier-ignore

// prettier-ignore

import academicYear, {
  AcademicYearState
} from 'app/entities/academic-year/academic-year.reducer';
// prettier-ignore

import feeCategory, {
  FeeCategoryState
} from 'app/entities/fee-category/fee-category.reducer';
// prettier-ignore
import transportRoute, {
  TransportRouteState
} from 'app/entities/transport-route/transport-route.reducer';
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
import country, {
  CountryState
} from 'app/entities/country/country.reducer';
// prettier-ignore
import teacher, {
  TeacherState
} from 'app/entities/teacher/teacher.reducer';
// prettier-ignore
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
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
import currency, {
  CurrencyState
} from 'app/entities/currency/currency.reducer';
// prettier-ignore
import state, {
  StateState
} from 'app/entities/state/state.reducer';
// prettier-ignore
import city, {
  CityState
} from 'app/entities/city/city.reducer';
// prettier-ignore
import facility, {
  FacilityState
} from 'app/entities/facility/facility.reducer';
// prettier-ignore
import feeDetails, {
  FeeDetailsState
} from 'app/entities/fee-details/fee-details.reducer';
// prettier-ignore
import dueDate, {
  DueDateState
} from 'app/entities/due-date/due-date.reducer';
// prettier-ignore
import paymentRemainder, {
  PaymentRemainderState
} from 'app/entities/payment-remainder/payment-remainder.reducer';
// prettier-ignore
import lateFee, {
  LateFeeState
} from 'app/entities/late-fee/late-fee.reducer';
// prettier-ignore
import invoice, {
  InvoiceState
} from 'app/entities/invoice/invoice.reducer';
// prettier-ignore
import competitiveExam, {
  CompetitiveExamState
} from 'app/entities/competitive-exam/competitive-exam.reducer';
// prettier-ignore
import documents, {
  DocumentsState
} from 'app/entities/documents/documents.reducer';
// prettier-ignore
import academicHistory, {
  AcademicHistoryState
} from 'app/entities/academic-history/academic-history.reducer';
// prettier-ignore
import admissionApplication, {
  AdmissionApplicationState
} from 'app/entities/admission-application/admission-application.reducer';
// prettier-ignore
import branch, {
  BranchState
} from 'app/entities/branch/branch.reducer';
// prettier-ignore
import admissionEnquiry, {
  AdmissionEnquiryState
} from 'app/entities/admission-enquiry/admission-enquiry.reducer';
// prettier-ignore
import dueDate, {
  DueDateState
} from 'app/entities/due-date/due-date.reducer';
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
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
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
import currency, {
  CurrencyState
} from 'app/entities/currency/currency.reducer';
// prettier-ignore
import state, {
  StateState
} from 'app/entities/state/state.reducer';
// prettier-ignore
import city, {
  CityState
} from 'app/entities/city/city.reducer';
// prettier-ignore
import facility, {
  FacilityState
} from 'app/entities/facility/facility.reducer';
// prettier-ignore
import feeDetails, {
  FeeDetailsState
} from 'app/entities/fee-details/fee-details.reducer';
// prettier-ignore
import dueDate, {
  DueDateState
} from 'app/entities/due-date/due-date.reducer';
// prettier-ignore
import paymentRemainder, {
  PaymentRemainderState
} from 'app/entities/payment-remainder/payment-remainder.reducer';
// prettier-ignore
import lateFee, {
  LateFeeState
} from 'app/entities/late-fee/late-fee.reducer';
// prettier-ignore
import invoice, {
  InvoiceState
} from 'app/entities/invoice/invoice.reducer';
// prettier-ignore
import competitiveExam, {
  CompetitiveExamState
} from 'app/entities/competitive-exam/competitive-exam.reducer';
// prettier-ignore
import documents, {
  DocumentsState
} from 'app/entities/documents/documents.reducer';
// prettier-ignore
import academicHistory, {
  AcademicHistoryState
} from 'app/entities/academic-history/academic-history.reducer';
// prettier-ignore
import admissionApplication, {
  AdmissionApplicationState
} from 'app/entities/admission-application/admission-application.reducer';
// prettier-ignore
import admissionEnquiry, {
  AdmissionEnquiryState
} from 'app/entities/admission-enquiry/admission-enquiry.reducer';
// prettier-ignore
import adminAttendance, {
  AdminAttendanceState
} from 'app/entities/admin-attendance/admin-attendance.reducer';
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
import holiday, {
  HolidayState
} from 'app/entities/holiday/holiday.reducer';
// prettier-ignore
import teach, {
  TeachState
} from 'app/entities/teach/teach.reducer';
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
import currency, {
  CurrencyState
} from 'app/entities/currency/currency.reducer';
// prettier-ignore
import state, {
  StateState
} from 'app/entities/state/state.reducer';
// prettier-ignore
import city, {
  CityState
} from 'app/entities/city/city.reducer';
// prettier-ignore
import facility, {
  FacilityState
} from 'app/entities/facility/facility.reducer';
// prettier-ignore
import feeDetails, {
  FeeDetailsState
} from 'app/entities/fee-details/fee-details.reducer';
// prettier-ignore
import dueDate, {
  DueDateState
} from 'app/entities/due-date/due-date.reducer';
// prettier-ignore
import paymentRemainder, {
  PaymentRemainderState
} from 'app/entities/payment-remainder/payment-remainder.reducer';
// prettier-ignore
import lateFee, {
  LateFeeState
} from 'app/entities/late-fee/late-fee.reducer';
// prettier-ignore
import invoice, {
  InvoiceState
} from 'app/entities/invoice/invoice.reducer';
// prettier-ignore
import competitiveExam, {
  CompetitiveExamState
} from 'app/entities/competitive-exam/competitive-exam.reducer';
// prettier-ignore
import documents, {
  DocumentsState
} from 'app/entities/documents/documents.reducer';
// prettier-ignore
import academicHistory, {
  AcademicHistoryState
} from 'app/entities/academic-history/academic-history.reducer';
// prettier-ignore
import admissionApplication, {
  AdmissionApplicationState
} from 'app/entities/admission-application/admission-application.reducer';
// prettier-ignore
import academicExamSetting, {
  AcademicExamSettingState
} from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
// prettier-ignore
import academicExamSetting, {
  AcademicExamSettingState
} from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
// prettier-ignore
import metaLecture, {
  MetaLectureState
} from 'app/entities/meta-lecture/meta-lecture.reducer';
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
import currency, {
  CurrencyState
} from 'app/entities/currency/currency.reducer';
// prettier-ignore
import state, {
  StateState
} from 'app/entities/state/state.reducer';
// prettier-ignore
import city, {
  CityState
} from 'app/entities/city/city.reducer';
// prettier-ignore
import facility, {
  FacilityState
} from 'app/entities/facility/facility.reducer';
// prettier-ignore
import feeDetails, {
  FeeDetailsState
} from 'app/entities/fee-details/fee-details.reducer';
// prettier-ignore
import dueDate, {
  DueDateState
} from 'app/entities/due-date/due-date.reducer';
// prettier-ignore
import paymentRemainder, {
  PaymentRemainderState
} from 'app/entities/payment-remainder/payment-remainder.reducer';
// prettier-ignore
import lateFee, {
  LateFeeState
} from 'app/entities/late-fee/late-fee.reducer';
// prettier-ignore
import invoice, {
  InvoiceState
} from 'app/entities/invoice/invoice.reducer';
// prettier-ignore
import competitiveExam, {
  CompetitiveExamState
} from 'app/entities/competitive-exam/competitive-exam.reducer';
// prettier-ignore
import documents, {
  DocumentsState
} from 'app/entities/documents/documents.reducer';
// prettier-ignore
import academicHistory, {
  AcademicHistoryState
} from 'app/entities/academic-history/academic-history.reducer';
// prettier-ignore
import admissionApplication, {
  AdmissionApplicationState
} from 'app/entities/admission-application/admission-application.reducer';
// prettier-ignore
import admissionEnquiry, {
  AdmissionEnquiryState
} from 'app/entities/admission-enquiry/admission-enquiry.reducer';
// prettier-ignore
import adminAttendance, {
  AdminAttendanceState
} from 'app/entities/admin-attendance/admin-attendance.reducer';
// prettier-ignore
import academicExamSetting, {
  AcademicExamSettingState
} from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
// prettier-ignore
import metaLecture, {
  MetaLectureState
} from 'app/entities/meta-lecture/meta-lecture.reducer';
// prettier-ignore
import admissionEnquiry, {
  AdmissionEnquiryState
} from 'app/entities/admission-enquiry/admission-enquiry.reducer';
// prettier-ignore
import adminAttendance, {
  AdminAttendanceState
} from 'app/entities/admin-attendance/admin-attendance.reducer';
// prettier-ignore
import academicExamSetting, {
  AcademicExamSettingState
} from 'app/entities/academic-exam-setting/academic-exam-setting.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
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
  readonly country: CountryState;
  readonly currency: CurrencyState;
  readonly state: StateState;
  readonly city: CityState;
  readonly adminOverview: AdminOverviewState;
  readonly adminAttendance: AdminAttendanceState;
  readonly feeCategory: FeeCategoryState;
  readonly facility: FacilityState;
  readonly transportRoute: TransportRouteState;
  readonly feeDetails: FeeDetailsState;
  readonly dueDate: DueDateState;
  readonly paymentRemainder: PaymentRemainderState;
  readonly lateFee: LateFeeState;
  readonly invoice: InvoiceState;
  readonly studentFee: StudentFeeState;
  readonly reports: ReportsState;
  readonly idCard: IdCardState;
  readonly documents: DocumentsState;
  readonly competitiveExam: CompetitiveExamState;
  readonly academicHistory: AcademicHistoryState;
  readonly admissionApplication: AdmissionApplicationState;
  readonly admissionEnquiry: AdmissionEnquiryState;
  readonly academicExamSetting: AcademicExamSettingState;
  readonly metaLecture: MetaLectureState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
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
  country,
  currency,
  state,
  city,
  adminOverview,
  adminAttendance,
  feeCategory,
  facility,
  transportRoute,
  feeDetails,
  dueDate,
  paymentRemainder,
  lateFee,
  invoice,
  studentFee,
  reports,
  idCard,
  documents,
  competitiveExam,
  academicHistory,
  admissionApplication,
  admissionEnquiry,
  academicExamSetting,
  metaLecture,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
