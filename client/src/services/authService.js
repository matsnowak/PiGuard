import { login as restLogin, logout as restLogout } from './restService';

export const AUTH_TOKEN = 'user';

export function isLogged() {
  const user = JSON.parse(localStorage.getItem(AUTH_TOKEN));
  return user !== null;
}

export function login(pin) {
  const token = btoa(`defaultuser:${pin}`);

  return restLogin(token)
    .then(isSuccess => {
      if (isSuccess) {
        localStorage.setItem(AUTH_TOKEN, JSON.stringify({ pin }));
      }
      return isSuccess;
    })
}

export function logout() {
 const token = localStorage.getItem(AUTH_TOKEN);
 return restLogout(token)
   .then(() => {
     localStorage.removeItem(AUTH_TOKEN);
   })
}
