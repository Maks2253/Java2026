<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Geo Distance Tool</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: radial-gradient(circle at top, #1f4037, #99f2c8);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .wrapper {
            width: 850px;
            display: flex;
            gap: 20px;
        }

        .panel {
            flex: 1;
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(12px);
            border-radius: 18px;
            padding: 25px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.25);
            color: #fff;
        }

        .title {
            font-size: 22px;
            text-align: center;
            margin-bottom: 20px;
            font-weight: bold;
        }

        label {
            font-size: 12px;
            opacity: 0.9;
            display: block;
            margin-top: 10px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 10px;
            border: none;
            outline: none;
            background: rgba(255,255,255,0.8);
        }

        button {
            width: 100%;
            margin-top: 15px;
            padding: 12px;
            border: none;
            border-radius: 12px;
            background: #00c9a7;
            color: white;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background: #00b39b;
        }

        .result {
            font-size: 18px;
            text-align: center;
            margin-top: 40px;
            padding: 20px;
            background: rgba(0,0,0,0.2);
            border-radius: 12px;
        }

        .error {
            margin-top: 20px;
            background: rgba(255,0,0,0.2);
            padding: 12px;
            border-radius: 10px;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="wrapper">

    <!-- ЛІВА ПАНЕЛЬ -->
    <div class="panel">
        <div class="title"> Координати</div>

        <form method="post">

            <label>Latitude 1</label>
            <input type="text" name="lat1" value="${param.lat1}" required>

            <label>Longitude 1</label>
            <input type="text" name="lon1" value="${param.lon1}" required>

            <label>Latitude 2</label>
            <input type="text" name="lat2" value="${param.lat2}" required>

            <label>Longitude 2</label>
            <input type="text" name="lon2" value="${param.lon2}" required>

            <label>Earth Radius</label>
            <input type="text" name="radius" value="${empty param.radius ? '6371000' : param.radius}">

            <button type="submit">CALCULATE</button>

        </form>
    </div>

    <!-- ПРАВА ПАНЕЛЬ -->
    <div class="panel">
        <div class="title"> Result</div>

        <%
            if (request.getMethod().equalsIgnoreCase("POST")) {
                try {
                    double lat1 = Double.parseDouble(request.getParameter("lat1"));
                    double lon1 = Double.parseDouble(request.getParameter("lon1"));
                    double lat2 = Double.parseDouble(request.getParameter("lat2"));
                    double lon2 = Double.parseDouble(request.getParameter("lon2"));
                    double R = Double.parseDouble(request.getParameter("radius"));

                    double phi1 = Math.toRadians(lat1);
                    double phi2 = Math.toRadians(lat2);
                    double dLat = Math.toRadians(lat2 - lat1);
                    double dLon = Math.toRadians(lon2 - lon1);

                    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                               Math.cos(phi1) * Math.cos(phi2) *
                               Math.sin(dLon / 2) * Math.sin(dLon / 2);

                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                    double distance = R * c;
        %>

                    <div class="result">
                        Distance: <br>
                        <b><%= String.format("%.2f", distance) %> meters</b>
                    </div>

        <%
                } catch (Exception e) {
        %>

                    <div class="error">
                        Invalid input format
                    </div>

        <%
                }
            } else {
        %>

            <div class="result">
                Enter coordinates to calculate distance
            </div>

        <%
            }
        %>

    </div>

</div>

</body>
</html>