import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDueDate } from 'app/shared/model/due-date.model';
import { getEntities as getDueDates } from 'app/entities/due-date/due-date.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './payment-remainder.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
// import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaymentRemainderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPaymentRemainderUpdateState {
  isNew: boolean;
  dueDateId: string;
  collegeId: string;
  branchId: string;
}

export class PaymentRemainderUpdate extends React.Component<IPaymentRemainderUpdateProps, IPaymentRemainderUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      dueDateId: '0',
      collegeId: '0',
      branchId: '0',
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

    this.props.getDueDates();
    this.props.getColleges();
    this.props.getBranches();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { paymentRemainderEntity } = this.props;
      const entity = {
        ...paymentRemainderEntity,
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
    this.props.history.push('/entity/payment-remainder');
  };

  render() {
    const { paymentRemainderEntity, dueDates, colleges, branches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.paymentRemainder.home.createOrEditLabel">Create or edit a PaymentRemainder</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : paymentRemainderEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="payment-remainder-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="feeRemainderLabel">Fee Remainder</Label>
                  <AvInput
                    id="payment-remainder-feeRemainder"
                    type="select"
                    className="form-control"
                    name="feeRemainder"
                    value={(!isNew && paymentRemainderEntity.feeRemainder) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="noticeDayLabel" for="noticeDay">
                    Notice Day
                  </Label>
                  <AvField
                    id="payment-remainder-noticeDay"
                    type="string"
                    className="form-control"
                    name="noticeDay"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="overDueRemainderLabel">Over Due Remainder</Label>
                  <AvInput
                    id="payment-remainder-overDueRemainder"
                    type="select"
                    className="form-control"
                    name="overDueRemainder"
                    value={(!isNew && paymentRemainderEntity.overDueRemainder) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="remainderRecipientsLabel" for="remainderRecipients">
                    Remainder Recipients
                  </Label>
                  <AvField
                    id="payment-remainder-remainderRecipients"
                    type="text"
                    name="remainderRecipients"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="dueDate.id">Due Date</Label>
                  <AvInput id="payment-remainder-dueDate" type="select" className="form-control" name="dueDateId">
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
                  <Label for="college.id">College</Label>
                  <AvInput id="payment-remainder-college" type="select" className="form-control" name="collegeId">
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
                  <AvInput id="payment-remainder-branch" type="select" className="form-control" name="branchId">
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
                <Button tag={Link} id="cancel-save" to="/entity/payment-remainder" replace color="info">
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
  dueDates: storeState.dueDate.entities,
  colleges: storeState.college.entities,
  branches: storeState.branch.entities,
  paymentRemainderEntity: storeState.paymentRemainder.entity,
  loading: storeState.paymentRemainder.loading,
  updating: storeState.paymentRemainder.updating,
  updateSuccess: storeState.paymentRemainder.updateSuccess
});

const mapDispatchToProps = {
  getDueDates,
  getColleges,
  getBranches,
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
)(PaymentRemainderUpdate);
