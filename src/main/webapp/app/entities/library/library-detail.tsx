import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './library.reducer';
import { ILibrary } from 'app/shared/model/library.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILibraryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LibraryDetail extends React.Component<ILibraryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { libraryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Library [<b>{libraryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bookTitle">Book Title</span>
            </dt>
            <dd>{libraryEntity.bookTitle}</dd>
            <dt>
              <span id="author">Author</span>
            </dt>
            <dd>{libraryEntity.author}</dd>
            <dt>
              <span id="noOfCopies">No Of Copies</span>
            </dt>
            <dd>{libraryEntity.noOfCopies}</dd>
            <dt>
              <span id="bookId">Book Id</span>
            </dt>
            <dd>{libraryEntity.bookId}</dd>
            <dt>Batch</dt>
            <dd>{libraryEntity.batchId ? libraryEntity.batchId : ''}</dd>
            <dt>Subject</dt>
            <dd>{libraryEntity.subjectId ? libraryEntity.subjectId : ''}</dd>
            <dt>Department</dt>
            <dd>{libraryEntity.departmentId ? libraryEntity.departmentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/library" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/library/${libraryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ library }: IRootState) => ({
  libraryEntity: library.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LibraryDetail);
