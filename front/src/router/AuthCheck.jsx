import { useEffect } from "react";
import { Navigate, Outlet } from "react-router-dom";

const AuthCheck = () => {
    const token = localStorage.getItem("accessToken");

    useEffect(() => {
        if (!token) {
            alert("로그인 후 이용 가능합니다.");
        }
    }, []);

    return token ? <Outlet /> : <Navigate to="/" />;
};

export default AuthCheck;