import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { getEntity, updateEntity, createEntity, reset } from './bank-accounts.reducer';
import { IBankAccounts } from 'app/shared/model/bank-accounts.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IBankAccountsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IBankAccountsUpdateState {
  isNew: boolean;
  branchId: number;
  collegeId: number;
}

export class BankAccountsUpdate extends React.Component<IBankAccountsUpdateProps, IBankAccountsUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: 0,
      collegeId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getBranches();
    this.props.getColleges();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { bankAccountsEntity } = this.props;
      const entity = {
        ...bankAccountsEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/bank-accounts');
  };

  branchUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        branchId: -1
      });
    } else {
      for (const i in this.props.branches) {
        if (id === this.props.branches[i].id.toString()) {
          this.setState({
            branchId: this.props.branches[i].id
          });
        }
      }
    }
  };

  collegeUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        collegeId: -1
      });
    } else {
      for (const i in this.props.colleges) {
        if (id === this.props.colleges[i].id.toString()) {
          this.setState({
            collegeId: this.props.colleges[i].id
          });
        }
      }
    }
  };

  render() {
    const { bankAccountsEntity, branches, colleges, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.bankAccounts.home.createOrEditLabel">Create or edit a BankAccounts</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bankAccountsEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="bank-accounts-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameOfBankLabel">Name Of Bank</Label>
                  <AvInput
                    id="bank-accounts-nameOfBank"
                    type="select"
                    className="form-control"
                    name="nameOfBank"
                    value={(!isNew && bankAccountsEntity.nameOfBank) || 'HDFC'}
                  >
                    <option value="HDFC">HDFC</option>
                    <option value="SBI">SBI</option>
                    <option value="ICICI">ICICI</option>
                    <option value="ANDHRABANK">ANDHRABANK</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="accountNumberLabel" for="accountNumber">
                    Account Number
                  </Label>
                  <AvField
                    id="bank-accounts-accountNumber"
                    type="number"
                    className="form-control"
                    name="accountNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfAccountLabel" for="typeOfAccount">
                    Type Of Account
                  </Label>
                  <AvField
                    id="bank-accounts-typeOfAccount"
                    type="text"
                    name="typeOfAccount"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="ifscCodeLabel" for="ifscCode">
                    Ifsc Code
                  </Label>
                  <AvField
                    id="bank-accounts-ifscCode"
                    type="text"
                    name="ifscCode"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="branchAddressLabel" for="branchAddress">
                    Branch Address
                  </Label>
                  <AvField
                    id="bank-accounts-branchAddress"
                    type="text"
                    name="branchAddress"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="corporateIdLabel" for="corporateId">
                    Corporate Id
                  </Label>
                  <AvField
                    id="bank-accounts-corporateId"
                    type="number"
                    className="form-control"
                    name="corporateId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="bank-accounts-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
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
                  <Label for="college.id">College</Label>
                  <AvInput id="bank-accounts-college" type="select" className="form-control" name="collegeId" onChange={this.collegeUpdate}>
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
                <Button tag={Link} id="cancel-save" to="/entity/bank-accounts" replace color="info">
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
  branches: storeState.branch.entities,
  colleges: storeState.college.entities,
  bankAccountsEntity: storeState.bankAccounts.entity,
  loading: storeState.bankAccounts.loading,
  updating: storeState.bankAccounts.updating
});

const mapDispatchToProps = {
  getBranches,
  getColleges,
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
)(BankAccountsUpdate);
