import React from 'react';
import ReactDOM from 'react-dom';
import { Router, browserHistory } from 'react-router'
import routes from './routes';
import './index.css';
import { Provider } from 'react-redux'
import { syncHistoryWithStore } from 'react-router-redux'
import injectTapEventPlugin from 'react-tap-event-plugin';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import configureStore from './store';

const store = configureStore();
const history = syncHistoryWithStore(browserHistory, store);
injectTapEventPlugin();

ReactDOM.render(
  <Provider store={store}>
    <MuiThemeProvider>
      <Router history={history} routes={routes} />
    </MuiThemeProvider>
  </Provider>,
  document.getElementById('root')
);
