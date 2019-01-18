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
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { getEntity, updateEntity, createEntity, reset } from './course-offer.reducer';
import { ICourseOffer } from 'app/shared/model/course-offer.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ICourseOfferUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ICourseOfferUpdateState {
  isNew: boolean;
  collegeId: number;
  departmentId: number;
  subjectId: number;
}

export class CourseOfferUpdate extends React.Component<ICourseOfferUpdateProps, ICourseOfferUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      collegeId: 0,
      departmentId: 0,
      subjectId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getColleges();
    this.props.getDepartments();
    this.props.getSubjects();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { courseOfferEntity } = this.props;
      const entity = {
        ...courseOfferEntity,
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
    this.props.history.push('/entity/course-offer');
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

  departmentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        departmentId: -1
      });
    } else {
      for (const i in this.props.departments) {
        if (id === this.props.departments[i].id.toString()) {
          this.setState({
            departmentId: this.props.departments[i].id
          });
        }
      }
    }
  };

  subjectUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        subjectId: -1
      });
    } else {
      for (const i in this.props.subjects) {
        if (id === this.props.subjects[i].id.toString()) {
          this.setState({
            subjectId: this.props.subjects[i].id
          });
        }
      }
    }
  };

  render() {
    const { courseOfferEntity, colleges, departments, subjects, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.courseOffer.home.createOrEditLabel">Create or edit a CourseOffer</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : courseOfferEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="course-offer-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="descLabel" for="desc">
                    Desc
                  </Label>
                  <AvField id="course-offer-desc" type="text" name="desc" />
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="course-offer-college" type="select" className="form-control" name="collegeId" onChange={this.collegeUpdate}>
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
                  <Label for="department.id">Department</Label>
                  <AvInput
                    id="course-offer-department"
                    type="select"
                    className="form-control"
                    name="departmentId"
                    onChange={this.departmentUpdate}
                  >
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
                <AvGroup>
                  <Label for="subject.id">Subject</Label>
                  <AvInput id="course-offer-subject" type="select" className="form-control" name="subjectId" onChange={this.subjectUpdate}>
                    <option value="" key="0" />
                    {subjects
                      ? subjects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/course-offer" replace color="info">
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
  departments: storeState.department.entities,
  subjects: storeState.subject.entities,
  courseOfferEntity: storeState.courseOffer.entity,
  loading: storeState.courseOffer.loading,
  updating: storeState.courseOffer.updating
});

const mapDispatchToProps = {
  getColleges,
  getDepartments,
  getSubjects,
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
)(CourseOfferUpdate);
