import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './admin-overview.reducer';
import { IAdminOverview } from 'app/shared/model/admin-overview.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdminOverviewDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AdminOverviewDetail extends React.Component<IAdminOverviewDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { adminOverviewEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AdminOverview [<b>{adminOverviewEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="chooseDate">Choose Date</span>
            </dt>
            <dd>
              <TextFormat value={adminOverviewEntity.chooseDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="section">Section</span>
            </dt>
            <dd>{adminOverviewEntity.section}</dd>
            <dt>
              <span id="lectureOne">Lecture One</span>
            </dt>
            <dd>{adminOverviewEntity.lectureOne}</dd>
            <dt>
              <span id="lectureTwo">Lecture Two</span>
            </dt>
            <dd>{adminOverviewEntity.lectureTwo}</dd>
            <dt>
              <span id="lectureThree">Lecture Three</span>
            </dt>
            <dd>{adminOverviewEntity.lectureThree}</dd>
            <dt>
              <span id="lectureFour">Lecture Four</span>
            </dt>
            <dd>{adminOverviewEntity.lectureFour}</dd>
            <dt>
              <span id="lectureFive">Lecture Five</span>
            </dt>
            <dd>{adminOverviewEntity.lectureFive}</dd>
            <dt>
              <span id="lectureSix">Lecture Six</span>
            </dt>
            <dd>{adminOverviewEntity.lectureSix}</dd>
            <dt>
              <span id="lectureSeven">Lecture Seven</span>
            </dt>
            <dd>{adminOverviewEntity.lectureSeven}</dd>
            <dt>
              <span id="lectureEight">Lecture Eight</span>
            </dt>
            <dd>{adminOverviewEntity.lectureEight}</dd>
            <dt>Department</dt>
            <dd>{adminOverviewEntity.departmentId ? adminOverviewEntity.departmentId : ''}</dd>
            <dt>Batch</dt>
            <dd>{adminOverviewEntity.batchId ? adminOverviewEntity.batchId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/admin-overview" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/admin-overview/${adminOverviewEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ adminOverview }: IRootState) => ({
  adminOverviewEntity: adminOverview.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AdminOverviewDetail);
