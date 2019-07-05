import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './add-new-book.reducer';
import { IAddNewBook } from 'app/shared/model/add-new-book.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAddNewBookDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AddNewBookDetail extends React.Component<IAddNewBookDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { addNewBookEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AddNewBook [<b>{addNewBookEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bookTitle">Book Title</span>
            </dt>
            <dd>{addNewBookEntity.bookTitle}</dd>
            <dt>
              <span id="author">Author</span>
            </dt>
            <dd>{addNewBookEntity.author}</dd>
            <dt>
              <span id="noOfCopies">No Of Copies</span>
            </dt>
            <dd>{addNewBookEntity.noOfCopies}</dd>
            <dt>
              <span id="bookId">Book Id</span>
            </dt>
            <dd>{addNewBookEntity.bookId}</dd>
            <dt>Batch</dt>
            <dd>{addNewBookEntity.batchId ? addNewBookEntity.batchId : ''}</dd>
            <dt>Subject</dt>
            <dd>{addNewBookEntity.subjectId ? addNewBookEntity.subjectId : ''}</dd>
            <dt>Department</dt>
            <dd>{addNewBookEntity.departmentId ? addNewBookEntity.departmentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/add-new-book" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/add-new-book/${addNewBookEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ addNewBook }: IRootState) => ({
  addNewBookEntity: addNewBook.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AddNewBookDetail);
