import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFeeCategory } from 'app/shared/model/fee-category.model';
import { getEntities as getFeeCategories } from 'app/entities/fee-category/fee-category.reducer';
import { IFeeDetails } from 'app/shared/model/fee-details.model';
import { getEntities as getFeeDetails } from 'app/entities/fee-details/fee-details.reducer';
import { IDueDate } from 'app/shared/model/due-date.model';
import { getEntities as getDueDates } from 'app/entities/due-date/due-date.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
import { getEntities as getPaymentRemainders } from 'app/entities/payment-remainder/payment-remainder.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInvoiceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInvoiceUpdateState {
  isNew: boolean;
  feeCategoryId: string;
  feeDetailsId: string;
  dueDateId: string;
  paymentRemainderId: string;
  collegeId: string;
  branchId: string;
  studentId: string;
  academicYearId: string;
}

export class InvoiceUpdate extends React.Component<IInvoiceUpdateProps, IInvoiceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      feeCategoryId: '0',
      feeDetailsId: '0',
      dueDateId: '0',
      paymentRemainderId: '0',
      collegeId: '0',
      branchId: '0',
      studentId: '0',
      academicYearId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getFeeCategories();
    this.props.getFeeDetails();
    this.props.getDueDates();
    this.props.getPaymentRemainders();
    this.props.getColleges();
    this.props.getBranches();
    this.props.getStudents();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { invoiceEntity } = this.props;
      const entity = {
        ...invoiceEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/invoice');
  };

  render() {
    const {
      invoiceEntity,
      feeCategories,
      feeDetails,
      dueDates,
      paymentRemainders,
      colleges,
      branches,
      students,
      academicYears,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.invoice.home.createOrEditLabel">Create or edit a Invoice</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : invoiceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="invoice-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="invoiceNumberLabel" for="invoiceNumber">
                    Invoice Number
                  </Label>
                  <AvField
                    id="invoice-invoiceNumber"
                    type="text"
                    name="invoiceNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="amountPaidLabel" for="amountPaid">
                    Amount Paid
                  </Label>
                  <AvField
                    id="invoice-amountPaid"
                    type="string"
                    className="form-control"
                    name="amountPaid"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentDateLabel" for="paymentDate">
                    Payment Date
                  </Label>
                  <AvField
                    id="invoice-paymentDate"
                    type="date"
                    className="form-control"
                    name="paymentDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nextPaymentDateLabel" for="nextPaymentDate">
                    Next Payment Date
                  </Label>
                  <AvField
                    id="invoice-nextPaymentDate"
                    type="date"
                    className="form-control"
                    name="nextPaymentDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="outStandingAmountLabel" for="outStandingAmount">
                    Out Standing Amount
                  </Label>
                  <AvField
                    id="invoice-outStandingAmount"
                    type="string"
                    className="form-control"
                    name="outStandingAmount"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="modeOfPaymentLabel">Mode Of Payment</Label>
                  <AvInput
                    id="invoice-modeOfPayment"
                    type="select"
                    className="form-control"
                    name="modeOfPayment"
                    value={(!isNew && invoiceEntity.modeOfPayment) || 'CARD'}
                  >
                    <option value="CARD">CARD</option>
                    <option value="CASH">CASH</option>
                    <option value="NETBANKING">NETBANKING</option>
                    <option value="CHEQUE">CHEQUE</option>
                    <option value="DEMANDDRAFT">DEMANDDRAFT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="chequeNumberLabel" for="chequeNumber">
                    Cheque Number
                  </Label>
                  <AvField id="invoice-chequeNumber" type="string" className="form-control" name="chequeNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="demandDraftNumberLabel" for="demandDraftNumber">
                    Demand Draft Number
                  </Label>
                  <AvField id="invoice-demandDraftNumber" type="string" className="form-control" name="demandDraftNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="onlineTxnRefNumberLabel" for="onlineTxnRefNumber">
                    Online Txn Ref Number
                  </Label>
                  <AvField id="invoice-onlineTxnRefNumber" type="text" name="onlineTxnRefNumber" />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentStatusLabel">Payment Status</Label>
                  <AvInput
                    id="invoice-paymentStatus"
                    type="select"
                    className="form-control"
                    name="paymentStatus"
                    value={(!isNew && invoiceEntity.paymentStatus) || 'PAID'}
                  >
                    <option value="PAID">PAID</option>
                    <option value="UNPAID">UNPAID</option>
                    <option value="CANCELED">CANCELED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="invoice-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedByLabel" for="updatedBy">
                    Updated By
                  </Label>
                  <AvField
                    id="invoice-updatedBy"
                    type="text"
                    name="updatedBy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="updatedOnLabel" for="updatedOn">
                    Updated On
                  </Label>
                  <AvField
                    id="invoice-updatedOn"
                    type="date"
                    className="form-control"
                    name="updatedOn"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="feeCategory.id">Fee Category</Label>
                  <AvInput id="invoice-feeCategory" type="select" className="form-control" name="feeCategoryId">
                    <option value="" key="0" />
                    {feeCategories
                      ? feeCategories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="feeDetails.id">Fee Details</Label>
                  <AvInput id="invoice-feeDetails" type="select" className="form-control" name="feeDetailsId">
                    <option value="" key="0" />
                    {feeDetails
                      ? feeDetails.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="dueDate.id">Due Date</Label>
                  <AvInput id="invoice-dueDate" type="select" className="form-control" name="dueDateId">
                    <option value="" key="0" />
                    {dueDates
                      ? dueDates.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="paymentRemainder.id">Payment Remainder</Label>
                  <AvInput id="invoice-paymentRemainder" type="select" className="form-control" name="paymentRemainderId">
                    <option value="" key="0" />
                    {paymentRemainders
                      ? paymentRemainders.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="invoice-college" type="select" className="form-control" name="collegeId">
                    <option value="" key="0" />
                    {colleges
                      ? colleges.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="invoice-branch" type="select" className="form-control" name="branchId">
                    <option value="" key="0" />
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="invoice-student" type="select" className="form-control" name="studentId">
                    <option value="" key="0" />
                    {students
                      ? students.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicYear.id">Academic Year</Label>
                  <AvInput id="invoice-academicYear" type="select" className="form-control" name="academicYearId">
                    <option value="" key="0" />
                    {academicYears
                      ? academicYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/invoice" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  feeCategories: storeState.feeCategory.entities,
  feeDetails: storeState.feeDetails.entities,
  dueDates: storeState.dueDate.entities,
  paymentRemainders: storeState.paymentRemainder.entities,
  colleges: storeState.college.entities,
  branches: storeState.branch.entities,
  students: storeState.student.entities,
  academicYears: storeState.academicYear.entities,
  invoiceEntity: storeState.invoice.entity,
  loading: storeState.invoice.loading,
  updating: storeState.invoice.updating,
  updateSuccess: storeState.invoice.updateSuccess
});

const mapDispatchToProps = {
  getFeeCategories,
  getFeeDetails,
  getDueDates,
  getPaymentRemainders,
  getColleges,
  getBranches,
  getStudents,
  getAcademicYears,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InvoiceUpdate);
