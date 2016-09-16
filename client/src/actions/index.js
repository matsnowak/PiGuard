import { getSensors, getSensorsProfile, getSlots, getFreeSlots,
  postSensor, postSignaller,  getZones, getArmedZones, getSignallers,
  deleteSensor, deleteArmedZone, deleteSignaller, postZone, deleteZone, postArmZone,
  updateSettings as restUpdateSettings, getSettings } from '../services/restService';

import { logout } from '../services/authService';

export const SIGNALLER_WINDOW_VISIBILITY = 'SIGNALLER_WINDOW_VISIBILITY';
export const ZONE_WINDOW_VISIBILITY = 'ZONE_WINDOW_VISIBILITY';
export const SENSOR_WINDOW_VISIBILITY = 'SENSOR_WINDOW_VISIBILITY';
export const SETTINGS_WINDOW_VISIBILITY = 'SETTINGS_WINDOW_VISIBILITY';

export const LOAD_RESISTANCES = 'LOAD_RESISTANCES';
export const LOAD_TRIGGERS = 'LOAD_TRIGGERS';

export const LOAD_SIGNALLERS = 'LOAD_SIGNALLERS';
export const LOAD_ZONES = 'LOAD_ZONES';

export const LOAD_SENSORS = 'LOAD_SENSORS';
export const LOAD_SENSORS_PROFILE = 'LOAD_SENSORS_PROFILE';
export const LOAD_SLOTS = 'LOAD_SLOTS';
export const LOAD_FREE_SLOTS = 'LOAD_FREE_SLOTS';
export const CREATE_SENSOR = 'CREATE_SENSOR';
export const CREATE_SIGNALLER = 'CREATE_SIGNALLER';
export const REMOVE_SENSOR = 'REMOVE_SENSOR';
export const REMOVE_ZONE = 'REMOVE_ZONE';
export const REMOVE_ARMED_ZONE = 'REMOVE_ARMED_ZONE';
export const REMOVE_SIGNALLER = 'REMOVE_SIGNALLER';
export const CREATE_ZONE = 'CREATE_ZONE';

export const LOAD_ARMED_ZONES = 'LOAD_ARMED_ZONES';
export const START_ARMING = 'START_ARMING';
export const END_ARMING = 'END_ARMING';
export const ARM_ZONE = 'ARM_ZONE';

export const LOAD_SETTINGS = 'LOAD_SETTINGS';
export const UPDATE_SETTINGS = 'UPDATE_SETTINGS';

function loadSettings() {
  return dispatch => {
    getSettings()
      .then(settings => dispatch({
        type: LOAD_SETTINGS,
        settings,
      }))
  }
}

function updateSettings(settings) {
  return dispatch => {
    restUpdateSettings(settings)
      .then(() => getSettings())
      .then(settings => dispatch({
        type: LOAD_SETTINGS,
        settings,
      }))
  }
}

function startArming(zone) {
  return {
    type: START_ARMING,
    zone
  }
}

function endArming() {
  return {
    type: END_ARMING,
  }
}

function loadZones() {
  return dispatch => {
    getZones()
      .then(zones => dispatch({
        type: LOAD_ZONES,
        zones
      }))
  }
}

function loadArmedZones() {
  return dispatch => {
    getArmedZones()
      .then(armedZones => dispatch({
        type: LOAD_ARMED_ZONES,
        armedZones
      }))
  }
}

function autoLogout(delay, router) {
  setTimeout(() => {
    logout();
    router.push('/login');
  }, delay * 1000);
}

function armZone(zone, router) {
  return (dispatch, getState) => {
    postArmZone(zone)
      .then((armedZone) => {
        autoLogout(getState().piguard.settings.exitDelay, router);
        return dispatch({
          type: ARM_ZONE,
          armedZone
        })

      })
  }
}



function createSensor(sensor) {
  return dispatch => {
    postSensor(sensor)
      .then((createdSensor) => {
        return dispatch({
          type: CREATE_SENSOR,
          sensor: createdSensor
        })

      })
  }
}

function createZone(zone) {
  return dispatch => {
    postZone(zone)
      .then((createdZone) => {
        return dispatch({
          type: CREATE_ZONE,
          zone: createdZone
        })
      })
  }
}

function createSignaller(signaller) {
  return dispatch => {
    postSignaller(signaller)
      .then((createdSignaller) => {
        return dispatch({
          type: CREATE_SIGNALLER,
          signaller: createdSignaller
        })
      })
  }
}


function loadSignallers() {
  return dispatch => {
    getSignallers()
      .then(signallers => dispatch({
        type: LOAD_SIGNALLERS,
        signallers
      }))
  }
}

function loadSlots() {
  return dispatch => {
    getSlots()
      .then(slots => dispatch({
        type: LOAD_SLOTS,
        slots
      }))
  }
}

function loadFreeSlots() {
  return dispatch => {
    getFreeSlots()
      .then(slots => dispatch({
        type: LOAD_FREE_SLOTS,
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

function removeSensor(sensor) {
  return dispatch => {
    deleteSensor(sensor.id)
      .then(() => dispatch({
        type: REMOVE_SENSOR,
        sensor,
      }))
  }
}

function removeZone(zone) {
  return dispatch => {
    deleteZone(zone.id)
      .then(() => dispatch({
        type: REMOVE_ZONE,
        zone,
      }))
  }
}

function removeArmedZone(armedZone) {
  return dispatch => {
    deleteArmedZone(armedZone.id)
      .then(() => dispatch({
        type: REMOVE_ARMED_ZONE,
        armedZone,
      }))
  }
}

function removeSignaller(signaller) {
  return dispatch => {
    deleteSignaller(signaller.id)
      .then(() => dispatch({
        type: REMOVE_SIGNALLER,
        signaller,
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


function setSettingsWindowVisibility(visibility) {
  return {
    type: SETTINGS_WINDOW_VISIBILITY,
    visibility,
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
  loadSlots,
  createSensor,
  loadFreeSlots,
  createSignaller,
  loadSignallers,
  removeSensor,
  removeSignaller,
  createZone,
  removeZone,
  endArming,
  startArming,
  loadZones,
  loadArmedZones,
  armZone,
  removeArmedZone,
  loadSettings,
  updateSettings,
  setSettingsWindowVisibility,
}
