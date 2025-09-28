import React, { useEffect } from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Header from "./components/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import AuthCheck from "./router/AuthCheck";
import { loadTokenFromStorage } from "./utils/Auth";
import Profile from "./pages/Profile";
import { Provider } from "react-redux";
import store from "./redux/Store";

function App() {
  useEffect(() => {
    loadTokenFromStorage();
  }, []);
  return (
    <BrowserRouter>
      <Provider store={store}>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
          <Route element={<AuthCheck />}>
            <Route path="/profile" element={<Profile />} />
            <Route path="/signIn" element={<SignIn />} />
            <Route path="/signIn" element={<SignIn />} />
          </Route>
        </Routes>
      </Provider>
    </BrowserRouter>
  );
}

export default App;
