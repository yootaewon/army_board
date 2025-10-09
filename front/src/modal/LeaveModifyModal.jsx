import { useState, useEffect } from "react";
import api from "../api/AxiosInstance";
import { toast } from "react-toastify";

const LeaveModifyModal = ({
  modalBackground,
  modalToggle,
  onUpdate,
  initialData,
}) => {
  const [form, setForm] = useState({
    leaveId: "",
    leaveType: "연가",
    leaveDays: 0,
    reason: "",
  });

  useEffect(() => {
    setForm(initialData);
  }, [initialData]);

  const handleBackgroundClick = (e) => {
    if (e.target === modalBackground?.current) modalToggle();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const modifyLeave = async () => {
    if (!form.reason) {
      toast.info("사유를 입력하세요.");
      return;
    } else if (form.leaveDays <= 0) {
      toast.info("최소 1일 이상 입력하세요.");
      return;
    }

    try {
      const res = await api.post("/leave-type/modify", form);
      toast.success(res.data);
      modalToggle();
      onUpdate();
    } catch(err) {
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
      <div className="modal-dialog modal-dialog-centered modal-lg">
        <div className="modal-content">
          <header className="modal-header">
            <h5 className="modal-title">휴가 수정</h5>
          </header>
          <section className="modal-body">
            <div className="mb-3">
              <label className="form-label">유형</label>
              <select className="form-select" name="leaveType" disabled>
                <option value="연가">{form.leaveType}</option>
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
              onClick={modifyLeave}
            >
              수정 완료
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

export default LeaveModifyModal;
