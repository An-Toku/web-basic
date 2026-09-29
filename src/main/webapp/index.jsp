<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="common_header.jsp"%>	
<link href="css/pages/index.css" rel="stylesheet">

	<div class="body">
		<%@ include file="common_menu.jsp" %>
		
		<div class="right homeMain">
			<div class="top homeTop">
				<div class="questionRecommand">
					<div class="titleBox">
						<p class="title">인기 질문</p>
					</div>
					<div class="questionBox" onclick="">
						<p class="question">気分과 気持의 차이는 뭔가요?</p>
					</div>
					<div class="answerBox" onclick="">
						<p>그냥 느낌으로...</p>
						<p>그러게요...</p>
						<p>몰라요</p>
					</div>
				</div>
				<div class="wordRecommand">
					<div class="titleBox">
						<p class="title">오늘의 단어</p>
					</div>
					<div class="wordBox" onclick="">
						<ruby>鎖国<rt>さこく</rt></ruby>
						<p>쇄국</p>
					</div>
				</div>
			</div>
			
			<div class="bottom homeBottom">
				<div class="post">
					<div class="titleBox">
						<p class="title">오늘의 일상</p>
					</div>
					<div class="postContentBox">
						<div class="imgBox" onclick="">
							<img src="img/daily.png">
						</div>
						<div class="contentBox" onclick="">
							<p class="postTitle">최근에 일본에 다녀왔어요</p>
							<p class="postPreview">
								일본의 봄, 공원 가득 피어난 벚꽃 아래에서 🌸<br>
								살랑이는 바람에 꽃잎이 흩날리는 순간까지 너무 예뻤다.<br>
								오래오래 기억하고 싶은 여행의 한 장면
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="common_footer.jsp" %>
