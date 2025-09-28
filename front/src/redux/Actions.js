export const login = () => ({
  type: "LOGIN",
});

export const logout = () => ({
  type: "LOGOUT",
});

export const accessToken = (token) => ({
  type: "TOKEN",
  payload: { token },
});
