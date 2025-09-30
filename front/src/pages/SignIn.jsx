import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { login } from "../redux/userSlice";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";

const SignIn = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [armyNumber, setArmyNumber] = useState("");
  const [password, setPassword] = useState("");

  const userSignIn = () => {
    axios
      .post("/api/signIn", { armyNumber, password })
      .then((res) => {
        const token = res.data.accessToken;
        localStorage.setItem("accessToken", token);
        dispatch(login(token));
        navigate("/");
      })
      .catch((err) => {
        toast.error(err.response?.data?.message || "로그인 실패");
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
          <button className="btn btn-primary w-100 py-2" onClick={userSignIn}>
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
