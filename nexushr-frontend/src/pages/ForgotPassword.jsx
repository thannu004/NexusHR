import { useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";

export default function ForgotPassword() {

    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    async function sendResetLink() {

        try {

            const response = await api.post("/auth/forgot-password", {

                email

            });

            setMessage(response.data);

        }

        catch {

            setMessage("Email not found.");

        }

    }

    return (

        <div
            className="container d-flex justify-content-center align-items-center"
            style={{ minHeight: "100vh" }}
        >

            <div
                className="card shadow p-4"
                style={{ width: "420px" }}
            >

                <h3 className="text-center mb-4">

                    Forgot Password

                </h3>

                <input
                    className="form-control mb-3"
                    placeholder="Enter Email"
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}
                />

                <button
                    className="btn btn-primary w-100"
                    onClick={sendResetLink}
                >

                    Send Reset Link

                </button>

                {

                    message &&

                    <div className="alert alert-success mt-3">

                        {message}

                    </div>

                }

                <div className="text-center mt-3">

                    <Link to="/login">

                        Back to Login

                    </Link>

                </div>

            </div>

        </div>

    );

}