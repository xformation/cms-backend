import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBatch } from 'app/shared/model/batch.model';
import { getEntities as getBatches } from 'app/entities/batch/batch.reducer';
import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { getEntity, updateEntity, createEntity, reset } from './add-new-book.reducer';
import { IAddNewBook } from 'app/shared/model/add-new-book.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAddNewBookUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAddNewBookUpdateState {
  isNew: boolean;
  batchId: string;
  subjectId: string;
  departmentId: string;
}

export class AddNewBookUpdate extends React.Component<IAddNewBookUpdateProps, IAddNewBookUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      batchId: '0',
      subjectId: '0',
      departmentId: '0',
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

    this.props.getBatches();
    this.props.getSubjects();
    this.props.getDepartments();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { addNewBookEntity } = this.props;
      const entity = {
        ...addNewBookEntity,
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
    this.props.history.push('/entity/add-new-book');
  };

  render() {
    const { addNewBookEntity, batches, subjects, departments, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.addNewBook.home.createOrEditLabel">Create or edit a AddNewBook</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : addNewBookEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="add-new-book-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="bookTitleLabel" for="bookTitle">
                    Book Title
                  </Label>
                  <AvField
                    id="add-new-book-bookTitle"
                    type="text"
                    name="bookTitle"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="authorLabel" for="author">
                    Author
                  </Label>
                  <AvField
                    id="add-new-book-author"
                    type="text"
                    name="author"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="bookIdLabel" for="bookId">
                    Book Id
                  </Label>
                  <AvField
                    id="add-new-book-bookId"
                    type="string"
                    className="form-control"
                    name="bookId"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="batch.id">Batch</Label>
                  <AvInput id="add-new-book-batch" type="select" className="form-control" name="batchId">
                    <option value="" key="0" />
                    {batches
                      ? batches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="subject.id">Subject</Label>
                  <AvInput id="add-new-book-subject" type="select" className="form-control" name="subjectId">
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
                <AvGroup>
                  <Label for="department.id">Department</Label>
                  <AvInput id="add-new-book-department" type="select" className="form-control" name="departmentId">
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
                <Button tag={Link} id="cancel-save" to="/entity/add-new-book" replace color="info">
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
  batches: storeState.batch.entities,
  subjects: storeState.subject.entities,
  departments: storeState.department.entities,
  addNewBookEntity: storeState.addNewBook.entity,
  loading: storeState.addNewBook.loading,
  updating: storeState.addNewBook.updating,
  updateSuccess: storeState.addNewBook.updateSuccess
});

const mapDispatchToProps = {
  getBatches,
  getSubjects,
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
)(AddNewBookUpdate);
