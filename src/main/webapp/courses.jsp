<%@ page import="com.mylab.cromero.service.Course" %>
<%@ page import="com.mylab.cromero.service.Level" %>
<%@ page import="java.util.List" %>
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
    </style>
    <title>Course List</title>
</head>
<body>
    <form method="post" action="">
        <tr>
            <td>title</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>hours</td>
            <td><input type="text" name="hours"></td>
        </tr>

        <tr>
            <td>level</td>

            <td>
                <select name="level">
                    <option value="<%= Level.BASIC %>"><%= Level.BASIC %></option>
                    <option value="<%= Level.ADVANCE %>"><%= Level.ADVANCE %></option>
                    <option value="<%= Level.INTERMEDIATE %>"><%= Level.INTERMEDIATE %></option>
                </select>
            </td>
        </tr>

        <tr>
            <td>active</td>
            <td><input type="checkbox" name="active" value="true" checked="checked"></td>
        </tr>

        <tr>
            <td><input type="submit" value="create course"></td>
        </tr>
    </form>


<h3>Course List:</h3>

<%
    List<Course> courses = (List<Course>) request.getAttribute("courses");
    if (courses != null) {
%>

<table>
    <tr>
        <th>title</th>
        <th>level</th>
        <th>hours</th>
    </tr>

    <%
        for (int i = 0; i < courses.size(); ++i) {
    %>

    <tr>
        <td><%= courses.get(i).getTitle() %></td>
        <td><%= courses.get(i).getLevel() %></td>
        <td><%= courses.get(i).getHours() %></td>
    </tr>
    <%
        }
    %>

</table>
<%
    }
%>

</body>
</html>
