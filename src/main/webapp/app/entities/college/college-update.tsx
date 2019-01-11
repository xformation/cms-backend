import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { getEntity, updateEntity, createEntity, reset } from './college.reducer';
import { ICollege } from 'app/shared/model/college.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ICollegeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ICollegeUpdateState {
  isNew: boolean;
  branchId: number;
}

export class CollegeUpdate extends React.Component<ICollegeUpdateProps, ICollegeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: 0,
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { collegeEntity } = this.props;
      const entity = {
        ...collegeEntity,
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
    this.props.history.push('/entity/college');
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

  render() {
    const { collegeEntity, branches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.college.home.createOrEditLabel">
              <Translate contentKey="cmsApp.college.home.createOrEditLabel">Create or edit a College</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : collegeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="college-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="shortNameLabel" for="shortName">
                    <Translate contentKey="cmsApp.college.shortName">Short Name</Translate>
                  </Label>
                  <AvField
                    id="college-shortName"
                    type="text"
                    name="shortName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="logoLabel" for="logo">
                    <Translate contentKey="cmsApp.college.logo">Logo</Translate>
                  </Label>
                  <AvField
                    id="college-logo"
                    type="number"
                    className="form-control"
                    name="logo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="backgroundImageLabel" for="backgroundImage">
                    <Translate contentKey="cmsApp.college.backgroundImage">Background Image</Translate>
                  </Label>
                  <AvField
                    id="college-backgroundImage"
                    type="number"
                    className="form-control"
                    name="backgroundImage"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="instructionInformationLabel" for="instructionInformation">
                    <Translate contentKey="cmsApp.college.instructionInformation">Instruction Information</Translate>
                  </Label>
                  <AvField
                    id="college-instructionInformation"
                    type="text"
                    name="instructionInformation"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">
                    <Translate contentKey="cmsApp.college.branch">Branch</Translate>
                  </Label>
                  <AvInput id="college-branch" type="select" className="form-control" name="branchId" onChange={this.branchUpdate}>
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
                <Button tag={Link} id="cancel-save" to="/entity/college" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
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
  collegeEntity: storeState.college.entity,
  loading: storeState.college.loading,
  updating: storeState.college.updating
});

const mapDispatchToProps = {
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
)(CollegeUpdate);
