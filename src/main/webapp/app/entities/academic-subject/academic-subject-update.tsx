import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDepartments } from 'app/shared/model/departments.model';
import { getEntities as getDepartments } from 'app/entities/departments/departments.reducer';
import { getEntity, updateEntity, createEntity, reset } from './academic-subject.reducer';
import { IAcademicSubject } from 'app/shared/model/academic-subject.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAcademicSubjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAcademicSubjectUpdateState {
  isNew: boolean;
  departmentId: number;
}

export class AcademicSubjectUpdate extends React.Component<IAcademicSubjectUpdateProps, IAcademicSubjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getDepartments();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { academicSubjectEntity } = this.props;
      const entity = {
        ...academicSubjectEntity,
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
    this.props.history.push('/entity/academic-subject');
  };

  render() {
    const { academicSubjectEntity, departments, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.academicSubject.home.createOrEditLabel">
              <Translate contentKey="cmsApp.academicSubject.home.createOrEditLabel">Create or edit a AcademicSubject</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : academicSubjectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="academic-subject-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="subjectNameLabel" for="subjectName">
                    <Translate contentKey="cmsApp.academicSubject.subjectName">Subject Name</Translate>
                  </Label>
                  <AvField
                    id="academic-subject-subjectName"
                    type="text"
                    name="subjectName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="electiveSubLabel" check>
                    <AvInput id="academic-subject-electiveSub" type="checkbox" className="form-control" name="electiveSub" />
                    <Translate contentKey="cmsApp.academicSubject.electiveSub">Elective Sub</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">
                    <Translate contentKey="cmsApp.academicSubject.department">Department</Translate>
                  </Label>
                  <AvInput id="academic-subject-department" type="select" className="form-control" name="departmentId">
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/academic-subject" replace color="info">
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
  departments: storeState.departments.entities,
  academicSubjectEntity: storeState.academicSubject.entity,
  loading: storeState.academicSubject.loading,
  updating: storeState.academicSubject.updating
});

const mapDispatchToProps = {
  getDepartments,
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
)(AcademicSubjectUpdate);
