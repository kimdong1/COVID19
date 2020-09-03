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
	$(function(){
		var val = solution3([10,9,8,7,6,5,4,3,2,1]);
		alert(val);
	})
	
	function solution3(arr) {
	    var answer = true;
		arr.sort(function(a, b){ return a-b;});
		alert(arr);
	    for(var i = 0; i < arr.length; i++){
	    	if(arr[i] == i+1) answer = true;
	    	else answer = false;
	    }
	    return answer;
	}
	
	function solution1(arr)
	{
	    var answer = [];
	    
	    var val = arr[0];
	    answer.push(val);

	    for(var i = 1; i < arr.length; i++){
	        if(val != arr[i]){
	            val = arr[i];
	            answer.push(val);
	        }
	    }
	    
	    return answer;
	}
</script>









    <script type="text/javascript">
    google.charts.load('current', {
	    'packages':['geochart'],
	    // Note: you will need to get a mapsApiKey for your project.
	    // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
	    'mapsApiKey': 'AIzaSyBwIY_BQlEAyNSZcFqQZmWj131-sqvjy_A'
    });
    google.charts.setOnLoadCallback(drawRegionsMap);
      
   	var areaArr = new Array("KR-11","KR-26","KR-27","KR-28","KR-29","KR-30","KR-31","KR-50","KR-41","KR-42",
   			"KR-43","KR-44","KR-45","KR-46","KR-47","KR-48","KR-49");
    function drawRegionsMap() {
    	
        var data = google.visualization.arrayToDataTable([
          ['지역코드', '지역명', '감염자 수'],
          [areaArr[0], areaNumToArea(areaArr[0]), ${list[0].infected }], [areaArr[1], areaNumToArea(areaArr[1]), ${list[1].infected }], [areaArr[2], areaNumToArea(areaArr[2]), ${list[2].infected }], 
          [areaArr[3], areaNumToArea(areaArr[3]), ${list[3].infected }], [areaArr[4], areaNumToArea(areaArr[4]), ${list[4].infected }], [areaArr[5], areaNumToArea(areaArr[5]), ${list[5].infected }],
          [areaArr[6], areaNumToArea(areaArr[6]), ${list[6].infected }], [areaArr[7], areaNumToArea(areaArr[7]), ${list[7].infected }], [areaArr[8], areaNumToArea(areaArr[8]), ${list[8].infected }], 
          [areaArr[9], areaNumToArea(areaArr[9]), ${list[9].infected }], [areaArr[10],areaNumToArea(areaArr[10]), ${list[10].infected }], [areaArr[11], areaNumToArea(areaArr[11]), ${list[11].infected }],
          [areaArr[12], areaNumToArea(areaArr[12]), ${list[12].infected }], [areaArr[13], areaNumToArea(areaArr[13]), ${list[13].infected }], [areaArr[14], areaNumToArea(areaArr[14]), ${list[14].infected }], 
          [areaArr[15], areaNumToArea(areaArr[15]), ${list[15].infected }], [areaArr[16], areaNumToArea(areaArr[16]), ${list[16].infected }]
        ]);
        var options = {
			sizeAxis: { minValue: 1, maxValue: 1 },
		    region: 'KR',
	 	  	resolution: 'provinces',
	 	  	backgroundColor: '#81d4fa',
		    colorAxis: {colors: ['yellow', 'orange', 'red']}	  
        };
        var chart = new google.visualization.GeoChart(document.getElementById('geo_chart'));
        chart.draw(data, options);
          
      	google.visualization.events.addListener(chart, 'select', function() {
      		var selection = chart.getSelection();
      		//alert(selection.length);
      		var item = selection[0];
      		//var areaNum = data.getFormattedValue(item.row, 0);
      		var area = data.getFormattedValue(item.row, 1);
      		var infected = data.getFormattedValue(item.row, 2);
      		
      		alert("지역 : " + area + "\n"+ "감염자 수 : " + infected + "명");
      	  });
      }
    
    function areaNumToArea(areaNum){
    	var area = "";
    	"KR-11","KR-26","KR-27","KR-28","KR-29","KR-30","KR-31","KR-50","KR-41","KR-42",
			"KR-43","KR-44","KR-45","KR-46","KR-47","KR-48","KR-49"
    	switch (areaNum){
	    	case "KR-11": area="서울"; break;
	    	case "KR-26": area="부산"; break;
	    	case "KR-27": area="대구"; break;
	    	case "KR-28": area="인천"; break;
	    	case "KR-29": area="광주"; break;
	    	case "KR-30": area="대전"; break;
	    	case "KR-31": area="울산"; break;
	    	case "KR-50": area="세종"; break;
	    	case "KR-41": area="경기"; break;
	    	case "KR-42": area="강원"; break;
	    	case "KR-43": area="충북"; break;
	    	case "KR-44": area="충남"; break;
	    	case "KR-45": area="전북"; break;
	    	case "KR-46": area="전남"; break;
	    	case "KR-47": area="경북"; break;
	    	case "KR-48": area="경남"; break;
	    	case "KR-49": area="제주"; break;
    	}
    	return area;
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
	<h3>실시간</h3>
	<fmt:formatNumber var="formatTotal" value="${total }" pattern="###,###,###" />
	전체 확진자 수 : ${formatTotal }명<span style="color: red;">${subNum }</span>
	<br />
	사망자 수 : ${dead }명
	<hr />
	<div id="geo_chart" style="width: 60%; height: 500px;"></div>
	
	<br />
	<h3>수동 업데이트</h3>
	<hr />
	<span style="font-weight: bold;">번호</span> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<span style="font-weight: bold;">내용</span>
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