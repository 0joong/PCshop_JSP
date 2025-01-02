<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.amazonaws.services.s3.AmazonS3" %>
<%@ page import="com.amazonaws.services.s3.AmazonS3ClientBuilder" %>
<%@ page import="com.amazonaws.services.s3.model.PutObjectRequest" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.UUID" %>

<%
    // S3 설정
    final String bucketName = "pcshop1"; // S3 버킷 이름
    final String region = "ap-northeast-2"; // S3 리전

    // 멀티파트 요청인지 확인
    if (request.getContentType() != null && request.getContentType().startsWith("multipart/")) {
        try {
            // 파일 데이터를 Part로 읽어오기
            Part filePart = request.getPart("file"); // input 태그의 name 속성
            String fileName = UUID.randomUUID().toString() + "_" + filePart.getSubmittedFileName(); // 고유 파일 이름 생성
            InputStream fileContent = filePart.getInputStream(); // 파일 데이터를 InputStream으로 읽음

            // S3 클라이언트 생성 (환경 변수에서 자격 증명 사용)
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .build();

            // S3에 파일 업로드 (InputStream 사용, ACL 제거)
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileContent, null));

            // 업로드된 파일의 URL 생성
            String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;

            // 화면에 URL과 이미지를 출력
            out.println("<h1>Image Uploaded Successfully!</h1>");
            out.println("<p>Image URL: <a href='" + fileUrl + "' target='_blank'>" + fileUrl + "</a></p>");
            out.println("<img src='" + fileUrl + "' alt='Uploaded Image' style='max-width: 400px;'>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error while uploading the file: " + e.getMessage() + "</p>");
        }
    } else {
        out.println("<p>Invalid request. Please upload an image file.</p>");
    }
%>