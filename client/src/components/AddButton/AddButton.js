import React, { Component } from 'react';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton/IconButton';
import ContentAdd from 'material-ui/svg-icons/content/add'

class AddButton extends Component {
  render() {
    return (
      <IconMenu
        style={{ height: '100%', marginTop: '5px' }}
        iconButtonElement={<IconButton><ContentAdd color="white" hoverColor="green"/></IconButton>}
        anchorOrigin={{horizontal: 'right', vertical: 'top'}}
        targetOrigin={{horizontal: 'right', vertical: 'top'}}
      >
        <MenuItem primaryText="Signaller" onClick={() => this.props.actions.setSignallerWindowVisibility(true)} />
        <MenuItem primaryText="Sensor" onClick={() => this.props.actions.setSensorWindowVisibility(true)} />
        <MenuItem primaryText="Zone" onClick={() => this.props.actions.setZoneWindowVisibility(true)} />
      </IconMenu>

    );
  }
}


export default AddButton;
