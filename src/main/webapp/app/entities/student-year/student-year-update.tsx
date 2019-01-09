import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './student-year.reducer';
import { IStudentYear } from 'app/shared/model/student-year.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentYearUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentYearUpdateState {
  isNew: boolean;
}

export class StudentYearUpdate extends React.Component<IStudentYearUpdateProps, IStudentYearUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentYearEntity } = this.props;
      const entity = {
        ...studentYearEntity,
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
    this.props.history.push('/entity/student-year');
  };

  render() {
    const { studentYearEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentYear.home.createOrEditLabel">
              <Translate contentKey="cmsApp.studentYear.home.createOrEditLabel">Create or edit a StudentYear</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentYearEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="student-year-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="yearDescLabel">
                    <Translate contentKey="cmsApp.studentYear.yearDesc">Year Desc</Translate>
                  </Label>
                  <AvInput
                    id="student-year-yearDesc"
                    type="select"
                    className="form-control"
                    name="yearDesc"
                    value={(!isNew && studentYearEntity.yearDesc) || 'I'}
                  >
                    <option value="I">
                      <Translate contentKey="cmsApp.SYear.I" />
                    </option>
                    <option value="II">
                      <Translate contentKey="cmsApp.SYear.II" />
                    </option>
                    <option value="III">
                      <Translate contentKey="cmsApp.SYear.III" />
                    </option>
                    <option value="IV">
                      <Translate contentKey="cmsApp.SYear.IV" />
                    </option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student-year" replace color="info">
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
  studentYearEntity: storeState.studentYear.entity,
  loading: storeState.studentYear.loading,
  updating: storeState.studentYear.updating
});

const mapDispatchToProps = {
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
)(StudentYearUpdate);
