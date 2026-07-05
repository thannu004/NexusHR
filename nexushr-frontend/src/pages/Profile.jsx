import Layout from "../components/Layout";
import { FaUserCircle, FaEnvelope, FaUserTag } from "react-icons/fa";

export default function Profile() {

    const name = localStorage.getItem("name");
    const email = localStorage.getItem("email");
    const role = localStorage.getItem("role");

    return (

        <Layout>

            <div className="container">

                <div className="card shadow border-0 rounded-4">

                    <div className="card-body p-5">

                        <div className="text-center">

                            <FaUserCircle
                                size={110}
                                color="#2563EB"
                            />

                            <h2 className="mt-3">

                                {name}

                            </h2>

                            <span className="badge bg-primary">

                                {role}

                            </span>

                        </div>

                        <hr />

                        <div className="row mt-4">

                            <div className="col-md-6 mb-4">

                                <h6>

                                    <FaEnvelope className="me-2"/>

                                    Email

                                </h6>

                                <p>{email}</p>

                            </div>

                            <div className="col-md-6 mb-4">

                                <h6>

                                    <FaUserTag className="me-2"/>

                                    Role

                                </h6>

                                <p>{role}</p>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}