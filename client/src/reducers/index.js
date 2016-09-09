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
  armedZones: [],
  freeSlots: [],
  slots: [],
  arming: false,
  armingZone: undefined,
};

function convertSensor(sensor) {
  return {
    id: sensor.id,
    name: sensor.name,
    triggeredOn: sensor.triggeredOn,
    pullResistance: sensor.pullResistance,
    link: sensor._links.self.href,
    slotId: sensor.slotId,
  }
}

function convertSignaller(signaller) {
  return {
    id: signaller.id,
    name: signaller.name,
    slotId: signaller.slotId,
    link: signaller._links.self.href,
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

function convertZone(zone) {
  return {
    id: zone.id,
    name: zone.name,
    link: zone._links.self.href,
    //todo add sensors and signallers later

  }
}

function convertArmedZone(armedZone) {
  return {
    id: armedZone.id,
    name: armedZone.name,
    link: armedZone._links.self.href,
    date: armedZone.startGuardFrom
    //todo add sensors and signallers later

  }
}

function mapSensors(state, sensors) {
  const convertedSensors = sensors._embedded.sensors.map(sensor => convertSensor(sensor));
  return Object.assign({}, state, { sensors: convertedSensors });
}

function mapZones(state, zones) {
  const convertedZones = zones._embedded.zones.map(zone => convertZone(zone));
  return Object.assign({}, state, { zones: convertedZones });
}

function mapArmedZones(state, armedZones) {
  const convertedArmedZones = armedZones._embedded.armedzones.map(armedZone => convertArmedZone(armedZone));
  return Object.assign({}, state, { armedZones: convertedArmedZones });
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

function unfreeSlot(state, slotId) {
  const index = state.slots.findIndex(slot => slot.id === slotId);

  if (index > -1) {
    const removedSlot = state.slots.splice(index, 1);
    state.freeSlots.push(removedSlot[0]);

    return Object.assign({}, state);
  }

  return state;

}

function removeSignaller(state, signallerToRemove) {
  const index = state.signallers.findIndex(signaller => signaller.id === signallerToRemove.id);

  if (index > -1) {
    state.signallers.splice(index, 1);
    return Object.assign({}, state);
  }

  return state;
}

function removeSensor(state, sensorToRemove) {
  const index = state.sensors.findIndex(sensor => sensor.id === sensorToRemove.id);

  if (index > -1) {
    state.sensors.splice(index, 1);
    return Object.assign({}, state);
  }

  return state;
}

function removeZone(state, zoneToRemove) {
  const index = state.zones.findIndex(zone => zone.id === zoneToRemove.id);

  if (index > -1) {
    state.zones.splice(index, 1);
    return Object.assign({}, state);
  }

  return state;
}

function convertArmedZone(armedZone) {
  return armedZone;
}

function armZone(state, armedZone) {
  const afterRemovingZone = remove(state, 'zones', armedZone.id);
  afterRemovingZone.armedZones.push(convertArmedZone(armedZone));
  return Object.assign({}, afterRemovingZone);
}

function remove(state, collectionName, id) {
  const index = state[collectionName].findIndex(element => element.id === id);

  if (index > -1) {
    state[collectionName].splice(index, 1);
    return Object.assign({}, state);
  }

  return state;
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
    case actions.REMOVE_SENSOR: return unfreeSlot(removeSensor(state, action.sensor), action.sensor.slotId);
    case actions.REMOVE_SIGNALLER: return unfreeSlot(removeSignaller(state, action.signaller), action.signaller.slotId);
    case actions.CREATE_ZONE: return Object.assign({}, state, { zones: state.zones.concat([convertZone(action.zone)])});
    case actions.REMOVE_ZONE: return removeZone(state, action.zone);
    case actions.START_ARMING: return Object.assign({}, state, { arming: true, armingZone: action.zone });
    case actions.END_ARMING: return Object.assign({}, state, { arming: false, armingZone: undefined });
    case actions.ARM_ZONE: return armZone(state, action.armedZone);
    case actions.LOAD_ZONES: return mapZones(state, action.zones);
    case actions.LOAD_ARMED_ZONES: return mapArmedZones(state, action.armedZones);
    default: return state;
  }
};

const rootReducer = combineReducers({
  visibilities: visibilityReducer,
  piguard,
  routing
});

export default rootReducer;
