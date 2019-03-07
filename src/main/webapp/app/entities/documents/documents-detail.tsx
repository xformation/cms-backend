import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './documents.reducer';
import { IDocuments } from 'app/shared/model/documents.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDocumentsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DocumentsDetail extends React.Component<IDocumentsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { documentsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Documents [<b>{documentsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="documentName">Document Name</span>
            </dt>
            <dd>{documentsEntity.documentName}</dd>
            <dt>
              <span id="upload">Upload</span>
            </dt>
            <dd>{documentsEntity.upload}</dd>
            <dt>Student</dt>
            <dd>{documentsEntity.studentId ? documentsEntity.studentId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/documents" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/documents/${documentsEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ documents }: IRootState) => ({
  documentsEntity: documents.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DocumentsDetail);
