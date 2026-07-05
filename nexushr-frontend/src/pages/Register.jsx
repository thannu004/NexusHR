import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Register() {

    const navigate = useNavigate();

    const [user, setUser] = useState({
        name: "",
        email: "",
        password: "",
        role: "EMPLOYEE",
        department: ""
    });

    async function register() {

        try {

            await api.post("/auth/register", user);

            alert("Registration Successful");

            navigate("/login");

        } catch (e) {

            alert("Registration Failed");

        }

    }

    return (

        <div
            className="container d-flex justify-content-center align-items-center"
            style={{ minHeight: "100vh" }}
        >

            <div
                className="card shadow p-4"
                style={{ width: "450px" }}
            >

                <h2 className="text-center mb-4">

                    Create Account

                </h2>

                <input
                    className="form-control mb-3"
                    placeholder="Full Name"
                    onChange={(e)=>setUser({...user,name:e.target.value})}
                />

                <input
                    className="form-control mb-3"
                    placeholder="Email"
                    onChange={(e)=>setUser({...user,email:e.target.value})}
                />

                <input
                    type="password"
                    className="form-control mb-3"
                    placeholder="Password"
                    onChange={(e)=>setUser({...user,password:e.target.value})}
                />

                <select
                    className="form-select mb-3"
                    onChange={(e)=>setUser({...user,role:e.target.value})}
                >

                    <option value="EMPLOYEE">Employee</option>
                    <option value="MANAGER">Manager</option>
                    <option value="HR">HR</option>
                    <option value="ADMIN">Admin</option>

                </select>

                <input
                    className="form-control mb-4"
                    placeholder="Department"
                    onChange={(e)=>setUser({...user,department:e.target.value})}
                />

                <button
                    className="btn btn-primary w-100"
                    onClick={register}
                >

                    Register

                </button>

                <div className="text-center mt-3">

                    <Link to="/login">

                        Already have an account?

                    </Link>

                </div>

            </div>

        </div>

    );

}