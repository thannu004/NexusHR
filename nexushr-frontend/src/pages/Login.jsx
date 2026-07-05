import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import api from "../services/api";

export default function Login() {

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    async function handleLogin() {

        try {

            const response = await api.post("/auth/login", {
                email,
                password
            });

            localStorage.setItem("token", response.data.token);
            localStorage.setItem("role", response.data.role);
            localStorage.setItem("name", response.data.name);
            localStorage.setItem("email", response.data.email);

            navigate("/dashboard");

        } catch (error) {

            alert("Invalid Email or Password");

        }

    }

    return (

        <div
            className="d-flex justify-content-center align-items-center"
            style={{
                minHeight: "100vh",
                background: "#f4f7fc"
            }}
        >

            <div
                className="shadow bg-white p-5"
                style={{
                    width: "420px",
                    borderRadius: "18px"
                }}
            >

                <h2 className="text-center mb-2">
                    NexusHR
                </h2>

                <p className="text-center text-muted mb-4">
                    Welcome Back
                </p>

                <input
                    className="form-control mb-3"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <input
                    type="password"
                    className="form-control mb-4"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button
                    className="btn btn-primary w-100"
                    onClick={handleLogin}
                >
                    Login
                </button>

                <div className="text-center mt-3">

                    <Link to="/forgot-password">
                        Forgot Password?
                    </Link>

                </div>

                <div className="text-center mt-2">

                    <Link to="/register">
                        Don't have an account? Register
                    </Link>

                </div>

            </div>

        </div>

    );

}