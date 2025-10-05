import { useEffect, useState } from "react";
import api from "../api/AxiosInstance";
import { toast } from "react-toastify";

const LeaveModal = ({ modalBackground, modalToggle }) => {
  const [leaveList, setLeaveList] = useState([]);
  const [selectedIds, setSelectedIds] = useState([]);

  useEffect(() => {
    fetchLeaveList();
  }, []);

  const fetchLeaveList = async () => {
    try {
      const res = await api.post("/leave-type/select/history");
      setLeaveList(res.data);
    } catch {
      toast.error("휴가 리스트를 불러오는 데 실패했습니다.");
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
    try {
      await api.post("/leave-type/delete", selectedIds);
      toast.success("선택한 휴가가 삭제되었습니다.");
      fetchLeaveList();
    } catch {
      toast.error("휴가를 삭제하는 데 실패했습니다.");
    }
  };

  return (
    <div
      className="modal fade show d-block"
      id="staticBackdrop"
      tabIndex={-1}
      aria-labelledby="staticBackdropLabel"
      aria-modal="true"
      role="dialog"
      ref={modalBackground}
      style={{ backgroundColor: "rgba(0,0,0,0.4)" }}
      onClick={handleBackgroundClick}
    >
      <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
        <div className="modal-content">
          <header className="modal-header">
            <h5 className="modal-title" id="staticBackdropLabel">
              등록 내역
            </h5>
            <button
              type="button"
              className="btn-close"
              aria-label="Close"
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
            <button type="button" className="btn btn-primary">
              수정
            </button>
            <button
              type="button"
              className="btn btn-danger"
              onClick={deleteLeave}
            >
              삭제
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

export default LeaveModal;
