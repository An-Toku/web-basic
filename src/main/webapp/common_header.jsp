<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link href="css/common.css" rel="stylesheet">
<link href="css/layout.css" rel="stylesheet">
<link href="css/components.css" rel="stylesheet">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>日本語勉強</title>
<script src="common.script/common.js"></script>
</head>
<body>
	<form name="forHref">
		<input type="hidden" name="t_url">
		<input type="hidden" name="t_action">
	</form>
	<div class="header">
		<div class="topBar">
			<ul>
				<c:if test="${empty sessionId}">
					<li><a href="javascript:goPage('Member', 'login')">로그인</a></li>
					<li><a href="javascript:goPage('Member', 'join')">회원가입</a></li>
				</c:if>
				<c:if test="${not empty sessionId}">
					<li>${sessionName}님</li>
					<li><a href="javascript:goPage('Member', 'myInfo')">회원정보</a></li>
					<li><a href="javascript:goPage('Member', 'memberLogout')">로그아웃</a></li>
				</c:if>
			</ul>
		</div>
	
		<div class="siteBanner">
			<img src="img/banner.png" class="siteBannerImage">
			<a href="Index" class="brand">
				<strong>日本語勉強</strong>
				<span>Nihongo Benkyou · 일본어 공부</span>
			</a>
		</div>
	
		<div class="serviceBar">
			<ul>
				<li class="allServices">
					<a href="">전체메뉴</a>
					<div class="dropdownMenu">
						<ul>
							<li>
								<strong class="title"><a href="">메일</a></strong>
								<a href="">메일쓰기</a>
								<a href="">보관함</a>
								<a href="">휴지통</a>
							</li>
							<li>
								<strong class="title"><a href="">일상공유</a></strong>
								<a href="">전체 일상글</a>
								<a href="">일상글 쓰기</a>
								<a href="">일상글 관리</a>
							</li>
							<li>
								<strong class="title"><a href="">질문</a></strong>
								<a href="">전체 질문</a>
								<a href="">질문 쓰기</a>
								<a href="">질문 관리</a>
							</li>
							<li>
								<strong class="title"><a href="">친구</a></strong>
								<a href="">친구 찾기</a>
								<a href="">친구 관리</a>
							</li>
							<li>
								<strong class="title"><a href="javascript:goPage('Member', 'myInfo')">회원정보</a></strong>
								<a href="">아이디/비밀번호 찾기</a>
								<a href="javascript:goPage('Member', 'myInfo')">회원정보 수정</a>
								<a href="">회원 탈퇴</a>
							</li>
							<li>
								<strong class="title"><a href="">공지사항</a></strong>
								<a href="Notice">공지사항</a>
								<a href="">회원문의</a>
								<a href="">FAQ</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="service"><a href="">메일</a></li>
				<li class="service"><a href="">일상공유</a></li>
				<li class="service"><a href="">질문</a></li>
				<li class="service"><a href="">친구</a></li>
			</ul>
		</div>
	</div>
