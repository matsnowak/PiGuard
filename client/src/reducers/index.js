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
  freeSlots: [],
  slots: [],
};

function convertSensor(sensor) {
  return {
    id: sensor.id,
    name: sensor.name,
    triggeredOn: sensor.triggeredOn,
    pullResistance: sensor.pullResistance,
    self: sensor._links.self.href,
    slot: sensor._links.slot.href,
    slotId: sensor.slotId,
  }
}

function convertSignaller(signaller) {
  return {
    id: signaller.id,
    name: signaller.name,
    slotId: signaller.slotId,
  }
}

function convertSlot(slot) {

  return {
    address: slot.address,
    description: slot.description,
    taken: false,
    link: slot._links.self.href,
    id: slot.id
  };
}

function mapSensors(state, sensors) {
  const convertedSensors = sensors._embedded.sensors.map(sensor => convertSensor(sensor));
  return Object.assign({}, state, { sensors: convertedSensors });
}

function mapFreeSlots(state, slots) {
  const loadedSlots = slots._embedded.slots.map(slot => convertSlot(slot));

  return Object.assign({}, state, { freeSlots: loadedSlots });
}

function mapSensorsProfile(state, sensorsProfile) {
  return state;
}

function removeSlot(state, slotId) {
  const filteredSlots = state.freeSlots.filter(slot => slot.id !== slotId);

  return Object.assign({}, state, { freeSlots: filteredSlots });
}

function mapSignallers(state, signallers) {
  const loadedSignallers = signallers._embedded.signallers.map(signaller => convertSignaller(signaller));

  return Object.assign({}, state, { signallers: loadedSignallers });
}

function mapSlots(state, slots) {
  const loadedSlots = slots._embedded.slots.map(slot => convertSlot(slot));

  return Object.assign({}, state, { slots: loadedSlots });
}

const piguard = (state = defaultDefinitions, action) => {
  switch(action.type) {
    case actions.LOAD_SENSORS: return mapSensors(state, action.sensors);
    case actions.LOAD_SIGNALLERS: return mapSignallers(state, action.signallers);
    case actions.LOAD_SENSORS_PROFILE: return mapSensorsProfile(state, action.sensorsProfile);
    case actions.LOAD_SLOTS: return mapSlots(state, action.slots);
    case actions.CREATE_SENSOR: return removeSlot(Object.assign({}, state, { sensors: state.sensors.concat([convertSensor(action.sensor)])}), action.sensor.slotId);
    case actions.CREATE_SIGNALLER: return removeSlot(Object.assign({}, state, { signallers: state.signallers.concat([convertSignaller(action.signaller)])}), action.signaller.slotId);
    case actions.LOAD_FREE_SLOTS: return mapFreeSlots(state, action.slots);
    default: return state;
  }
};

const rootReducer = combineReducers({
  visibilities: visibilityReducer,
  piguard,
  routing
});

export default rootReducer;
