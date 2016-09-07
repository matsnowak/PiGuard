import { routerReducer as routing } from 'react-router-redux'
import { combineReducers } from 'redux'
import * as actions from '../actions';

const visibilityReducer = (state = { zoneWindow: false, signallerWindow: false, sensorWindow: false }, action) => {
  switch(action.type) {
    case actions.SIGNALLER_WINDOW_VISIBILITY: return Object.assign({}, state, { signallerWindow: action.visibility });
    case actions.SENSOR_WINDOW_VISIBILITY: return Object.assign({}, state, { sensorWindow: action.visibility });
    case actions.ZONE_WINDOW_VISIBILITY: return Object.assign({}, state, { zoneWindow: action.visibility });
    default: return state;
  }
};

function generateDefaultSlots() {
  const pins = [];
  for(let i = 0; i < 25; i++) {
    pins.push({ address: `SLOT_${i}`, taken: false, description: 'No description' })
  }
  return pins;
}

const defaultDefinitions = {
  triggers: ['LOW_STATE', 'HIGH_STATE', 'EDGE'],
  resistances: ['OFF', 'PULL_DOWN', 'PULL_UP'],
  sensors: [],
  signallers: [],
  zones: [],
  slots: generateDefaultSlots()
};

function mapSensors(state, sensors) {
  console.log(sensors);
  return state;
}

function mapSensorsProfile(state, sensorsProfile) {
  return state;
}

function mapSlots(state, slots) {
  const loadedSlots = slots._embedded.slots.map(slot => ({
    address: slot.address,
    description: slot.description,
    taken: false,
    link: slot._links.self.href
  }));

  return Object.assign({}, state, { slots: loadedSlots });
}

const piguard = (state = defaultDefinitions, action) => {
  switch(action.type) {
    case actions.LOAD_SENSORS: return mapSensors(state, action.sensors);
    case actions.LOAD_SENSORS_PROFILE: return mapSensorsProfile(state, action.sensorsProfile);
    case actions.LOAD_SLOTS: return mapSlots(state, action.slots);
    default: return state;
  }
};

const rootReducer = combineReducers({
  visibilities: visibilityReducer,
  piguard,
  routing
});

export default rootReducer;
