import { useState, useEffect } from "react";
import { toast, ToastContainer } from "react-toastify";
import api from "../api/AxiosInstance";

const DischargeDate = () => {
  const [enlistmentDate, setEnlistmentDate] = useState("");
  const [dischargeDate, setDischargeDate] = useState("");

  useEffect(() => {
    const fetchDates = async () => {
      try {
        const res = await api.post("/dischargeDate");

        setEnlistmentDate(res.data.enlistmentDate);
        setDischargeDate(res.data.dischargeDate);
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
    </>
  );
};

export default DischargeDate;
