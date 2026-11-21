
package com.staynest.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping(
        value = "/api/v1/greeting",
        produces = MediaType.TEXT_HTML_VALUE
    )
    public String greeting() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport"
                      content="width=device-width, initial-scale=1">
                <title>StayNest API</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background: #f4f6f8;
                        color: #253247;
                        margin: 0;
                        padding: 30px;
                    }
                    main {
                        max-width: 850px;
                        margin: auto;
                        background: white;
                        padding: 30px;
                        border-radius: 12px;
                    }
                    h1, h2 {
                        color: #176b5b;
                    }
                    li {
                        margin-bottom: 10px;
                    }
                </style>
            </head>
            <body>
                <main>
                    <h1>Welcome to StayNest</h1>
                    <p>
                        StayNest is an accommodation booking
                        platform for Azerbaijan. It connects
                        customers with property owners.
                    </p>

                    <h2>API Status</h2>
                    <p>API is running - Version v1</p>

                    
<h2>Team Members</h2>

<p>
    <strong>Novruz Mirishli</strong><br>
    Student ID: 000019638<br>
    Responsibilities: Property CRUD and authentication
</p>

<p>
    <strong>Shamsi Nagiev</strong><br>
    Student ID: 000018974<br>
    Responsibilities: Input validation, structured error
    responses, HTML greeting page, and documentation
</p>

                    

                    <h2>Available Endpoints</h2>
                    <ul>
                        <li>GET /api/v1/properties - List properties</li>
                        <li>GET /api/v1/properties/{id} - Get property</li>
                        <li>POST /api/v1/properties - Create property</li>
                        <li>PUT /api/v1/properties/{id} - Update property</li>
                        <li>DELETE /api/v1/properties/{id} - Delete property</li>
                        <li>GET /api/v1/greeting - Greeting page</li>
                    </ul>
                </main>
            </body>
            </html>
            """;
    }
}
