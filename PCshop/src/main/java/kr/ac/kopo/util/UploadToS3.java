package kr.ac.kopo.util;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class UploadToS3 {
	
	public String uploadToS3(String fileName, InputStream fileContent) {
    	// S3 설정
        final String bucketName = "pcshop1"; // S3 버킷 이름
        final String region = "ap-northeast-2"; // S3 리전

        try {
            // S3 클라이언트 생성
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .build();

            // 메타데이터 설정
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/octet-stream"); // 기본 MIME 타입
            metadata.setContentLength(fileContent.available()); // 파일 크기

            // S3 업로드 요청 생성
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, fileContent, metadata); // 파일을 퍼블릭 읽기 가능하도록 설정

            // 파일 업로드 실행
            s3Client.putObject(request);

            // 업로드된 파일의 URL 생성 및 반환
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("S3 업로드 중 오류가 발생했습니다.");
        }
    }
}
