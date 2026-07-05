import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Announcements() {

    const role = localStorage.getItem("role");

    const [announcements, setAnnouncements] = useState([]);

    const [announcement, setAnnouncement] = useState({
        title: "",
        message: "",
        postedBy: localStorage.getItem("name")
    });

    useEffect(() => {

        loadAnnouncements();

    }, []);

    async function loadAnnouncements() {

        try {

            const response = await api.get("/announcements");

            setAnnouncements(response.data);

        } catch (e) {

            console.log(e);

        }

    }

    async function addAnnouncement() {

        try {

            await api.post("/announcements", announcement);

            loadAnnouncements();

            setAnnouncement({
                title: "",
                message: "",
                postedBy: localStorage.getItem("name")
            });

        } catch (e) {

            alert("Unable to post announcement");

        }

    }

    return (

        <Layout>

            <div className="d-flex justify-content-between mb-4">

                <h2>Announcements</h2>

                {(role === "ADMIN" ||
                    role === "HR" ||
                    role === "MANAGER") && (

                        <button
                            className="btn btn-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#announcementModal"
                        >

                            + New Announcement

                        </button>

                    )}

            </div>

            {

                announcements.length === 0 ?

                    (

                        <div className="alert alert-secondary">

                            No announcements yet.

                        </div>

                    )

                    :

                    announcements.map(a => (

                        <div
                            className="card shadow mb-3"
                            key={a.id}
                        >

                            <div className="card-body">

                                <h5>{a.title}</h5>

                                <p>{a.message}</p>

                                <small>

                                    Posted by {a.postedBy} | {a.postedDate}

                                </small>

                            </div>

                        </div>

                    ))

            }

            <div
                className="modal fade"
                id="announcementModal"
            >

                <div className="modal-dialog">

                    <div className="modal-content">

                        <div className="modal-header">

                            <h4>New Announcement</h4>

                            <button
                                className="btn-close"
                                data-bs-dismiss="modal"
                            />

                        </div>

                        <div className="modal-body">

                            <input
                                className="form-control mb-3"
                                placeholder="Title"
                                onChange={(e) =>
                                    setAnnouncement({
                                        ...announcement,
                                        title: e.target.value
                                    })
                                }
                            />

                            <textarea
                                rows="5"
                                className="form-control mb-3"
                                placeholder="Announcement"
                                onChange={(e) =>
                                    setAnnouncement({
                                        ...announcement,
                                        message: e.target.value
                                    })
                                }
                            />

                            <button
                                className="btn btn-success w-100"
                                onClick={addAnnouncement}
                            >

                                Publish

                            </button>

                        </div>

                    </div>

                </div>

            </div>

        </Layout>

    );

}