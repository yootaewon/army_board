import React, { useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Header from "./components/Header";
import { useDispatch, Provider } from "react-redux";
import { login, logout } from "./redux/userSlice";
import store from "./redux/userStore";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthCheck from "./router/AuthCheck";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    if (token) {
      dispatch(login(token));
    } else {
      dispatch(logout());
    }
  }, [dispatch]);

  return (
    <Provider store={store}>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route element={<AuthCheck />}>
            <Route path="/profile" element={<SignIn />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
