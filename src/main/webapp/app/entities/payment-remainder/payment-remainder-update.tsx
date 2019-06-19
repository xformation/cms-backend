import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './payment-remainder.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPaymentRemainderUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPaymentRemainderUpdateState {
  isNew: boolean;
  collegeId: string;
  branchId: string;
}

export class PaymentRemainderUpdate extends React.Component<IPaymentRemainderUpdateProps, IPaymentRemainderUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
    const { paymentRemainderEntity, colleges, branches, loading, updating } = this.props;
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
                  <Label id="isAutoRemainderLabel" for="isAutoRemainder">
                    Is Auto Remainder
                  </Label>
                  <AvField
                    id="payment-remainder-isAutoRemainder"
                    type="text"
                    name="isAutoRemainder"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isFirstPaymentRemainderLabel" for="isFirstPaymentRemainder">
                    Is First Payment Remainder
                  </Label>
                  <AvField id="payment-remainder-isFirstPaymentRemainder" type="text" name="isFirstPaymentRemainder" />
                </AvGroup>
                <AvGroup>
                  <Label id="firstPaymentRemainderDaysLabel" for="firstPaymentRemainderDays">
                    First Payment Remainder Days
                  </Label>
                  <AvField
                    id="payment-remainder-firstPaymentRemainderDays"
                    type="string"
                    className="form-control"
                    name="firstPaymentRemainderDays"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isSecondPaymentRemainderLabel" for="isSecondPaymentRemainder">
                    Is Second Payment Remainder
                  </Label>
                  <AvField id="payment-remainder-isSecondPaymentRemainder" type="text" name="isSecondPaymentRemainder" />
                </AvGroup>
                <AvGroup>
                  <Label id="secondPaymentRemainderDaysLabel" for="secondPaymentRemainderDays">
                    Second Payment Remainder Days
                  </Label>
                  <AvField
                    id="payment-remainder-secondPaymentRemainderDays"
                    type="string"
                    className="form-control"
                    name="secondPaymentRemainderDays"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isOverDuePaymentRemainderLabel" for="isOverDuePaymentRemainder">
                    Is Over Due Payment Remainder
                  </Label>
                  <AvField id="payment-remainder-isOverDuePaymentRemainder" type="text" name="isOverDuePaymentRemainder" />
                </AvGroup>
                <AvGroup>
                  <Label id="overDuePaymentRemainderAfterDueDateOrUntilPaidLabel" for="overDuePaymentRemainderAfterDueDateOrUntilPaid">
                    Over Due Payment Remainder After Due Date Or Until Paid
                  </Label>
                  <AvField
                    id="payment-remainder-overDuePaymentRemainderAfterDueDateOrUntilPaid"
                    type="text"
                    name="overDuePaymentRemainderAfterDueDateOrUntilPaid"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="overDuePaymentRemainderDaysLabel" for="overDuePaymentRemainderDays">
                    Over Due Payment Remainder Days
                  </Label>
                  <AvField
                    id="payment-remainder-overDuePaymentRemainderDays"
                    type="string"
                    className="form-control"
                    name="overDuePaymentRemainderDays"
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="isRemainderRecipientsLabel" for="isRemainderRecipients">
                    Is Remainder Recipients
                  </Label>
                  <AvField id="payment-remainder-isRemainderRecipients" type="text" name="isRemainderRecipients" />
                </AvGroup>
                <AvGroup>
                  <Label id="remainderRecipientsLabel" for="remainderRecipients">
                    Remainder Recipients
                  </Label>
                  <AvField id="payment-remainder-remainderRecipients" type="text" name="remainderRecipients" />
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
  colleges: storeState.college.entities,
  branches: storeState.branch.entities,
  paymentRemainderEntity: storeState.paymentRemainder.entity,
  loading: storeState.paymentRemainder.loading,
  updating: storeState.paymentRemainder.updating,
  updateSuccess: storeState.paymentRemainder.updateSuccess
});

const mapDispatchToProps = {
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
