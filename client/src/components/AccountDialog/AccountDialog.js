import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';

class AccountDialog extends Component {

  constructor(props) {
    super(props);

    this.state = {
      oldPin: '',
      newPin: ''
    }
  }

  handleChangeOldPin = (event) => {
    const value = event.target.value;
    if (value === '' || !isNaN(value)) {
      this.setState({
        oldPin: event.target.value,
      });
    }

  };


  handleChangeNewPin = (event) => {
    const value = event.target.value;
    if (value === '' || !isNaN(value)) {
      this.setState({
        newPin: event.target.value,
      });
    }
  };

  handleClose = () => {
    this.props.actions.setAccountWindowVisibility(false);
  };

  handleUpdate = () => {
    this.props.actions.setAccountWindowVisibility(false);

    this.props.actions.updatePin({
      oldPin: this.state.oldPin,
      newPin: this.state.newPin
    });
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
        disabled={this.state.oldPin === '' || this.state.newPin === ''}
        onTouchTap={this.handleUpdate}
      />,
    ];


    return (
      <Dialog
        title="Edit Pin"
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
          value={this.state.oldPin}
          onChange={this.handleChangeOldPin}
          floatingLabelText="Current pin"
          type="password"
        />
        <TextField
          value={this.state.newPin}
          onChange={this.handleChangeNewPin}
          floatingLabelText="New pin"
          type="password"
        />


      </Dialog>

    );
  }
}


export default AccountDialog;
