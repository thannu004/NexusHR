import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

export default function Layout({ children }) {

    return (

        <div className="d-flex">

            <Sidebar />

            <div
                className="flex-grow-1"
                style={{
                    minHeight: "100vh",
                    background: "#f5f7fb"
                }}
            >

                <Navbar />

                <div className="p-4">

                    {children}

                </div>

            </div>

        </div>

    );

}