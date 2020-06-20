<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/comm.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    var chart  
    google.charts.load('current', {
	    'packages':['geochart'],
	    // Note: you will need to get a mapsApiKey for your project.
	    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
	    'mapsApiKey': 'AIzaSyBHeimWpwRC7Bs6L5YQ5sTHX6E0fdPO51g'
    });
    google.charts.setOnLoadCallback(drawRegionsMap);
      
    function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
          ['지역', '감염자 수'],
          ['서울', ${list[0].infected }],
          ['부산', ${list[1].infected }],
          ['대구', ${list[2].infected }],
          ['인천', ${list[3].infected }],
          ['광주', ${list[4].infected }],
          ['대전', ${list[5].infected }],
          ['울산', ${list[6].infected }],
          ['세종', ${list[7].infected }],
          ['경기', ${list[8].infected }],
          ['강원', ${list[9].infected }],
          ['충북', ${list[10].infected }],
          ['충남', ${list[11].infected }],
          ['전북', ${list[12].infected }],
          ['전남', ${list[13].infected }],
          ['경북', ${list[14].infected }],
          ['경남', ${list[15].infected }],
          ['제주', ${list[16].infected }]
        ]);
        var options = {
			sizeAxis: { minValue: 0, maxValue: 100 },
		    region: 'KR',
		    displayMode: 'markers',
	 	  	backgroundColor: '#81d4fa',
		    colorAxis: {colors: ['yellow', 'orange', 'red']}	  
        };
        var chart = new google.visualization.GeoChart(document.getElementById('geo_chart'));
        chart.draw(data, options);
          
      	google.visualization.events.addListener(chart, 'select', function() {
      		var selection = chart.getSelection();
      		//alert(selection.length);
      		var item = selection[0];
      		var area = data.getFormattedValue(item.row, 0);
      		var infected = data.getFormattedValue(item.row, 1);
      		alert("지역 : " + area + "\n"+ "감염자 수 : " + infected);
      	  });
      }
    
      
      </script>
</head>
<body>
	<c:if test="${list.size() == 0 }">
		<button onclick="sendPost('${pageContext.request.contextPath}/firstData')">새로 가져오기</button>
	</c:if>
	<button onclick="sendPost('${pageContext.request.contextPath}/dataUpdate')">데이터 update</button>
	<br />
	<hr />
	<fmt:formatNumber var="formatTotal" value="${total }" pattern="###,###,###" />
	전체 확진자 수 : ${formatTotal }명<span style="color: red;">${subNum }</span>
	<br />
	사망자 수 : ${dead }명
	<hr />
	<div id="geo_chart" style="width: 60%; height: 500px;"></div>
	
	<br />
	<hr />
	<c:forEach var="vo" items="${list }" varStatus="vs">
		<fmt:formatDate var="date" value="${vo.regDate }" pattern="yyyy.MM.dd(E) HH:mm"/>
		<table>
			<tr>
				<td rowspan="4"><h1>${vs.count }</h1></td>
				<td>등록일 : ${date }</td>
			</tr>
			<tr>
				<td>지역 : ${vo.area }</td>
			</tr>
			<tr>
				<td>감염자 수 : ${vo.infected }</td>
			</tr>
			<tr>
				<td>추가된 감염자 수 : ${vo.additionalInfected }</td>
			</tr>
		</table>
		<hr />
	</c:forEach>
</body>
</html>