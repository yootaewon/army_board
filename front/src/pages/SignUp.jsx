import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignUp = () => {
  const navigate = useNavigate();

  const [armyType, setArmyTpye] = useState("army");
  const [armyNumber, setArmyNumber] = useState();
  const [enlistmentDate, setEnlistmentDate] = useState();
  const [password, setPassword] = useState();
  const [passwordCheck, setPasswordCheck] = useState();
  const [mail, setMail] = useState();
  const [phoneNumber, setPhoneNumber] = useState();
  const [formErrors, setFormErrors] = useState({});

  const handleSubmit = (e) => {
    errorFilter();
    axios
      .post("/signUp", {
        armyType,
        armyNumber,
        enlistmentDate,
        password,
        mail,
        phoneNumber,
      })
      .then((res) => {
        console.log("회원가입 성공");
      });
  };

  const errorFilter = () => {
    const errors = {};

    if (!armyNumber) errors.armyNumber = "군 번을 입력해주세요.";
    if (!enlistmentDate) errors.enlistmentDate = "군 입대일을 입력해주세요.";
    if (!password) errors.password = "비밀번호를 입력해주세요.";
    if (password !== passwordCheck) {
      errors.passwordCheck = "비밀번호가 일치하지 않습니다.";
    }

    if (Object.keys(errors).length > 0) {
      setFormErrors(errors);
      return;
    }

    setFormErrors({});
  };

  const goToSignIn = () => {
    navigate("/signIn");
  };

  return (
    <>
      <section className="vh-100 gradient-custom">
        <div className="container py-5 h-100">
          <div className="row justify-content-center align-items-center h-100">
            <div className="col-12 col-lg-9 col-xl-7">
              <div
                className="card shadow-2-strong card-registration"
                style={{ borderRadius: "15px" }}
              >
                <div className="card-body p-4 p-md-5">
                  <h3 className="mb-4 pb-2 pb-md-0 mb-md-5">회원가입</h3>

                  <div>
                    <div className="row">
                      <div className="col-md-12 mb-4">
                        <div className="form-check form-check-inline">
                          <input
                            className="form-check-input"
                            type="radio"
                            value="army"
                            checked={armyType === "army"}
                            onChange={(e) => setArmyTpye(e.target.value)}
                          />
                          <label className="form-check-label">육군</label>
                        </div>

                        <div className="form-check form-check-inline">
                          <input
                            className="form-check-input"
                            type="radio"
                            value="navy"
                            checked={armyType === "navy"}
                            onChange={(e) => setArmyTpye(e.target.value)}
                          />
                          <label className="form-check-label">해군</label>
                        </div>

                        <div className="form-check form-check-inline">
                          <input
                            className="form-check-input"
                            type="radio"
                            value="airForce"
                            checked={armyType === "airForce"}
                            onChange={(e) => setArmyTpye(e.target.value)}
                          />
                          <label className="form-check-label">공군</label>
                        </div>

                        <div className="form-check form-check-inline">
                          <input
                            className="form-check-input"
                            type="radio"
                            value="marine"
                            checked={armyType === "marine"}
                            onChange={(e) => setArmyTpye(e.target.value)}
                          />
                          <label className="form-check-label">해병대</label>
                        </div>
                      </div>
                    </div>

                    <div className="row">
                      <div className="col-md-6 mb-4">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="text"
                            className="form-control form-control-lg"
                            onChange={(e) => setArmyNumber(e.target.value)}
                          />
                          <label className="form-label" htmlFor="armyNumber">
                            *군 번
                          </label>
                        </div>
                        {formErrors.armyNumber && (
                          <div className="alert alert-danger" role="alert">
                            {formErrors.armyNumber}
                          </div>
                        )}
                      </div>
                      <div className="col-md-6 mb-4">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="date"
                            className="form-control form-control-lg"
                            onChange={(e) => setEnlistmentDate(e.target.value)}
                          />
                          <label
                            className="form-label"
                            htmlFor="enlistmentDate"
                          >
                            *군 입대일
                          </label>
                        </div>
                        {formErrors.enlistmentDate && (
                          <div className="alert alert-danger" role="alert">
                            {formErrors.enlistmentDate}
                          </div>
                        )}
                      </div>
                    </div>

                    <div className="row">
                      <div className="col-md-6 mb-4 pb-2">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="password"
                            className="form-control form-control-lg"
                            onChange={(e) => setPassword(e.target.value)}
                          />
                          <label className="form-label" htmlFor="password">
                            *비밀번호
                          </label>
                        </div>
                        {formErrors.password && (
                          <div className="alert alert-danger" role="alert">
                            {formErrors.password}
                          </div>
                        )}
                      </div>
                      <div className="col-md-6 mb-4 pb-2">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="password"
                            className="form-control form-control-lg"
                            onChange={(e) => setPasswordCheck(e.target.value)}
                          />
                          <label
                            className="form-label"
                            htmlFor="confirmPassword"
                          >
                            *비밀번호 재확인
                          </label>
                        </div>
                        {formErrors.passwordCheck && (
                          <div className="alert alert-danger" role="alert">
                            {formErrors.passwordCheck}
                          </div>
                        )}
                      </div>
                    </div>

                    <div className="row">
                      <div className="col-md-6 mb-4 pb-2">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="email"
                            className="form-control form-control-lg"
                            onChange={(e) => setMail(e.target.value)}
                          />
                          <label className="form-label" htmlFor="emailAddress">
                            이메일
                          </label>
                        </div>
                      </div>
                      <div className="col-md-6 mb-4 pb-2">
                        <div data-mdb-input-init className="form-outline">
                          <input
                            type="tel"
                            className="form-control form-control-lg"
                            onChange={(e) => setPhoneNumber(e.target.value)}
                          />
                          <label className="form-label" htmlFor="phoneNumber">
                            전화번호
                          </label>
                        </div>
                      </div>
                    </div>

                    <div className="mt-4 pt-2">
                      <button
                        type="button"
                        className="btn btn-primary btn-lg"
                        onClick={handleSubmit}
                      >
                        회원가입
                      </button>
                    </div>
                  </div>
                  <p className="mt-3">
                    이미 회원이신가요?{" "}
                    <button
                      className="underline"
                      style={{ cursor: "pointer" }}
                      onClick={goToSignIn}
                    >
                      로그인
                    </button>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
};

export default SignUp;
