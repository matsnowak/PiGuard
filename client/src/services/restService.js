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

export function getSlots() {
  return fetch('api/v1/slots')
    .then(res => res.json())
}
