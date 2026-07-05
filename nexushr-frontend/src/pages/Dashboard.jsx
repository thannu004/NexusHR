import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Dashboard() {

    const navigate = useNavigate();

    const [dashboard, setDashboard] = useState({
        totalEmployees: 0,
        totalDepartments: 0,
        totalAttendance: 0,
        payrollGenerated: 0
    });

    useEffect(() => {
        loadDashboard();
    }, []);

    async function loadDashboard() {

        try {

            const response = await api.get("/dashboard");

            setDashboard(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    function Card(title, value, color, page) {

        return (

            <div className="col-md-3">

                <div
                    className="card shadow border-0 text-white"
                    style={{
                        background: color,
                        borderRadius: "18px",
                        cursor: "pointer",
                        transition: ".2s"
                    }}
                    onClick={() => navigate(page)}
                >

                    <div className="card-body">

                        <h6>{title}</h6>

                        <h1>{value}</h1>

                    </div>

                </div>

            </div>

        );

    }

    return (

        <Layout>

            <h2 className="mb-4">

                Welcome back, {localStorage.getItem("name")}

            </h2>

            <div className="row g-4">

                {Card("Employees", dashboard.totalEmployees, "#2563EB", "/employees")}

                {Card("Departments", dashboard.totalDepartments, "#059669", "/users")}

                {Card("Attendance", dashboard.totalAttendance, "#F59E0B", "/attendance")}

                {Card("Payroll", dashboard.payrollGenerated, "#DC2626", "/payroll")}

            </div>

            <div className="row mt-5">

                <div className="col-md-8">

                    <div className="card shadow border-0">

                        <div className="card-body">

                            <h4>Quick Overview</h4>

                            <hr/>

                            <p>Manage employees, attendance, payroll and announcements from one place.</p>

                            <p>Click any dashboard card to open that module.</p>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}