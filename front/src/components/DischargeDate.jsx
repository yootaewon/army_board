import { useState, useEffect } from "react";
import { toast, ToastContainer } from "react-toastify";
import api from "../api/AxiosInstance";

const DischargeDate = () => {
  const [enlistmentDate, setEnlistmentDate] = useState("");
  const [dischargeDate, setDischargeDate] = useState("");
  const [percent, setPercent] = useState("");

  useEffect(() => {
    const fetchDates = async () => {
      try {
        const res = await api.post("/dischargeDate");

        setEnlistmentDate(res.data.enlistmentDate);
        setDischargeDate(res.data.dischargeDate);
        setPercent(res.data.persent);
      } catch (err) {
        console.log(err);
        toast.error("날짜를 불러오는 데 실패했습니다.");
      }
    };

    fetchDates();
  }, []);

  return (
    <>
      <ToastContainer />
      <h2>입대일과 전역일</h2>
      <p>입대일: {enlistmentDate}</p>
      <p>전역일: {dischargeDate}</p>
      <p>전역일: {percent}</p>

      <div className="d-flex justify-content-center align-items-center min-vh-70">
        <div className="m-auto" style={{ maxWidth: "1000px", width: "100%" }}>
          <div className="progress">
            <div
              className="progress-bar"
              role="progressbar"
              style={{ width: `${percent || 0}%` }}
              aria-valuenow={parseFloat(percent) || 0}
              aria-valuemin="0"
              aria-valuemax="100"
            >
              {percent}%
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default DischargeDate;
