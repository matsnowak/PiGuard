import React, { Component, PropTypes } from 'react';
import { logout } from '../../services/authService';
import Paper from 'material-ui/Paper';
import {Tabs, Tab} from 'material-ui/Tabs';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton/IconButton';
import ContentAdd from 'material-ui/svg-icons/content/add';
import ContentSettings from 'material-ui/svg-icons/action/settings';
import ContentAccount from 'material-ui/svg-icons/action/account-box';
import ContentExit from 'material-ui/svg-icons/action/exit-to-app';

import AddButton from '../AddButton/AddButton';

const logoStyle = {
  height: 50,
  width: 150,
  margin: 5,
  textAlign: 'left',
  display: 'block',
};

class AppBar extends Component {
  static propTypes = {
    routes: PropTypes.array.isRequired
  };

  static contextTypes = {
    router: PropTypes.object.isRequired
  };

  onTabChange(path) {
    this.context.router.push(path);
  }

  render() {
    return (
      <Paper style={{backgroundColor: '#151517', display: 'flex', flexDirection: 'row', height: '100%'}}>
          <div style={{ flex: 2, }}>
            <div style={logoStyle}>
              <div className="Login-logo" style={{ height: '48px', width: '48px', display: 'inline-block', verticalAlign: 'middle' }}/>
              <h2 style={{ margin:0, display: 'inline-block', verticalAlign: 'middle', color: 'white'}}> Guard </h2>
            </div>
          </div>
          <div style={{ marginLeft: 100, marginRight: 100, flex: 20, height: '100%' }}>
            <Tabs
              style={{ height: '100%', background: 'none'}}
              tabItemContainerStyle={{ height: '100%', background: 'none'}}
              valueLink={{ value: this.props.location, requestChange: this.onTabChange.bind(this) }}
            >
              <Tab
                style={{ height: '100%' }}
                value={'/'}
                label='Zones'
              />
              <Tab
                style={{ height: '100%' }}
                value={'/signallers'}
                label='Signallers'
              />
              <Tab
                style={{ height: '100%' }}
                value={'/sensors'}
                label='Sensors'
              />
            </Tabs>
          </div>
          <div style={{ flex: 1, verticalAlign: 'middle', display: 'flex' }}>
            <IconButton style={{ margin: 'auto' }} onClick={() => this.props.actions.setAccountWindowVisibility(true)}>
              <ContentAccount color="white" hoverColor="green" />
            </IconButton>
            <IconButton style={{ margin: 'auto' }} onClick={() => this.props.actions.setSettingsWindowVisibility(true)}>
              <ContentSettings color="white" hoverColor="green" />
            </IconButton>
            <AddButton actions={this.props.actions} />
            <IconButton style={{ margin: 'auto' }} onClick={() => {
              logout();
              this.context.router.push('/login');
            }}>
              <ContentExit color="white" hoverColor="green" />
            </IconButton>
          </div>
      </Paper>

    );
  }
}


export default AppBar;
