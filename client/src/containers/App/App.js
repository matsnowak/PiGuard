import React, { Component } from 'react';
import './App.css';
import AuthorizedComponent from '../../components/Authorized/AuthorizedComponent';

import FloatingActionButton from 'material-ui/FloatingActionButton';
import ContentAdd from 'material-ui/svg-icons/content/add';

import AppBar from '../../components/AppBar/AppBar';

const style = {
  height: 50,
  width: 300,
  margin: 5,
  textAlign: 'center',
  display: 'block',
};

class App extends AuthorizedComponent {
  render() {
    return (
      <div className="App">
        <div style={{ flex: 0.3 }}>
          <AppBar location={this.props.location.pathname}/>
        </div>
        <div style={{ flex: 5 }}>

          {this.props.children}
        </div>
      </div>
    );
  }
}



export default App;
