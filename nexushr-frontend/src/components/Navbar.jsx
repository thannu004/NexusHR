import { FaBell, FaUserCircle } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export default function Navbar() {

    const navigate = useNavigate();

    function logout() {

        localStorage.clear();

        navigate("/login");

    }

    return (

        <nav
            className="navbar bg-white shadow-sm px-4"
            style={{ height: "70px" }}
        >

            <h4>NexusHR Dashboard</h4>

            <div className="d-flex align-items-center">

                <FaBell
                    size={20}
                    className="me-4"
                    style={{ cursor: "pointer" }}
                    onClick={() => navigate("/notifications")}
                />

                <FaUserCircle
                    size={30}
                    className="me-4"
                    style={{ cursor: "pointer" }}
                    onClick={() => navigate("/profile")}
                />

                <button
                    className="btn btn-danger btn-sm"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

        </nav>

    );

}