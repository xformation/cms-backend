import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ILibrary } from 'app/shared/model/library.model';
import { getEntities as getLibraries } from 'app/entities/library/library.reducer';
import { getEntity, updateEntity, createEntity, reset } from './book.reducer';
import { IBook } from 'app/shared/model/book.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBookUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBookUpdateState {
  isNew: boolean;
  studentId: string;
  libraryId: string;
}

export class BookUpdate extends React.Component<IBookUpdateProps, IBookUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: '0',
      libraryId: '0',
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

    this.props.getStudents();
    this.props.getLibraries();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { bookEntity } = this.props;
      const entity = {
        ...bookEntity,
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
    this.props.history.push('/entity/book');
  };

  render() {
    const { bookEntity, students, libraries, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.book.home.createOrEditLabel">Create or edit a Book</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : bookEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="book-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="issueDateLabel" for="issueDate">
                    Issue Date
                  </Label>
                  <AvField
                    id="book-issueDate"
                    type="date"
                    className="form-control"
                    name="issueDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dueDateLabel" for="dueDate">
                    Due Date
                  </Label>
                  <AvField
                    id="book-dueDate"
                    type="date"
                    className="form-control"
                    name="dueDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="noOfCopiesAvailableLabel" for="noOfCopiesAvailable">
                    No Of Copies Available
                  </Label>
                  <AvField
                    id="book-noOfCopiesAvailable"
                    type="string"
                    className="form-control"
                    name="noOfCopiesAvailable"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">Status</Label>
                  <AvInput
                    id="book-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && bookEntity.status) || 'AVAILABLE'}
                  >
                    <option value="AVAILABLE">AVAILABLE</option>
                    <option value="RESERVED">RESERVED</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="receivedDateLabel" for="receivedDate">
                    Received Date
                  </Label>
                  <AvField id="book-receivedDate" type="date" className="form-control" name="receivedDate" />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="book-student" type="select" className="form-control" name="studentId">
                    <option value="" key="0" />
                    {students
                      ? students.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="library.id">Library</Label>
                  <AvInput id="book-library" type="select" className="form-control" name="libraryId">
                    <option value="" key="0" />
                    {libraries
                      ? libraries.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/book" replace color="info">
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
  students: storeState.student.entities,
  libraries: storeState.library.entities,
  bookEntity: storeState.book.entity,
  loading: storeState.book.loading,
  updating: storeState.book.updating,
  updateSuccess: storeState.book.updateSuccess
});

const mapDispatchToProps = {
  getStudents,
  getLibraries,
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
)(BookUpdate);
