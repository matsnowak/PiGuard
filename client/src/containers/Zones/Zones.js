import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

class Zones extends Component {
  render() {
    return (
      <div>

        <h3>Armed Zones </h3>
        <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
          {this.props.piguard.armedZones.map(zone => (
            <Card key={zone.id} style={{ display: 'flex', marginRight: '20px' }}>
              <CardHeader
                title={`Name: ${zone.name}`}
              />
              <CardActions>
                <FlatButton secondary label="Disarm zone" />
              </CardActions>
            </Card>
          ))}
        </div>

        <h3>Zones </h3>
        <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
          {this.props.piguard.zones.map(zone => (
            <Card key={zone.id} style={{ display: 'flex', marginRight: '20px' }}>
              <CardHeader
                title={`Name: ${zone.name}`}
              />
              <CardActions>
                <FlatButton label="Arm zone" onClick={() => this.props.actions.startArming(zone)}/>
                <FlatButton secondary label="Remove zone" onClick={() => this.props.actions.removeZone(zone)}/>
              </CardActions>
            </Card>
          ))}
        </div>

      </div>

    );
  }
}

export default Zones;
