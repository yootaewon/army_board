import LeaveType from "../components/LeaveCount";
import { toast, ToastContainer } from "react-toastify";

const Leave = () => {
  return (
    <>
      <ToastContainer />
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <br></br>
      <LeaveType toast={toast} />
    </>
  );
};

export default Leave;
