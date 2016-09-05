export function timeoutPromise(time) {
  return new Promise(function(resolve) {
    setTimeout(() => {
      resolve();
    }, time)
  });
}
