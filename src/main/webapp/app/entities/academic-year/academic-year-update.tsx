import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHoliday } from 'app/shared/model/holiday.model';
import { getEntities as getHolidays } from 'app/entities/holiday/holiday.reducer';
import { ITerm } from 'app/shared/model/term.model';
import { getEntities as getTerms } from 'app/entities/term/term.reducer';
import { getEntity, updateEntity, createEntity, reset } from './academic-year.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAcademicYearUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAcademicYearUpdateState {
  isNew: boolean;
  holidayId: number;
  termId: number;
}

export class AcademicYearUpdate extends React.Component<IAcademicYearUpdateProps, IAcademicYearUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      holidayId: 0,
      termId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getHolidays();
    this.props.getTerms();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { academicYearEntity } = this.props;
      const entity = {
        ...academicYearEntity,
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
    this.props.history.push('/entity/academic-year');
  };

  render() {
    const { academicYearEntity, holidays, terms, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.academicYear.home.createOrEditLabel">
              <Translate contentKey="cmsApp.academicYear.home.createOrEditLabel">Create or edit a AcademicYear</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : academicYearEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="academic-year-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="yearLabel" for="year">
                    <Translate contentKey="cmsApp.academicYear.year">Year</Translate>
                  </Label>
                  <AvField
                    id="academic-year-year"
                    type="number"
                    className="form-control"
                    name="year"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="startDateLabel" for="startDate">
                    <Translate contentKey="cmsApp.academicYear.startDate">Start Date</Translate>
                  </Label>
                  <AvField
                    id="academic-year-startDate"
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
                    <Translate contentKey="cmsApp.academicYear.endDate">End Date</Translate>
                  </Label>
                  <AvField
                    id="academic-year-endDate"
                    type="date"
                    className="form-control"
                    name="endDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="holiday.id">
                    <Translate contentKey="cmsApp.academicYear.holiday">Holiday</Translate>
                  </Label>
                  <AvInput id="academic-year-holiday" type="select" className="form-control" name="holidayId">
                    <option value="" key="0" />
                    {holidays
                      ? holidays.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="term.id">
                    <Translate contentKey="cmsApp.academicYear.term">Term</Translate>
                  </Label>
                  <AvInput id="academic-year-term" type="select" className="form-control" name="termId">
                    <option value="" key="0" />
                    {terms
                      ? terms.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/academic-year" replace color="info">
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
  holidays: storeState.holiday.entities,
  terms: storeState.term.entities,
  academicYearEntity: storeState.academicYear.entity,
  loading: storeState.academicYear.loading,
  updating: storeState.academicYear.updating
});

const mapDispatchToProps = {
  getHolidays,
  getTerms,
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
)(AcademicYearUpdate);
