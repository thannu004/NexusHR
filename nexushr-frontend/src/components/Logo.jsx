export default function Logo() {

    return (

        <div
            className="d-flex align-items-center"
        >

            <div
                style={{
                    width: "42px",
                    height: "42px",
                    borderRadius: "12px",
                    background: "#2563EB",
                    color: "white",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    fontWeight: "bold",
                    fontSize: "22px",
                    marginRight: "10px"
                }}
            >
                N
            </div>

            <div>

                <h5
                    className="mb-0"
                    style={{
                        fontWeight: "700"
                    }}
                >
                    NexusHR
                </h5>

                <small className="text-muted">
                    HRMS
                </small>

            </div>

        </div>

    );

}