import { Link } from "react-router-dom";
import "../styles/Landing.css";
import Logo from "../components/Logo";

export default function Landing() {
  return (
    <div className="hero">

      <div className="left">

        <div className="mb-4">

            <Logo />

        </div>

        <h2 className="subtitle">
          Human Resource Management System
        </h2>

        <p className="desc">
          Securely manage your workforce through one centralized platform.
        </p>

        <div className="btns">

          <Link
            to="/login"
            className="btn btn-main"
          >
            Login
          </Link>

          <Link
            to="/register"
            className="btn btn-outline-primary btn-lg"
          >
            Register
          </Link>

        </div>

      </div>

      <div className="right">

        <div
          style={{
            width: "420px",
            height: "420px",
            borderRadius: "50%",
            background: "rgba(255,255,255,0.08)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            fontSize: "120px"
          }}
        >
          👩‍💼
        </div>

      </div>

    </div>
  );
}