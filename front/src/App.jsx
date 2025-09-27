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

function App() {
  useEffect(() => {
    loadTokenFromStorage();
  }, []);
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signIn" element={<SignIn />} />
        <Route path="/signUp" element={<SignUp />} />
        <Route element={<AuthCheck />}>
          <Route path="/profile" element={<SignIn />} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signIn" element={<SignIn />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
