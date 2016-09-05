import React, { Component } from 'react';
import './App.css';
import AuthorizedComponent from '../../components/Authorized/AuthorizedComponent';

class App extends AuthorizedComponent {
  render() {
    return (
      <div className="App">
        App
      </div>
    );
  }
}

export default App;
