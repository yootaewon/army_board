import { useEffect, useRef, useState } from "react";
import api from "../api/AxiosInstance";
import "../style/LeaveType.css";
import LeaveHistoryModal from "../modal/LeaveManageModal";
import LeaveRegisterModal from "../modal/LeaveRegisterModal";
import { toast } from "react-toastify";

const LeaveCount = () => {
  const [annual, setAnnual] = useState(0);
  const [reward, setReward] = useState(0);
  const [comfort, setComfort] = useState(0);
  const [manageModalOpen, setManageModalOpen] = useState(false);
  const [registerModalOpen, setRegisterModalOpen] = useState(false);

  const manageModalRef = useRef(null);
  const registerModalRef = useRef(null);

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
      toast.error(err.response.data);
    }
  };

  const manageModalToggle = () => setManageModalOpen(!manageModalOpen);
  const registerModalToggle = () => setRegisterModalOpen(!registerModalOpen);

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

        <div className="d-flex justify-content-end mt-4 gap-2">
          <button className="btn btn-primary" onClick={registerModalToggle}>
            등록
          </button>
          <button className="btn btn-primary" onClick={manageModalToggle}>
            관리
          </button>
        </div>
        {manageModalOpen && (
          <LeaveHistoryModal
            modalBackground={manageModalRef}
            modalToggle={manageModalToggle}
            onUpdate={getLeaveTypeCount}
          />
        )}
        {registerModalOpen && (
          <LeaveRegisterModal
            modalBackground={registerModalRef}
            modalToggle={registerModalToggle}
            onUpdate={getLeaveTypeCount}
          />
        )}
      </div>
    </div>
  );
};

export default LeaveCount;
