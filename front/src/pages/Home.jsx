import axios from "axios";
import React, { useEffect } from "react";

const Home = () => {
  useEffect(() => {
    axios
      .get("/api/hello")
      .then((res) => {
        console.log(res.data); 
      })
      .catch((error) => {
        console.error("API 호출 실패:", error);
      });
  }, []); 
  return (
    <>
      <p>메인페이지입니다.</p>
    </>
  );
};

export default Home;
