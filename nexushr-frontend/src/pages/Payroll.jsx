import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Payroll() {

    const [payrolls, setPayrolls] = useState([]);

    useEffect(() => {
        loadPayrolls();
    }, []);

    async function loadPayrolls() {

        try {

            const response = await api.get("/payroll");

            setPayrolls(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>Payroll</h2>

                <button className="btn btn-success">

                    Generate Payroll

                </button>

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Employee</th>
                        <th>Month</th>
                        <th>Year</th>
                        <th>Basic Salary</th>
                        <th>Net Salary</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        payrolls.map(p => (

                            <tr key={p.id}>

                                <td>

                                    {p.employee?.firstName} {p.employee?.lastName}

                                </td>

                                <td>{p.month}</td>

                                <td>{p.year}</td>

                                <td>₹ {p.basicSalary}</td>

                                <td>₹ {p.netSalary}</td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </Layout>

    );

}