import {
    FaHome,
    FaUsers,
    FaClipboardCheck,
    FaCalendarAlt,
    FaMoneyBillWave,
    FaChartLine,
    FaBell,
    FaRobot,
    FaUserCircle,
    FaBullhorn,
    FaFilePdf
} from "react-icons/fa";

import { Link } from "react-router-dom";
import Logo from "./Logo";

export default function Sidebar() {

    const role = localStorage.getItem("role");

    return (

        <div
            style={{
                width: "250px",
                minHeight: "100vh",
                background: "#1E3A8A",
                color: "white",
                padding: "25px"
            }}
        >

            <div className="mb-5">
                <Logo />
            </div>

            <Link className="nav-link text-white mb-3" to="/dashboard">
                <FaHome className="me-2" />
                Dashboard
            </Link>

            {(role === "ADMIN" || role === "HR") && (
                <>
                    <Link className="nav-link text-white mb-3" to="/users">
                        <FaUsers className="me-2" />
                        Users
                    </Link>

                    <Link className="nav-link text-white mb-3" to="/employees">
                        <FaUsers className="me-2" />
                        Employees
                    </Link>
                </>
            )}

            {(role === "EMPLOYEE" ||
                role === "HR" ||
                role === "MANAGER") && (

                <Link className="nav-link text-white mb-3" to="/attendance">
                    <FaClipboardCheck className="me-2" />
                    Attendance
                </Link>

            )}

            {role === "EMPLOYEE" ? (

                <Link className="nav-link text-white mb-3" to="/leave">
                    <FaCalendarAlt className="me-2" />
                    Apply Leave
                </Link>

            ) : (

                <Link className="nav-link text-white mb-3" to="/leave">
                    <FaCalendarAlt className="me-2" />
                    Leave Approval
                </Link>

            )}

            {(role === "ADMIN" || role === "HR") && (

                <Link className="nav-link text-white mb-3" to="/payroll">
                    <FaMoneyBillWave className="me-2" />
                    Payroll
                </Link>

            )}

            {(role === "ADMIN" || role === "HR" || role === "MANAGER") && (

                <Link className="nav-link text-white mb-3" to="/performance">
                    <FaChartLine className="me-2" />
                    Performance
                </Link>

            )}

            {(role === "ADMIN" || role === "HR" || role === "MANAGER" || role ==="EMPLOYEE") && (

                <Link className="nav-link text-white mb-3" to="/announcements">
                    <FaBullhorn className="me-2" />
                    Announcements
                </Link>

            )}

            <Link className="nav-link text-white mb-3" to="/notifications">
                <FaBell className="me-2" />
                Notifications
            </Link>

            {role === "EMPLOYEE" && (

                <Link className="nav-link text-white mb-3" to="/nexusbot">
                    <FaRobot className="me-2" />
                    Nexus Bot
                </Link>

            )}

            {role === "ADMIN" && (

                <Link className="nav-link text-white mb-3" to="/documents">
                    <FaFilePdf className="me-2" />
                    Documents
                </Link>

            )}

            <Link className="nav-link text-white" to="/profile">
                <FaUserCircle className="me-2" />
                Profile
            </Link>

        </div>

    );

}