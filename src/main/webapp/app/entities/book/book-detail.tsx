import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './book.reducer';
import { IBook } from 'app/shared/model/book.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBookDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BookDetail extends React.Component<IBookDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bookEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Book [<b>{bookEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="issueDate">Issue Date</span>
            </dt>
            <dd>
              <TextFormat value={bookEntity.issueDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="dueDate">Due Date</span>
            </dt>
            <dd>
              <TextFormat value={bookEntity.dueDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="noOfCopiesAvailable">No Of Copies Available</span>
            </dt>
            <dd>{bookEntity.noOfCopiesAvailable}</dd>
            <dt>
              <span id="status">Status</span>
            </dt>
            <dd>{bookEntity.status}</dd>
            <dt>
              <span id="receivedDate">Received Date</span>
            </dt>
            <dd>
              <TextFormat value={bookEntity.receivedDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>Student</dt>
            <dd>{bookEntity.studentId ? bookEntity.studentId : ''}</dd>
            <dt>Library</dt>
            <dd>{bookEntity.libraryId ? bookEntity.libraryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/book" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/book/${bookEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ book }: IRootState) => ({
  bookEntity: book.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BookDetail);
