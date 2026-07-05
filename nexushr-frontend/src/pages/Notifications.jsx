import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Notifications() {

    const [notifications, setNotifications] = useState([]);
    const role = localStorage.getItem("role");

    useEffect(() => {
        loadNotifications();
    }, []);

    async function loadNotifications() {

        try {

            const response = await api.get("/personal-notifications");

            setNotifications(response.data);

        } catch (error) {

            console.log(error);

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Notifications</h2>

                <span className="badge bg-primary">
                    {role}
                </span>

            </div>

            {

                notifications.length === 0 ?

                    (

                        <div className="card shadow border-0">

                            <div className="card-body text-center p-5">

                                <h4>No Notifications Yet</h4>

                                <p className="text-muted mb-0">

                                    You're all caught up.

                                </p>

                            </div>

                        </div>

                    )

                    :

                    notifications.map(notification => (

                        <div
                            key={notification.id}
                            className="card shadow border-0 mb-3"
                        >

                            <div className="card-body">

                                <div className="d-flex justify-content-between">

                                    <h5>

                                        {notification.title}

                                    </h5>

                                    {

                                        notification.read ?

                                            <span className="badge bg-success">

                                                Read

                                            </span>

                                            :

                                            <span className="badge bg-danger">

                                                New

                                            </span>

                                    }

                                </div>

                                <p className="mt-2">

                                    {notification.message}

                                </p>

                                <small className="text-muted">

                                    {notification.createdAt}

                                </small>

                            </div>

                        </div>

                    ))

            }

        </Layout>

    );

}