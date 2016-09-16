import { timeoutPromise } from '../utils/timeoutPromise';
//todo declared two times, auth service
export const AUTH_TOKEN = 'user';

function getToken() {
  return localStorage.getItem(AUTH_TOKEN);
}

function getAuthHeader() {
  return { 'x-auth-token': `${getToken()}` };
}

export function login(token) {
  return fetch('auth/login', { headers: { 'Authorization': `Basic ${token}` }})
    .then(res => ({
      isSuccess: res.status !== 401,
      token: res.headers.get('x-auth-token'),
    }));
}

export function logout() {
  return fetch('auth/logout', { headers: { 'Authorization': `Basic ${getToken()}` }}).then((res) => res.status !== 401);
}

export function getSettings() {
  return checkRequest(fetch('api/v1/settings/1', { headers: getAuthHeader()}))
    .then(res => res.json())
}


export function updateSettings(settings) {
  return checkRequest(fetch('api/v1/settings/1',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'x-auth-token': getToken()
      },
      method: 'PUT',
      body: JSON.stringify(settings)
    })).then(res => res.json());
}

export function getSensors() {
  return checkRequest(fetch('api/v1/sensors?projection=inline', { headers: getAuthHeader()}))
    .then(res => res.json())

}

export function getSensorsProfile() {
  return checkRequest(fetch('api/v1/profile/sensors', { headers: getAuthHeader()}))
    .then(res => res.json())

}

export function getSignallers() {
  return checkRequest(fetch('api/v1/signallers?projection=inline', { headers: getAuthHeader()}))
    .then(res => res.json())
}

export function getSlots() {
  return checkRequest(fetch('api/v1/slots', { headers: getAuthHeader()}))
    .then(res => res.json())
}

export function getZones() {
  return checkRequest(fetch('api/v1/zones?projection=inline', { headers: getAuthHeader()}))
    .then(res => res.json())
}

export function getArmedZones() {
  return checkRequest(fetch('api/v1/armedzones?projection=inline', { headers: getAuthHeader()}))
    .then(res => res.json())
}

export function getFreeSlots() {
  return checkRequest(fetch('api/v1/slots/search/free', { headers: getAuthHeader()}))
    .then(res => res.json())
}

export function postSensor(sensor) {
  return checkRequest(fetch('api/v1/sensors?projection=inline',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'x-auth-token': getToken()
      },
      method: 'POST',
      body: JSON.stringify(sensor)
    })).then(res => res.json());
}


export function postSignaller(signaller) {
  return checkRequest(fetch('api/v1/signallers?projection=inline',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'x-auth-token': getToken()
      },
      method: 'POST',
      body: JSON.stringify(signaller)
    })).then(res => res.json());
}

export function postZone(zone) {
  return checkRequest(fetch('api/v1/zones?projection=inline',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'x-auth-token': getToken()
      },
      method: 'POST',
      body: JSON.stringify(zone)
    })).then(res => res.json());
}

export function postArmZone(zone) {
  return checkRequest(fetch('api/v1/armedzones?projection=inline',
    {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'x-auth-token': getToken()
      },
      method: 'POST',
      body: JSON.stringify(zone)
    })).then(res => res.json());
}

export function deleteZone(id) {
  return checkRequest(fetch(`api/v1/zones/${id}`,
    {
      method: 'DELETE',
      headers: getAuthHeader()
    }));
}

export function deleteSensor(id) {
  return checkRequest(fetch(`api/v1/sensors/${id}`,
    {
      method: 'DELETE',
      headers: getAuthHeader()
    }));
}

export function deleteSignaller(id) {
  return checkRequest(fetch(`api/v1/signallers/${id}`,
    {
      method: 'DELETE',
      headers: getAuthHeader()
    }));
}

export function deleteArmedZone(id) {
  return checkRequest(fetch(`api/v1/armedzones/${id}`,
    {
      method: 'DELETE',
      headers: getAuthHeader()
    }));
}

function checkRequest(request) {
  return request
    .then(res => {
      if (res.status < 300) return res;
      if (res.status === 401)  localStorage.removeItem(AUTH_TOKEN);
      else throw new Error(`${res.status}: ${res.statusText}`)
    }).catch(err => {
      alert(err.message);
      throw err;
    })
}
