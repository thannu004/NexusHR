import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Leave() {

    const role = localStorage.getItem("role");

    const [leaves, setLeaves] = useState([]);
    const [employees, setEmployees] = useState([]);

    const [leave, setLeave] = useState({
        employeeId: "",
        leaveType: "",
        startDate: "",
        endDate: "",
        reason: ""
    });

    useEffect(() => {

        loadLeaves();
        loadEmployees();

    }, []);

    async function loadLeaves() {

        try {

            const response = await api.get("/leaves");

            setLeaves(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    async function loadEmployees() {

        try {

            const response = await api.get("/employees");

            setEmployees(response.data);

        } catch (e) {}

    }

    async function applyLeave() {

        try {

            await api.post("/leaves", leave);

            alert("Leave Applied");

            loadLeaves();

        } catch (e) {

            alert("Failed");

        }

    }

    async function approve(id) {

        await api.put(`/leaves/approve/${id}`);

        loadLeaves();

    }

    async function reject(id) {

        await api.put(`/leaves/reject/${id}`);

        loadLeaves();

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>

                    {role === "EMPLOYEE" ? "My Leaves" : "Leave Approval"}

                </h2>

                {

                    role === "EMPLOYEE" &&

                    <button
                        className="btn btn-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#leaveModal"
                    >

                        Apply Leave

                    </button>

                }

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Employee</th>

                        <th>Type</th>

                        <th>From</th>

                        <th>To</th>

                        <th>Status</th>

                        {

                            role !== "EMPLOYEE" &&

                            <th>Action</th>

                        }

                    </tr>

                </thead>

                <tbody>

                    {

                        leaves.length === 0 ?

                        <tr>

                            <td
                                colSpan="6"
                                className="text-center"
                            >

                                No Leave Requests

                            </td>

                        </tr>

                        :

                        leaves.map(l=>(

                            <tr key={l.id}>

                                <td>

                                    {l.employee?.firstName} {l.employee?.lastName}

                                </td>

                                <td>{l.leaveType}</td>

                                <td>{l.startDate}</td>

                                <td>{l.endDate}</td>

                                <td>{l.status}</td>

                                {

                                    role !== "EMPLOYEE" &&

                                    <td>

                                        {

                                            l.status==="PENDING" &&

                                            <>

                                                <button
                                                    className="btn btn-success btn-sm me-2"
                                                    onClick={()=>approve(l.id)}
                                                >

                                                    Approve

                                                </button>

                                                <button
                                                    className="btn btn-danger btn-sm"
                                                    onClick={()=>reject(l.id)}
                                                >

                                                    Reject

                                                </button>

                                            </>

                                        }

                                    </td>

                                }

                            </tr>

                        ))

                    }

                </tbody>

            </table>

            <div
                className="modal fade"
                id="leaveModal"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h4>Apply Leave</h4>

                            <button
                                className="btn-close"
                                data-bs-dismiss="modal"
                            />

                        </div>

                        <div className="modal-body">

                            <select
                                className="form-select mb-3"
                                onChange={(e)=>setLeave({...leave,employeeId:e.target.value})}
                            >

                                <option>Select Employee</option>

                                {

                                    employees.map(emp=>(

                                        <option
                                            key={emp.id}
                                            value={emp.id}
                                        >

                                            {emp.firstName} {emp.lastName}

                                        </option>

                                    ))

                                }

                            </select>

                            <input
                                className="form-control mb-3"
                                placeholder="Leave Type"
                                onChange={(e)=>setLeave({...leave,leaveType:e.target.value})}
                            />

                            <input
                                type="date"
                                className="form-control mb-3"
                                onChange={(e)=>setLeave({...leave,startDate:e.target.value})}
                            />

                            <input
                                type="date"
                                className="form-control mb-3"
                                onChange={(e)=>setLeave({...leave,endDate:e.target.value})}
                            />

                            <textarea
                                className="form-control mb-3"
                                rows="4"
                                placeholder="Reason"
                                onChange={(e)=>setLeave({...leave,reason:e.target.value})}
                            />

                            <button
                                className="btn btn-primary w-100"
                                onClick={applyLeave}
                            >

                                Submit

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}