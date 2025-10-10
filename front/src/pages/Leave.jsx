import { useState, useEffect } from "react";
import LeaveType from "../components/LeaveCount";
import Table from "../components/Table";
import { toast, ToastContainer } from "react-toastify";
import api from "../api/AxiosInstance";
const Leave = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const fetchDates = async () => {
      try {
        const res = await api.post("/leave-request/select/history");
        setItems(res.data);
      } catch (err) {
        toast.error(err.reponse.data);
      }
    };
    fetchDates();
  }, []);

  const headers = [
    { text: "제목", value: "title" },
    { text: "휴가 유형", value: "leaveType" },
    { text: "시작일", value: "startDate" },
    { text: "종료일", value: "endDate" },
    { text: "기간", value: "period" },
    { text: "행선지", value: "destination" },
    { text: "사유", value: "reason" },
    { text: "등록일", value: "regDateTime" },
  ];

  return (
    <>
      <ToastContainer />
      <div className="container d-flex flex-column justify-content-center align-items-center min-vh-70">
        <LeaveType toast={toast} />
        <Table headers={headers} items={items} />
      </div>
    </>
  );
};

export default Leave;
