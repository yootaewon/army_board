import { useDispatch } from "react-redux";
import axios from "axios";
import { login, logout } from "../redux/userSlice";
import { getAccessToken } from "../redux/userSlice";
const api = axios.create({
  baseURL: "/api",
  withCredentials: true,
});

api.interceptors.request.use(
  (config) => {
    const token = getAccessToken();
    
    if (token) {
      config.headers.Authorization = token;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    if (error.response?.status === 403 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const res = await axios.post(
          "/api/reissue",
          {},
          { withCredentials: true }
        );
                const newToken = res.data.accessToken;
        const dispatch = useDispatch();
        dispatch(login(newToken));
        originalRequest.headers.Authorization = `Bearer ${newToken}`;
        return api(originalRequest);
      } catch (refreshError) {
        const dispatch = useDispatch();
        dispatch(logout());
        localStorage.removeItem("accessToken");
        window.location.href = "/signIn";
        return Promise.reject(refreshError);
      }
    }
    return Promise.reject(error);
  }
);

export default api;
