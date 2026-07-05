import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Employees() {

    const emptyEmployee = {
        employeeId: "",
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        gender: "",
        designation: "",
        salary: "",
        joiningDate: "",
        managerName: "",
        status: "ACTIVE",
        department: {
            id: 1
        }
    };

    const [employees, setEmployees] = useState([]);
    const [employee, setEmployee] = useState(emptyEmployee);

    useEffect(() => {
        loadEmployees();
    }, []);

    async function loadEmployees() {

        try {

            const response = await api.get("/employees");

            setEmployees(response.data);

        } catch (error) {

            console.log(error);

        }

    }

    async function addEmployee() {

        try {

            await api.post("/employees", employee);

            alert("Employee Added Successfully");

            setEmployee(emptyEmployee);

            loadEmployees();

            document.getElementById("closeModal").click();

        } catch (error) {

            console.log(error);

            alert("Unable to add employee");

        }

    }

    async function deleteEmployee(id) {

        if (!window.confirm("Delete this employee?")) return;

        try {

            await api.delete(`/employees/${id}`);

            loadEmployees();

        } catch (error) {

            alert("Delete Failed");

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Employees</h2>

                <button
                    className="btn btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#employeeModal"
                >
                    + Add Employee
                </button>

            </div>

            <table className="table table-hover table-bordered shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Employee ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th>Designation</th>
                        <th>Salary</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        employees.map(emp => (

                            <tr key={emp.id}>

                                <td>{emp.employeeId}</td>

                                <td>{emp.firstName} {emp.lastName}</td>

                                <td>{emp.email}</td>

                                <td>{emp.department?.departmentName}</td>

                                <td>{emp.designation}</td>

                                <td>₹ {emp.salary}</td>

                                <td>

                                    <button
                                        className="btn btn-warning btn-sm me-2"
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => deleteEmployee(emp.id)}
                                    >
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

            <div
                className="modal fade"
                id="employeeModal"
            >

                <div className="modal-dialog modal-lg">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h4>Add Employee</h4>

                            <button
                                className="btn-close"
                                id="closeModal"
                                data-bs-dismiss="modal"
                            >
                            </button>

                        </div>

                        <div className="modal-body">

                            <div className="row">

                                <div className="col-md-6">

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Employee ID"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                employeeId: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="First Name"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                firstName: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Last Name"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                lastName: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Email"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                email: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Phone"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                phone: e.target.value
                                            })
                                        }
                                    />

                                </div>

                                <div className="col-md-6">

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Gender"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                gender: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Designation"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                designation: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        type="number"
                                        className="form-control mb-3"
                                        placeholder="Salary"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                salary: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        type="date"
                                        className="form-control mb-3"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                joiningDate: e.target.value
                                            })
                                        }
                                    />

                                    <input
                                        className="form-control mb-3"
                                        placeholder="Manager Name"
                                        onChange={(e) =>
                                            setEmployee({
                                                ...employee,
                                                managerName: e.target.value
                                            })
                                        }
                                    />

                                </div>

                            </div>

                            <button
                                className="btn btn-success w-100"
                                onClick={addEmployee}
                            >
                                Save Employee
                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}