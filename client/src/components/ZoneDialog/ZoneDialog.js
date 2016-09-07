import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';

class SensorDialog extends Component {
  handleClose = () => {
    this.props.setVisibility(false);
  };

  handleCreate = () => {
    this.props.setVisibility(false);
  };

  render() {
    const actions = [
      <FlatButton
        label="Cancel"
        primary
        onTouchTap={this.handleClose}
      />,
      <FlatButton
        label="Create"
        primary
        disabled
        onTouchTap={this.handleCreate}
      />,
    ];


    return (
      <Dialog
        title="Create Sensor"
        actions={actions}
        modal={true}
        open={this.props.open}
      >
        Only actions can close this dialog.
      </Dialog>

    );
  }
}


export default SensorDialog;
