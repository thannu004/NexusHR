import { Routes, Route } from "react-router-dom";

import Landing from "./pages/Landing";
import Login from "./pages/Login";
import Register from "./pages/Register";
import ForgotPassword from "./pages/ForgotPassword";
import Dashboard from "./pages/Dashboard";
import Employees from "./pages/Employees";
import Attendance from "./pages/Attendance";
import Leave from "./pages/Leave";
import Payroll from "./pages/Payroll";
import Performance from "./pages/Performance";
import Notifications from "./pages/Notifications";
import NexusBot from "./pages/NexusBot";
import Profile from "./pages/Profile";
import Users from "./pages/Users";
import Announcements from "./pages/Announcements";

export default function App() {

    return (

        <Routes>

            <Route path="/" element={<Landing />} />

            <Route path="/login" element={<Login />} />

            <Route path="/register" element={<Register />} />

            <Route path="/forgot-password" element={<ForgotPassword />} />

            <Route path="/dashboard" element={<Dashboard />} />

            <Route path="/employees" element={<Employees />} />

            <Route path="/attendance" element={<Attendance />} />

            <Route path="/leave" element={<Leave />} />
            
            <Route path="/payroll" element={<Payroll />} />

            <Route path="/performance" element={<Performance />} />
            
            <Route path="/notifications" element={<Notifications />} />
            
            <Route path="/nexusbot" element={<NexusBot />} />

            <Route path="/profile" element={<Profile />} />

            <Route path="/users" element={<Users />} />

            <Route path="/announcements" element={<Announcements />} />

        </Routes>

    );

}