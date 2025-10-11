import { useState } from "react";
import api from "../api/AxiosInstance";
import { toast } from "react-toastify";

const LeaveRegisterModal = ({ modalBackground, modalToggle, onUpdate }) => {
  const [form, setForm] = useState({
    leaveType: "포상",
    leaveDays: 0,
    reason: "",
  });

  const handleBackgroundClick = (e) => {
    if (e.target === modalBackground?.current) modalToggle();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const registerLeave = async () => {
    if (!form.reason) {
      toast.info("사유를 입력하세요.");
      return;
    } else if (form.leaveDays == 0) {
      toast.info("최소 1일 이상 입력하세요.");
      return;
    }

    try {
      const res = await api.post("/leave-type/register", form);
      toast.success(res.data);
      modalToggle();
      onUpdate();
    } catch (err) {
      toast.error(err.response.data);
    }
  };

  return (
    <div
      className="modal fade show d-block"
      tabIndex={-1}
      role="dialog"
      ref={modalBackground}
      style={{ backgroundColor: "rgba(0,0,0,0.4)" }}
      onClick={handleBackgroundClick}
    >
      <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div className="modal-content">
          <h5 className="modal-title">등록</h5>
          <section className="modal-body">
            <div className="mb-3">
              <label className="form-label">유형</label>
              <select
                className="form-select"
                name="leaveType"
                value={form.leaveType}
                onChange={handleChange}
              >
                <option value="포상">포상</option>
                <option value="위로">위로</option>
              </select>
            </div>
            <div className="mb-3">
              <label className="form-label">일 수</label>
              <input
                type="number"
                name="leaveDays"
                className="form-control"
                value={form.leaveDays}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label className="form-label">사유</label>
              <input
                type="text"
                name="reason"
                className="form-control"
                value={form.reason}
                onChange={handleChange}
              />
            </div>
          </section>
          <footer className="modal-footer">
            <button
              type="button"
              className="btn btn-primary"
              onClick={registerLeave}
            >
              등록
            </button>
            <button
              type="button"
              className="btn btn-secondary"
              onClick={modalToggle}
            >
              닫기
            </button>
          </footer>
        </div>
      </div>
    </div>
  );
};

export default LeaveRegisterModal;
