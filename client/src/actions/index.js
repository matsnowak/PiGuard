import { getSensors, getSensorsProfile, getSlots } from '../services/restService';

export const SIGNALLER_WINDOW_VISIBILITY = 'SIGNALLER_WINDOW_VISIBILITY';
export const ZONE_WINDOW_VISIBILITY = 'ZONE_WINDOW_VISIBILITY';
export const SENSOR_WINDOW_VISIBILITY = 'SENSOR_WINDOW_VISIBILITY';

export const LOAD_RESISTANCES = 'LOAD_RESISTANCES';
export const LOAD_TRIGGERS = 'LOAD_TRIGGERS';

export const LOAD_SIGNALLERS = 'LOAD_SIGNALLERS';
export const LOAD_ZONES = 'LOAD_ZONES';

export const LOAD_SENSORS = 'LOAD_SENSORS';
export const LOAD_SENSORS_PROFILE = 'LOAD_SENSORS_PROFILE';
export const LOAD_SLOTS = 'LOAD_SLOTS';

function loadSlots() {
  return dispatch => {
    getSlots()
      .then(slots => dispatch({
        type: LOAD_SLOTS,
        slots
      }))
  }
}

function loadSensorsProfile() {
  return dispatch => {
    getSensorsProfile()
      .then(sensorsProfile => dispatch({
        type: LOAD_SENSORS_PROFILE,
        sensorsProfile
      }))
  }
}

function loadSensors() {
  return dispatch => {
    getSensors()
      .then(sensors => dispatch({
        type: LOAD_SENSORS,
        sensors
      }))
  }
}

function setSignallerWindowVisibility(visibility) {
  return {
    type: SIGNALLER_WINDOW_VISIBILITY,
    visibility,
  }
}

function setZoneWindowVisibility(visibility) {
  return {
    type: ZONE_WINDOW_VISIBILITY,
    visibility,
  }
}

function setSensorWindowVisibility(visibility) {
  return {
    type: SENSOR_WINDOW_VISIBILITY,
    visibility,
  }
}

export default {
  setSignallerWindowVisibility,
  setZoneWindowVisibility,
  setSensorWindowVisibility,
  loadSensors,
  loadSensorsProfile,
  loadSlots
}
