import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

class SettingsDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      exitDelay: props.settings.exitDelay,
      disarmDelay: props.settings.disarmDelay
    }
  }

  handleChangeDisarmDelay = (event) => {
    const value = event.target.value;
    if (value === '' || !isNaN(value)) {
      this.setState({
        disarmDelay: event.target.value,
      });
    }

  };


  handleChangeExitDelay = (event) => {
    const value = event.target.value;
    if (value === '' || !isNaN(value)) {
      this.setState({
        exitDelay: event.target.value,
      });
    }
  };


  handleClose = () => {
    this.props.actions.setSettingsWindowVisibility(false);
  };

  handleUpdate = () => {
    this.props.actions.setSettingsWindowVisibility(false);
    const newSettings = this.props.settings;
    newSettings.exitDelay = this.state.exitDelay === '' ? 0 : this.state.exitDelay;
    newSettings.disarmDelay = this.state.disarmDelay === '' ? 0 : this.state.disarmDelay;
    this.props.actions.updateSettings(newSettings);
  };

  render() {
    const actions = [
      <FlatButton
        label="Cancel"
        primary
        onTouchTap={this.handleClose}
      />,
      <FlatButton
        label="Update"
        primary
        onTouchTap={this.handleUpdate}
      />,
    ];


    return (
      <Dialog
        title="Edit Settings"
        actions={actions}
        modal={true}
        contentStyle={{
          width: 350,
          maxWidth: 350,
        }}
        bodyStyle={{
          textAlign: 'center'
        }}
        open={this.props.open}
      >
        <TextField
          value={this.state.exitDelay}
          onChange={this.handleChangeExitDelay}
          floatingLabelText="Exit delay in seconds"
        />
        <TextField
          value={this.state.disarmDelay}
          onChange={this.handleChangeDisarmDelay}
          floatingLabelText="Disarm delay in seconds"
        />


      </Dialog>

    );
  }
}


export default SettingsDialog;
