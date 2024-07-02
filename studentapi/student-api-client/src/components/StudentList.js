// src/components/StudentList.js

import axios from 'axios';
import React, { useEffect, useState } from 'react';

const StudentList = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        const fetchStudents = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/students', {
                    auth: {
                        username: 'eren',
                        password: '230198'
                    }
                });
                setStudents(response.data);
            } catch (error) {
                console.error('Error fetching students:', error);
            }
        };

        fetchStudents();
    }, []);

    return (
        <div>
            <h1>Student List</h1>
            <ul>
                {students.map(student => (
                    <li key={student.id}>{student.name} - {student.email}</li>
                ))}
            </ul>
        </div>
    );
};

export default StudentList;
