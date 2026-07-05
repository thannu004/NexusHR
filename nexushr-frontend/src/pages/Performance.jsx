import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Performance() {

    const role = localStorage.getItem("role");

    const [reviews, setReviews] = useState([]);
    const [employees, setEmployees] = useState([]);

    const [review, setReview] = useState({
        employeeId: "",
        reviewPeriod: "",
        rating: "",
        remarks: ""
    });

    useEffect(() => {

        loadReviews();
        loadEmployees();

    }, []);

    async function loadReviews() {

        try {

            const response = await api.get("/performance");

            setReviews(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    async function loadEmployees() {

        try {

            const response = await api.get("/employees");

            setEmployees(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    async function submitReview() {

        try {

            await api.post("/performance", review);

            alert("Review Submitted");

            loadReviews();

            setReview({
                employeeId: "",
                reviewPeriod: "",
                rating: "",
                remarks: ""
            });

        } catch (e) {

            alert("Unable to submit review");

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Performance Reviews</h2>

                {

                    (role === "ADMIN" ||
                        role === "HR" ||
                        role === "MANAGER") &&

                    <button
                        className="btn btn-primary"
                        data-bs-toggle="modal"
                        data-bs-target="#reviewModal"
                    >

                        Give Review

                    </button>

                }

            </div>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Employee</th>
                        <th>Review Period</th>
                        <th>Rating</th>
                        <th>Remarks</th>
                        <th>Date</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        reviews.length === 0 ?

                            <tr>

                                <td
                                    colSpan="5"
                                    className="text-center"
                                >

                                    No Reviews Yet

                                </td>

                            </tr>

                            :

                            reviews.map(r => (

                                <tr key={r.id}>

                                    <td>

                                        {r.employee?.firstName} {r.employee?.lastName}

                                    </td>

                                    <td>{r.reviewPeriod}</td>

                                    <td>{r.rating}</td>

                                    <td>{r.remarks}</td>

                                    <td>{r.reviewDate}</td>

                                </tr>

                            ))

                    }

                </tbody>

            </table>

            <div
                className="modal fade"
                id="reviewModal"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h4>Give Performance Review</h4>

                            <button
                                className="btn-close"
                                data-bs-dismiss="modal"
                            >
                            </button>

                        </div>

                        <div className="modal-body">

                            <select
                                className="form-select mb-3"
                                onChange={(e) =>
                                    setReview({
                                        ...review,
                                        employeeId: e.target.value
                                    })
                                }
                            >

                                <option>

                                    Select Employee

                                </option>

                                {

                                    employees.map(emp => (

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
                                placeholder="Review Period (Q1 2026)"
                                onChange={(e) =>
                                    setReview({
                                        ...review,
                                        reviewPeriod: e.target.value
                                    })
                                }
                            />

                            <select
                                className="form-select mb-3"
                                onChange={(e) =>
                                    setReview({
                                        ...review,
                                        rating: e.target.value
                                    })
                                }
                            >

                                <option>

                                    Rating

                                </option>

                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>

                            </select>

                            <textarea
                                rows="4"
                                className="form-control mb-3"
                                placeholder="Remarks"
                                onChange={(e) =>
                                    setReview({
                                        ...review,
                                        remarks: e.target.value
                                    })
                                }
                            />

                            <button
                                className="btn btn-success w-100"
                                onClick={submitReview}
                            >

                                Submit Review

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}