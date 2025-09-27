import axios from "axios";
import { getAccessToken, setAccessToken, clearTokens } from "../utils/Auth";

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
        console.log(res.data);
        const newToken = `Bearer ${res.data}`;
        localStorage.setItem("accessToken", newToken);
        setAccessToken(newToken);

        originalRequest.headers.Authorization = newToken;
        return api(originalRequest);
      } catch (refreshError) {
        clearTokens();
        window.location.href = "/signIn";
        return Promise.reject(refreshError);
      }
    }
    return Promise.reject(error);
  }
);

export default api;
