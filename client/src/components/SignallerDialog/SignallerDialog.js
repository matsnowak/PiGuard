import React, { Component } from 'react';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';

class SignallerDialog extends Component {
  state = {
    name: '',
  };

  handleChangeName = (event) => {
    this.setState({
      name: event.target.value,
    });
  };

  isSubmitDisabled = () => {
    return this.state.name === '';
  };

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
        disabled={this.isSubmitDisabled()}
        onTouchTap={this.handleCreate}
      />,
    ];


    return (
      <Dialog
        title="Create Signaller"
        actions={actions}
        modal={true}
        open={this.props.open}
      >
        <TextField
          value={this.state.name}
          onChange={this.handleChangeName}
          hintText="Name"
        />
      </Dialog>

    );
  }
}


export default SignallerDialog;
