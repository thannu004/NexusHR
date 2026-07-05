import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Attendance() {

    const role = localStorage.getItem("role");

    const [attendance, setAttendance] = useState([]);

    useEffect(() => {

        loadAttendance();

    }, []);

    async function loadAttendance() {

        try {

            const response = await api.get("/attendance");

            setAttendance(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    async function checkIn() {

        try {

            await api.post("/attendance/checkin");

            alert("Checked In Successfully");

            loadAttendance();

        } catch (e) {

            alert("Check In Failed");

        }

    }

    async function checkOut() {

        try {

            await api.put("/attendance/checkout");

            alert("Checked Out Successfully");

            loadAttendance();

        } catch (e) {

            alert("Check Out Failed");

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Attendance</h2>

                {role !== "ADMIN" && (

                    <div>

                        <button
                            className="btn btn-success me-2"
                            onClick={checkIn}
                        >
                            Check In
                        </button>

                        <button
                            className="btn btn-danger"
                            onClick={checkOut}
                        >
                            Check Out
                        </button>

                    </div>

                )}

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Employee</th>
                        <th>Date</th>
                        <th>Check In</th>
                        <th>Check Out</th>
                        <th>Working Hours</th>
                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {attendance.length === 0 ? (

                        <tr>

                            <td
                                colSpan="6"
                                className="text-center"
                            >
                                No Attendance Records Yet
                            </td>

                        </tr>

                    ) : (

                        attendance.map(a => (

                            <tr key={a.id}>

                                <td>

                                    {a.employee?.firstName} {a.employee?.lastName}

                                </td>

                                <td>{a.attendanceDate}</td>

                                <td>{a.checkIn || "-"}</td>

                                <td>{a.checkOut || "-"}</td>

                                <td>{a.workingHours ?? "-"}</td>

                                <td>{a.attendanceType}</td>

                            </tr>

                        ))

                    )}

                </tbody>

            </table>

        </Layout>

    );

}