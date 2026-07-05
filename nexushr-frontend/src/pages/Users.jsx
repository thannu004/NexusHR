import { useEffect, useState } from "react";
import Layout from "../components/Layout";
import api from "../services/api";

export default function Users() {

    const [users, setUsers] = useState([]);

    useEffect(() => {

        loadUsers();

    }, []);

    async function loadUsers() {

        try {

            const response = await api.get("/users");

            setUsers(response.data);

        }

        catch(error){

            console.log(error);

        }

    }

    return(

        <Layout>

            <h2 className="mb-4">

                System Users

            </h2>

            <table className="table table-hover shadow">

                <thead className="table-dark">

                    <tr>

                        <th>Name</th>

                        <th>Email</th>

                        <th>Role</th>

                        <th>Department</th>

                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        users.map(user=>(

                            <tr key={user.id}>

                                <td>{user.name}</td>

                                <td>{user.email}</td>

                                <td>{user.role}</td>

                                <td>{user.department}</td>

                                <td>

                                    {

                                        user.active

                                        ?

                                        "Active"

                                        :

                                        "Inactive"

                                    }

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </Layout>

    );

}