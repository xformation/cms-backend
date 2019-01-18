import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './college.reducer';
import { ICollege } from 'app/shared/model/college.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICollegeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class CollegeDetail extends React.Component<ICollegeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { collegeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            College [<b>{collegeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="shortName">Short Name</span>
            </dt>
            <dd>{collegeEntity.shortName}</dd>
            <dt>
              <span id="logo">Logo</span>
            </dt>
            <dd>{collegeEntity.logo}</dd>
            <dt>
              <span id="backgroundImage">Background Image</span>
            </dt>
            <dd>{collegeEntity.backgroundImage}</dd>
            <dt>
              <span id="instructionInformation">Instruction Information</span>
            </dt>
            <dd>{collegeEntity.instructionInformation}</dd>
          </dl>
          <Button tag={Link} to="/entity/college" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/college/${collegeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ college }: IRootState) => ({
  collegeEntity: college.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CollegeDetail);
