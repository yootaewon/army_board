import Home from "./pages/Home";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import Header from "./components/Header";
import store from "./redux/userStore";
import AuthCheck from "./router/AuthCheck";
import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useDispatch, Provider } from "react-redux";
import { checkLoginStatus } from "./redux/userSlice";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(checkLoginStatus());
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
