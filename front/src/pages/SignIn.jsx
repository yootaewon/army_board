import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import mnd from "../asset/mnd.png";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";

const SignIn = () => {
  const navigate = useNavigate();

  const [armyNumber, setArmyNumber] = useState();
  const [password, setPassword] = useState();

  const userSignIn = () => {
    axios
      .post("/api/signIn", { armyNumber, password })
      .then((res) => {
        localStorage.setItem("accessToken", res.data.access);
        localStorage.setItem("refreshToken", res.data.refresh);
        navigate("/");
      })
      .catch((err) => {
        toast.error(err.response.data);
      });
  };

  const goToSignUp = () => {
    navigate("/signUp");
  };

  return (
    <>
      <ToastContainer />
      <div className="d-flex justify-content-center align-items-center min-vh-100">
        <div className="m-auto" style={{ maxWidth: "400px", width: "100%" }}>
          <img className="mb-4" src={mnd} alt="" width="70" height="70" />
          <h1 className="h3 mb-3 fw-normal">로그인</h1>
          <div className="form-floating mb-3">
            <input
              className="form-control"
              id="armyNumber"
              onChange={(e) => setArmyNumber(e.target.value)}
            />
            <label htmlFor="armyNumber">군 번</label>
          </div>
          <div className="form-floating mb-3">
            <input
              type="password"
              className="form-control"
              id="password"
              onChange={(e) => setPassword(e.target.value)}
            />
            <label htmlFor="password">비밀번호</label>
          </div>
          <button
            className="btn btn-primary w-100 py-2"
            type="button"
            onClick={userSignIn}
          >
            로그인
          </button>
          <p className="mt-3">
            아직 회원이 아니신가요?{" "}
            <button
              className="btn btn-link"
              style={{ cursor: "pointer" }}
              onClick={goToSignUp}
            >
              회원가입
            </button>
          </p>
        </div>
      </div>
    </>
  );
};

export default SignIn;
