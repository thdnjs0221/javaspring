<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar Example</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>달력</h1>
    
    <div>
        <label for="yearSelect">YEAR:</label>
        <select id="yearSelect" onchange="updateCalendar()">
            <c:forEach var="year" begin="2000" end="2030">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>
        
        <label for="monthSelect">Month:</label>
<select id="monthSelect" onchange="updateCalendar()">
    <option value="1">January</option>
    <option value="2">February</option>
    <option value="3">March</option>
    <option value="4">April</option>
    <option value="5">May</option>
    <option value="6">June</option>
    <option value="7">July</option>
    <option value="8">August</option>
    <option value="9">September</option>
    <option value="10">October</option>
    <option value="11">November</option>
    <option value="12">December</option>
</select>
        
        <label for="localeSelect">Locale:</label>
<select id="localeSelect">
    <option value="en_US">English (US)</option>
    <option value="ko_KR">Korean (KR)</option>
    <option value="ja_JP">Japanese (JP)</option>
    <!-- 다른 Locale 추가 -->
</select>
        
        <label for="timezoneSelect">Timezone:</label>
        <select id="timezoneSelect">
            <option value="America/New_York">New York</option>
            <option value="Asia/Seoul">Seoul</option>
            <!-- ... (다른 Timezone 추가) ... -->
        </select>
        
        <button onclick="prevMonth()">이전달</button>
        <button onclick="nextMonth()">다음달</button>
    </div>
    
    <table id="calendarTable">
        <tr>
            <th>Sun</th>
            <th>Mon</th>
            <th>Tue</th>
            <th>Wed</th>
            <th>Thu</th>
            <th>Fri</th>
            <th>Sat</th>
        </tr>
        <!-- 여기에 달력 내용이 들어갈 예정 -->
    </table>
    
   <script>
    var yearSelect = document.getElementById("yearSelect");
    var monthSelect = document.getElementById("monthSelect");
    var localeSelect = document.getElementById("localeSelect");
    var timezoneSelect = document.getElementById("timezoneSelect");
    var calendarTable = document.getElementById("calendarTable");
    
    function updateCalendar() {
        var year = parseInt(yearSelect.value);
        var month = parseInt(monthSelect.value) - 1;
        var locale = localeSelect.value;
        var timezone = timezoneSelect.value;

        var currentDate = new Date(year, month);
        var daysInMonth = new Date(year, month + 1, 0).getDate();
        var startDay = new Date(year, month, 1).getDay();

        var numRows = Math.ceil((daysInMonth + startDay) / 7); // 표시할 행 수 계산

        calendarTable.innerHTML = "<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>";

        var dayCount = 1;
        for (var i = 0; i < numRows; i++) { // numRows만큼 반복
            var row = document.createElement("tr");

            for (var j = 0; j < 7; j++) {
                if ((i === 0 && j < startDay) || dayCount > daysInMonth) {
                    var cell = document.createElement("td");
                    row.appendChild(cell);
                } else {
                    var cell = document.createElement("td");
                    cell.textContent = dayCount;
                    row.appendChild(cell);
                    dayCount++;
                }
            }

            calendarTable.appendChild(row);
        }
    }
    
    function prevMonth() {
        var currentYear = parseInt(yearSelect.value);
        var currentMonth = parseInt(monthSelect.value);
        
        if (currentMonth === 1) {
            yearSelect.value = currentYear - 1;
            monthSelect.value = 12;
        } else {
            monthSelect.value = currentMonth - 1;
        }
        
        updateCalendar();
    }
    
    function nextMonth() {
        var currentYear = parseInt(yearSelect.value);
        var currentMonth = parseInt(monthSelect.value);
        
        if (currentMonth === 12) {
            yearSelect.value = currentYear + 1;
            monthSelect.value = 1;
        } else {
            monthSelect.value = currentMonth + 1;
        }
        
        updateCalendar();
    }
    
    // 초기화
    updateCalendar();
</script>
</body>
</html>