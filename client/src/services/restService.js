import { timeoutPromise } from '../utils/timeoutPromise';

export function login(pin) {
  return timeoutPromise(200).then(() => pin === '0000');
}

export function getSensors() {
  return fetch('api/v1/sensors')
    .then(res => res.json())

}

export function getSensorsProfile() {
  return fetch('api/v1/profile/sensors')
    .then(res => res.json())

}

export function getSignallers() {
  return fetch('api/v1/signallers')
    .then(res => res.json())
}

export function getSlots() {
  return fetch('api/v1/slots')
    .then(res => res.json())
}

export function getZones() {
  return fetch('api/v1/zones')
    .then(res => res.json())
}

export function getArmedZones() {
  return fetch('api/v1/armedzones')
    .then(res => res.json())
}

export function getFreeSlots() {
  return fetch('api/v1/slots/search/free')
    .then(res => res.json())
}

export function postSensor(sensor) {
  return checkRequest(fetch('api/v1/sensors',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(sensor)
    })).then(res => res.json());
}


export function postSignaller(signaller) {
  return checkRequest(fetch('api/v1/signallers',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(signaller)
    })).then(res => res.json());
}

export function postZone(zone) {
  return checkRequest(fetch('api/v1/zones',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(zone)
    })).then(res => res.json());
}

export function postArmZone(zone) {
  return checkRequest(fetch('api/v1/armedzones',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'POST',
      body: JSON.stringify(zone)
    })).then(res => res.json());
}

export function deleteZone(id) {
  return checkRequest(fetch(`api/v1/zones/${id}`,
    {
      method: 'DELETE'
    }));
}

export function deleteSensor(id) {
  return checkRequest(fetch(`api/v1/sensors/${id}`,
    {
      method: 'DELETE'
    }));
}

export function deleteSignaller(id) {
  return checkRequest(fetch(`api/v1/signallers/${id}`,
    {
      method: 'DELETE'
    }));
}


function checkRequest(request) {
  return request
    .then(res => {
      if (res.status < 300) return res;
      else throw new Error(`${res.status}: ${res.statusText}`)
    }).catch(err => {
      alert(err.message);
      throw err;
    })
}
