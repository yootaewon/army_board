let accessToken = null;

export function getAccessToken() {
  return accessToken;
}

export function setAccessToken(token) {
  accessToken = token;
}

export function loadTokenFromStorage() {
  const token = localStorage.getItem("accessToken");
  if (token) {
    setAccessToken(token);
  }
}

export function clearTokens() {
  localStorage.removeItem("accessToken");
  setAccessToken(null);
}
