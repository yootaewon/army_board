import { useEffect, useState, useRef } from "react";
import { toast } from "react-toastify";
import api from "../api/AxiosInstance";
import LeaveModifyModal from "./LeaveModifyModal";

const LeaveManageModal = ({ modalBackground, modalToggle, onUpdate }) => {
  const [leaveList, setLeaveList] = useState([]);
  const [selectedIds, setSelectedIds] = useState([]);
  const [leaveData, setLeaveData] = useState(null);
  const modifyModalRef = useRef();

  useEffect(() => {
    leaveHistoryList();
  }, []);

  const leaveHistoryList = async () => {
    try {
      const res = await api.post("/leave-type/select/history");
      setLeaveList(res.data);
    } catch(err) {
      toast.error(err.response.data);
    }
  };

  const handleBackgroundClick = (e) => {
    if (e.target === modalBackground?.current) modalToggle();
  };

  const toggleSelect = (leaveId) => {
    setSelectedIds((prev) =>
      prev.includes(leaveId)
        ? prev.filter((id) => id !== leaveId)
        : [...prev, leaveId]
    );
  };

  const deleteLeave = async () => {
    if (selectedIds.length === 0) {
      toast.info("삭제할 항목을 선택하세요.");
      return;
    }

    const selectedLeaves = leaveList.filter((leave) =>
      selectedIds.includes(leave.leaveId)
    );

    const nonDeletableTypes = ["연가"];

    const hasNonDeletableLeave = selectedLeaves.some((leave) =>
      nonDeletableTypes.includes(leave.leaveType)
    );

    if (hasNonDeletableLeave) {
      toast.info("연가는 삭제할 수 없습니다.");
      return;
    }
    try {
      const res = await api.post("/leave-type/delete", selectedIds);
      toast.success(res.data);
      setSelectedIds([]);
      leaveHistoryList();
      onUpdate();
    } catch (err) {
      toast.error(err.response.data);
    }
  };

  const modifyLeave = () => {
    if (selectedIds.length === 0) {
      toast.info("수정할 항목을 선택하세요.");
      return;
    }
    if (selectedIds.length > 1) {
      toast.info("하나의 항목만 선택하세요.");
      return;
    }
    const target = leaveList.find((item) => item.leaveId === selectedIds[0]);
    if (!target) {
      toast.error("선택한 항목을 찾을 수 없습니다.");
      return;
    }
    if (target.leaveType === "연가") {
      toast.info("연가는 수정할 수 없습니다.");
      return;
    }

    setLeaveData(target);
  };

  const modifyToggle = () => {
    setLeaveData(null);
    leaveHistoryList();
  };

  return (
    <>
      <div
        className="modal fade show d-block"
        tabIndex={-1}
        role="dialog"
        ref={modalBackground}
        style={{ backgroundColor: "rgba(0,0,0,0.4)" }}
        onClick={handleBackgroundClick}
      >
        <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-xl">
          <div className="modal-content">
            <header className="modal-header">
              <h5 className="modal-title">등록 내역</h5>
              <button
                type="button"
                className="btn-close"
                onClick={modalToggle}
              />
            </header>
            <section className="modal-body">
              {leaveList.length === 0 ? (
                <p className="text-center my-3">등록된 내역이 없습니다.</p>
              ) : (
                <table className="table table-hover mb-0">
                  <thead>
                    <tr>
                      <th>선택</th>
                      <th>유형</th>
                      <th>일 수</th>
                      <th>사유</th>
                      <th>등록 시간</th>
                    </tr>
                  </thead>
                  <tbody>
                    {leaveList.map(
                      ({
                        leaveId,
                        leaveType,
                        leaveDays,
                        reason,
                        regDateTime,
                      }) => (
                        <tr key={leaveId}>
                          <td>
                            <input
                              type="checkbox"
                              checked={selectedIds.includes(leaveId)}
                              onChange={() => toggleSelect(leaveId)}
                            />
                          </td>
                          <td>{leaveType}</td>
                          <td>{leaveDays}일</td>
                          <td>{reason}</td>
                          <td>{regDateTime}</td>
                        </tr>
                      )
                    )}
                  </tbody>
                </table>
              )}
            </section>
            <footer className="modal-footer">
              <button
                type="button"
                className="btn btn-danger"
                onClick={deleteLeave}
              >
                삭제
              </button>
              <button
                type="button"
                className="btn btn-primary"
                onClick={modifyLeave}
              >
                수정
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
      {leaveData && (
        <LeaveModifyModal
          modalBackground={modifyModalRef}
          modalToggle={modifyToggle}
          onUpdate={onUpdate}
          initialData={leaveData}
        />
      )}
    </>
  );
};

export default LeaveManageModal;
