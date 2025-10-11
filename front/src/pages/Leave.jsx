import { useState, useEffect } from "react";
import LeaveType from "../components/LeaveCount";
import Table from "../components/Table";
import { toast, ToastContainer } from "react-toastify";
import api from "../api/AxiosInstance";
import Pagination from "../components/Pagenation";

const Leave = () => {
  const [items, setItems] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [size, setSize] = useState(5);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await api.post(
          `/leave-request/select/history?page=${currentPage}&size=${size}`
        );
        setItems(res.data.content);
        setTotalPages(res.data.totalPages);
      } catch (err) {
        toast.error(err.response.data);
      }
    };
    fetchData();
  }, [currentPage, size]);

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
        <Table
          headers={headers}
          items={items}
          setCurrentPage={setCurrentPage}
          currentPage={currentPage}
          totalPages={totalPages}
          size={size}
          setSize={setSize}
        />
      </div>
    </>
  );
};

export default Leave;
