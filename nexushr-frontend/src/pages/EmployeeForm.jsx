import { useState, useEffect } from "react";
import api from "../services/api";

export default function EmployeeForm({
    employee,
    onClose,
    onSaved
}) {

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

    const [form, setForm] = useState(emptyEmployee);

    useEffect(() => {

        if (employee) {

            setForm(employee);

        }

    }, [employee]);

    async function save() {

        try {

            if (form.id) {

                await api.put(`/employees/${form.id}`, form);

            } else {

                await api.post("/employees", form);

            }

            onSaved();

            onClose();

        } catch (e) {

            alert("Unable to Save Employee");

        }

    }

    return (

        <div>

            <input
                className="form-control mb-2"
                placeholder="Employee ID"
                value={form.employeeId}
                onChange={(e)=>setForm({...form,employeeId:e.target.value})}
            />

            <input
                className="form-control mb-2"
                placeholder="First Name"
                value={form.firstName}
                onChange={(e)=>setForm({...form,firstName:e.target.value})}
            />

            <input
                className="form-control mb-2"
                placeholder="Last Name"
                value={form.lastName}
                onChange={(e)=>setForm({...form,lastName:e.target.value})}
            />

            <input
                className="form-control mb-2"
                placeholder="Email"
                value={form.email}
                onChange={(e)=>setForm({...form,email:e.target.value})}
            />

            <button
                className="btn btn-success w-100"
                onClick={save}
            >
                Save Employee
            </button>

        </div>

    );

}