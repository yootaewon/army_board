import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { logout } from "../redux/userSlice";

const Header = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const { isLoggedIn } = useSelector((state) => state.user);

  const signOut = () => {
    localStorage.removeItem("accessToken");
    dispatch(logout());
    navigate("/signIn");
  };

  const goToSignIn = () => {
    navigate("/signIn");
  };

  const goToLeave = () => {
    navigate("/leave");
  };

  const goToBoard = () => {
    navigate("/board");
  };

  const goToMenu = () => {
    navigate("/menu");
  };

  return (
    <header>
      <nav className="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div className="container-fluid">
          <a className="navbar-brand" href="/">
            군 제대 플래너
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarCollapse"
            aria-controls="navbarCollapse"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarCollapse">
            <ul className="navbar-nav me-auto mb-2 mb-md-0">
              <li className="nav-item">
                <button className="nav-link active" onClick={goToLeave}>
                  휴가 계획
                </button>
              </li>
              <li className="nav-item">
                <button className="nav-link active" onClick={goToBoard}>
                  군 게시판
                </button>
              </li>
              <li className="nav-item">
                <button className="nav-link active" onClick={goToMenu}>
                  오늘의 메뉴
                </button>
              </li>
            </ul>
            {!isLoggedIn ? (
              <button
                className="btn btn-outline-success"
                type="submit"
                onClick={goToSignIn}
              >
                로그인
              </button>
            ) : (
              <button
                className="btn btn-outline-success"
                type="submit"
                onClick={signOut}
              >
                로그아웃
              </button>
            )}
          </div>
        </div>
      </nav>
    </header>
  );
};

export default Header;
