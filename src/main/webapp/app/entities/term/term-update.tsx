import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './term.reducer';
import { ITerm } from 'app/shared/model/term.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITermUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ITermUpdateState {
  isNew: boolean;
  academicYearId: number;
}

export class TermUpdate extends React.Component<ITermUpdateProps, ITermUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicYearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { termEntity } = this.props;
      const entity = {
        ...termEntity,
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
    this.props.history.push('/entity/term');
  };

  render() {
    const { termEntity, academicYears, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.term.home.createOrEditLabel">
              <Translate contentKey="cmsApp.term.home.createOrEditLabel">Create or edit a Term</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : termEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="term-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="termsDescLabel" for="termsDesc">
                    <Translate contentKey="cmsApp.term.termsDesc">Terms Desc</Translate>
                  </Label>
                  <AvField
                    id="term-termsDesc"
                    type="text"
                    name="termsDesc"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="startDateLabel" for="startDate">
                    <Translate contentKey="cmsApp.term.startDate">Start Date</Translate>
                  </Label>
                  <AvField
                    id="term-startDate"
                    type="date"
                    className="form-control"
                    name="startDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="endDateLabel" for="endDate">
                    <Translate contentKey="cmsApp.term.endDate">End Date</Translate>
                  </Label>
                  <AvField
                    id="term-endDate"
                    type="date"
                    className="form-control"
                    name="endDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="cmsApp.term.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="term-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && termEntity.status) || 'PRESENT'}
                  >
                    <option value="PRESENT">
                      <Translate contentKey="cmsApp.Status.PRESENT" />
                    </option>
                    <option value="ABSENT">
                      <Translate contentKey="cmsApp.Status.ABSENT" />
                    </option>
                    <option value="ACTIVE">
                      <Translate contentKey="cmsApp.Status.ACTIVE" />
                    </option>
                    <option value="DEACTIVE">
                      <Translate contentKey="cmsApp.Status.DEACTIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicYear.id">
                    <Translate contentKey="cmsApp.term.academicYear">Academic Year</Translate>
                  </Label>
                  <AvInput id="term-academicYear" type="select" className="form-control" name="academicYearId">
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
                <Button tag={Link} id="cancel-save" to="/entity/term" replace color="info">
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
  academicYears: storeState.academicYear.entities,
  termEntity: storeState.term.entity,
  loading: storeState.term.loading,
  updating: storeState.term.updating
});

const mapDispatchToProps = {
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
)(TermUpdate);
