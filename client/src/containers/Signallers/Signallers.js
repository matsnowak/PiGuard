import React, { Component } from 'react';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';

class Signallers extends Component {
  render() {
    return (
      <div style={{ marginTop: '20px', display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
        {this.props.piguard.signallers.map(signaller => (
          <Card key={signaller.id} style={{ display: 'flex', marginRight: '20px' }}>
            <CardHeader
              title={`Name: ${signaller.name}`}
            />
            <CardText style={{ textAlign: 'left' }}>
              Slot: {this.props.piguard.slots.find(slot => slot.id === signaller.slotId).description}
            </CardText>
            <CardActions>
              <FlatButton secondary label="Remove signaller" onClick={() => this.props.actions.removeSignaller(signaller)}/>
            </CardActions>
          </Card>
        ))}
      </div>
    );
  }
}

export default Signallers;
