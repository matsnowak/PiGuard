import { timeoutPromise } from '../utils/timeoutPromise';
export function login(pin) {
  return timeoutPromise(200).then(() => pin === '0000');
}
