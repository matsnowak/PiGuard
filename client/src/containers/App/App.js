import React, { Component } from 'react';
import './App.css';
import AuthorizedComponent from '../../components/Authorized/AuthorizedComponent';
import { connect } from 'react-redux';
import { bindActionCreators } from "redux";
import actions from '../../actions';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';

import AppBar from '../../components/AppBar/AppBar';
import SensorDialog from '../../components/SensorDialog/SensorDialog';
import SignallerDialog from '../../components/SignallerDialog/SignallerDialog';
import ZoneDialog from '../../components/ZoneDialog/ZoneDialog';

const style = {
  height: 50,
  width: 300,
  margin: 5,
  textAlign: 'center',
  display: 'block',
};

class App extends AuthorizedComponent {

  componentWillMount() {
    this.props.actions.loadFreeSlots();
    this.props.actions.loadSlots();

    this.props.actions.loadSignallers();
    this.props.actions.loadSensors();
  }

  render() {


    if (this.props.piguard.slots.length === 0) {
      return null;
    }

    return (
      <div className="App">
        <div style={{ flex: 0.3 }}>
          <AppBar location={this.props.location.pathname} actions={this.props.actions} />
        </div>
        <div style={{ flex: 5 }}>
          {React.cloneElement(this.props.children, { actions: this.props.actions, piguard: this.props.piguard })}
        </div>

        { this.props.visibilities.sensorWindow === false ? null : <SensorDialog piguard={this.props.piguard} open={this.props.visibilities.sensorWindow} setVisibility={this.props.actions.setSensorWindowVisibility} create={this.props.actions.createSensor} />}
        { this.props.visibilities.signallerWindow === false ? null : <SignallerDialog piguard={this.props.piguard} open={this.props.visibilities.signallerWindow} setVisibility={this.props.actions.setSignallerWindowVisibility} create={this.props.actions.createSignaller} />}
        { this.props.visibilities.zoneWindow === false ? null : <ZoneDialog piguard={this.props.piguard} open={this.props.visibilities.zoneWindow} setVisibility={this.props.actions.setZoneWindowVisibility} create={this.props.actions.createZone} />}

      </div>
    );
  }
}


function mapStateToProps(state) {
  return {
    visibilities: state.visibilities,
    piguard: state.piguard
  }
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(actions, dispatch)
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(App)


