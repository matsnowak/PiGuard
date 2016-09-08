import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';

class Signallers extends Component {
  render() {
    return (
      <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'space-around' }}>
        {this.props.piguard.signallers.map(signaller => (
          <Card style={{ display: 'flex'}}>
            <CardHeader
              title={signaller.name}
            />
            <CardText>
              {this.props.piguard.slots.find(slot => slot.id === signaller.slotId).description}
            </CardText>
          </Card>
        ))}
      </div>
    );
  }
}

export default Signallers;
