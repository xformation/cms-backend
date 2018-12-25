import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './college-branches.reducer';
import { ICollegeBranches } from 'app/shared/model/college-branches.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICollegeBranchesDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export class CollegeBranchesDetail extends React.Component<ICollegeBranchesDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { collegeBranchesEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.collegeBranches.detail.title">CollegeBranches</Translate> [<b>{collegeBranchesEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="branchName">
                <Translate contentKey="cmsApp.collegeBranches.branchName">Branch Name</Translate>
              </span>
            </dt>
            <dd>{collegeBranchesEntity.branchName}</dd>
            <dt>
              <span id="description">
                <Translate contentKey="cmsApp.collegeBranches.description">Description</Translate>
              </span>
            </dt>
            <dd>{collegeBranchesEntity.description}</dd>
            <dt>
              <span id="collegeHead">
                <Translate contentKey="cmsApp.collegeBranches.collegeHead">College Head</Translate>
              </span>
            </dt>
            <dd>{collegeBranchesEntity.collegeHead}</dd>
          </dl>
          <Button tag={Link} to="/entity/college-branches" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/college-branches/${collegeBranchesEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ collegeBranches }: IRootState) => ({
  collegeBranchesEntity: collegeBranches.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CollegeBranchesDetail);
