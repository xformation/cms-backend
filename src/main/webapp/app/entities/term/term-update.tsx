import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './term.reducer';
import { ITerm } from 'app/shared/model/term.model';
// tslint:disable-next-line:no-unused-variable

export interface ITermUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITermUpdateState {
  isNew: boolean;
  academicyearId: string;
}

export class TermUpdate extends React.Component<ITermUpdateProps, ITermUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      academicyearId: '0',
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
            <h2 id="cmsApp.term.home.createOrEditLabel">Create or edit a Term</h2>
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
                    <Label for="id">ID</Label>
                    <AvInput id="term-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="termsDescLabel" for="termsDesc">
                    Terms Desc
                  </Label>
                  <AvField
                    id="term-termsDesc"
                    type="text"
                    name="termsDesc"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="startDateLabel" for="startDate">
                    Start Date
                  </Label>
                  <AvField
                    id="term-startDate"
                    type="date"
                    className="form-control"
                    name="startDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="endDateLabel" for="endDate">
                    End Date
                  </Label>
                  <AvField
                    id="term-endDate"
                    type="date"
                    className="form-control"
                    name="endDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="termStatusLabel">Term Status</Label>
                  <AvInput
                    id="term-termStatus"
                    type="select"
                    className="form-control"
                    name="termStatus"
                    value={(!isNew && termEntity.termStatus) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput id="term-academicyear" type="select" className="form-control" name="academicyearId">
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
  academicYears: storeState.academicYear.entities,
  termEntity: storeState.term.entity,
  loading: storeState.term.loading,
  updating: storeState.term.updating,
  updateSuccess: storeState.term.updateSuccess
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
