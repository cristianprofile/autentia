<%@ page import="com.mylab.cromero.service.domain.Course" %>
<%@ page import="com.mylab.cromero.service.domain.Level" %>
<%@ page import="com.mylab.cromero.service.domain.Teacher" %>
<%@ page import="java.util.List" %>

<script>
    function showCourseForm() {
        var createCourseDiv = document.getElementById("createCourse");
        var listDiv = document.getElementById("list");
        if (createCourseDiv.style.display === "none") {
            createCourseDiv.style.display = "block";
            listDiv.style.display = "none";
        } else {
            createCourseDiv.style.display = "none";

        }
    }
</script>

<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }

        form {
            /* Center the form on the page */
            margin: 0 auto;
            width: 400px;
            /* Form outline */
            padding: 1em;
            border: 1px solid #CCC;
            border-radius: 1em;
        }

        form div + div {
            margin-top: 1em;
        }

        label {
            /* Uniform size & alignment */
            display: inline-block;
            width: 90px;
            text-align: right;
        }

        input,
        textarea {
            /* To make sure that all text fields have the same font settings
               By default, textareas have a monospace font */
            font: 1em sans-serif;

            /* Uniform text field size */
            width: 300px;
            box-sizing: border-box;

            /* Match form field borders */
            border: 1px solid #999;
        }

        input:focus,
        textarea:focus {
            /* Additional highlight for focused elements */
            border-color: #000;
        }

        textarea {
            /* Align multiline text fields with their labels */
            vertical-align: top;

            /* Provide space to type some text */
            height: 5em;
        }

        .button {
            /* Align buttons with the text fields */
            padding-left: 90px; /* same size as the label elements */
        }

        button {
            /* This extra margin represent roughly the same space as the space
               between the labels and their text fields */
            margin-left: .5em;
        }

    </style>
    <title>Course List</title>
</head>
<body>
<div id="createCourse" style="display: none">
    <form method="post" action="">
        <div>
            <label>title</label>
            <input type="text" name="title">
        </div>
        <div>
            <label>hours</label>
            <input type="text" name="hours">
        </div>


        <%
            List<Teacher> teachers = (List<Teacher>) request.getAttribute("teachers");
            if (teachers != null) {
        %>
        <div>
            <label>teacher</label>
            <select name="teacherId">
                <% for (int j = 0; j < teachers.size(); ++j) {
                %>
                <option value="<%= teachers.get(j).getId() %>"><%= teachers.get(j).getName() %>
                </option>

                <%
                    }
                %>

            </select>
        </div>
        <%
            }
        %>

        <div>
            <label>level</label>
            <select name="level">
                <option value="<%= Level.BASIC %>"><%= Level.BASIC %>
                </option>
                <option value="<%= Level.ADVANCE %>"><%= Level.ADVANCE %>
                </option>
                <option value="<%= Level.INTERMEDIATE %>"><%= Level.INTERMEDIATE %>
                </option>
            </select>
        </div>

        <div>
            <label>active</label>
            <input type="checkbox" name="active" value="true" checked="checked">
        </div>

        <div>
            <input type="submit" value="create course">
        </div>
    </form>
</div>

<div id="list">
    <h3>Course List</h3>

    <%
        List<Course> courses = (List<Course>) request.getAttribute("courses");
        if (courses != null) {
    %>

    <table>
        <tr>
            <th>title</th>
            <th>teacher</th>
            <th>level</th>
            <th>hours</th>
        </tr>

        <%
            for (int i = 0; i < courses.size(); ++i) {
        %>

        <tr>
            <td><%= courses.get(i).getTitle() %>
            </td>
            <td><%= courses.get(i).getTeacher().getName() %>
            </td>
            <td><%= courses.get(i).getLevel() %>
            </td>
            <td><%= courses.get(i).getHours() %>
            </td>
        </tr>
        <%
            }
        %>

    </table>
    <%
        }
    %>
    <div>
        <button onclick="showCourseForm()">Add Course</button>
    </div>

</div>


</body>
</html>
