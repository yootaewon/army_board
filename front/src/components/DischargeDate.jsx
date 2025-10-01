import { useState, useEffect } from "react";
import { toast, ToastContainer } from "react-toastify";
import api from "../api/AxiosInstance";

const DischargeDate = () => {
  const [enlistmentDate, setEnlistmentDate] = useState("");
  const [dischargeDate, setDischargeDate] = useState("");
  const [percent, setPercent] = useState("");
  const [current, setCurrent] = useState(0);
  const [total, setTotal] = useState(0);
  const [remaing, setRemaing] = useState(0);

  useEffect(() => {
    const fetchDates = async () => {
      try {
        const res = await api.post("/dischargeDate");
        setEnlistmentDate(res.data.enlistmentDate);
        setDischargeDate(res.data.dischargeDate);
        setPercent(res.data.persent);
        setCurrent(res.data.currentDays);
        setTotal(res.data.totalDays);
        setRemaing(res.data.remaingDays);
      } catch (err) {
        toast.error("날짜를 불러오는 데 실패했습니다.");
      }
    };

    fetchDates();
  }, []);

  return (
    <>
      <ToastContainer />
      <div className="justify-content-center align-items-center min-vh-70">
        <div className="m-auto" style={{ maxWidth: "1000px", width: "100%" }}>
          <div className="row">
            <h2>D-{remaing}</h2>
          </div>
          <div className="row">
            <div className="col-md-6 pb-2">
              <label className="form-label">전체 복무일: {total}</label>
            </div>
            <div className="col-md-6 pb-2">
              <label className="form-label">현재 복무일: {current}</label>
            </div>
          </div>
          <div className="row">
            <div className="col-md-6 pb-2">
              <label className="form-label">입대일: {enlistmentDate}</label>
            </div>
            <div className="col-md-6 pb-2">
              <label className="form-label">전역일: {dischargeDate}</label>
            </div>
          </div>
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
