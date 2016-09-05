import { login as restLogin } from './restService';

const AUTH_TOKEN = 'user';

export function isLogged() {
  const user = JSON.parse(localStorage.getItem(AUTH_TOKEN));
  return user !== null;
}

export function login(pin) {
  return restLogin(pin)
    .then(isSuccess => {
      if (isSuccess) {
        localStorage.setItem(AUTH_TOKEN, JSON.stringify({ pin }));
      }
      return isSuccess;
    })
}
