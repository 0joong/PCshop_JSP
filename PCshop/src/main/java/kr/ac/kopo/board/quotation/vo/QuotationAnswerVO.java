package kr.ac.kopo.board.quotation.vo;

import java.sql.Timestamp;

public class QuotationAnswerVO {

    private int answerId;       // 답변 ID
    private int inquiryId;      // 질문 게시글 ID
    private String userId;      // 작성자 ID
    private String content;     // 답변 내용
    private Timestamp createdAt; // 작성일
    private Timestamp updatedAt; // 수정일

    // 기본 생성자
    public QuotationAnswerVO() {}

    // 모든 필드를 초기화하는 생성자
    public QuotationAnswerVO(int answerId, int inquiryId, String userId, String content, Timestamp createdAt, Timestamp updatedAt) {
        this.answerId = answerId;
        this.inquiryId = inquiryId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getter와 Setter
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(int inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
