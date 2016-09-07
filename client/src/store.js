import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from './reducers';

export default function configureStore(preloadedState) {
  const windowIfDefined = typeof window === 'undefined' ? null : window;
  const devToolsExtension = windowIfDefined && windowIfDefined.devToolsExtension;
  const store = createStore(
    rootReducer,
    preloadedState,
    compose(
      applyMiddleware(thunk),
      devToolsExtension ? devToolsExtension() : f => f
    )
  );

  if (module.hot) {
    module.hot.accept('./reducers', () => {
      const nextRootReducer = require('./reducers');
      store.replaceReducer(nextRootReducer)
    })
  }

  return store
}
