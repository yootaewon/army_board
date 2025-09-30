import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  isLoggedIn: false,
  token: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    login: (state, action) => {
      state.isLoggedIn = true;
      state.token = action.payload;
    },
    logout: (state) => {
      state.isLoggedIn = false;
      state.token = null;
    },
  },
});

export const checkLoginStatus = () => (dispatch) => {
  const token = localStorage.getItem("accessToken");
  if (token) {
    dispatch(login(token));
  } else {
    dispatch(logout());
  }
};

export const { login, logout } = userSlice.actions;

export default userSlice.reducer;
