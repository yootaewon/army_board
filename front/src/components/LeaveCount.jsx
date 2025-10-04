import { useEffect, useState, useRef } from "react";
import api from "../api/AxiosInstance";
import "../style/LeaveType.css";
import "../style/Modal.css";

const LeaveType = ({ toast }) => {
  const [annual, setAnnual] = useState(0);
  const [reward, setReward] = useState(0);
  const [comfort, setComfort] = useState(0);
  const [modalOpen, setModalOpen] = useState(false);
  const modalBackground = useRef(null);

  useEffect(() => {
    getLeaveTypeCount();
  }, []);

  const getLeaveTypeCount = async () => {
    try {
      const res = await api.post("/leave-type/select");
      const data = res.data;
      setAnnual(data.annual);
      setReward(data.reward);
      setComfort(data.comfort);
    } catch (err) {
      toast.error("휴가 통계를 불러오는 데 실패했습니다.");
    }
  };

  const modalToggle = () => {
    setModalOpen(!modalOpen);
  };

  return (
    <div className="container d-flex justify-content-center align-items-center min-vh-70">
      <div className="m-auto leave-card">
        <div className="row mb-4">
          <h2 className="text-center">사용 가능 휴가</h2>
        </div>

        <div className="row text-center">
          <div className="col-md-4 pb-2">
            <label className="form-label">연가: {annual}</label>
          </div>
          <div className="col-md-4 pb-2">
            <label className="form-label">포상: {reward}</label>
          </div>
          <div className="col-md-4 pb-2">
            <label className="form-label">위로: {comfort}</label>
          </div>
        </div>

        <div className="d-flex justify-content-end mt-4">
          <button
            className="btn btn-primary"
            type="button"
            onClick={modalToggle}
          >
            관리
          </button>
        </div>

        {modalOpen && (
          <div
            className="modal-container"
            ref={modalBackground}
            onClick={(e) => {
              if (e.target === modalBackground.current) {
                setModalOpen(false);
              }
            }}
          >
            <div className="modal-content">
              <p>모달 구현하기</p>
              <button
                className="modal-close-btn"
                onClick={() => setModalOpen(false)}
              >
                모달 닫기
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default LeaveType;
