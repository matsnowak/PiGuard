import { login as restLogin, logout as restLogout } from './restService';

export const AUTH_TOKEN = 'user';

export function isLogged() {
  return localStorage.getItem(AUTH_TOKEN) !== null;
}

export function login(pin) {
  const token = btoa(`defaultuser:${pin}`);

  return restLogin(token)
    .then(loginResponse => {
      if (loginResponse.isSuccess) {
        localStorage.setItem(AUTH_TOKEN, loginResponse.token);
      }
      return loginResponse.isSuccess;
    })
}

export function logout() {
 const token = localStorage.getItem(AUTH_TOKEN);
  if (token !== null) {
    return restLogout(token)
      .then(() => {
        localStorage.removeItem(AUTH_TOKEN);
      })
  }
}
