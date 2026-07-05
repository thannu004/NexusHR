import { useState } from "react";
import Layout from "../components/Layout";

export default function NexusBot() {

    const [question, setQuestion] = useState("");

    return (

        <Layout>

            <h2 className="mb-4">

                Nexus AI Assistant

            </h2>

            <textarea
                className="form-control"
                rows="6"
                placeholder="Ask anything..."
                value={question}
                onChange={(e)=>setQuestion(e.target.value)}
            />

            <button
                className="btn btn-primary mt-3"
            >

                Send

            </button>

        </Layout>

    );

}