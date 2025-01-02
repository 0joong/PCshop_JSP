package kr.ac.kopo.board.quotation.vo;

import java.sql.Timestamp;

public class QuotationInquiryVO {
    private int postId; // 게시글 ID
    private String userId; // 작성자 ID
    private String title; // 제목
    private String content; // 내용
    private Timestamp createdAt; // 작성일
    private Timestamp updatedAt; // 수정일
    private int viewCnt; // 조회수
    private int ansCnt; // 답변 개수
    
    
	public QuotationInquiryVO() {}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getAnsCnt() {
		return ansCnt;
	}
	public void setAnsCnt(int ansCnt) {
		this.ansCnt = ansCnt;
	}
	@Override
	public String toString() {
		return "QuoationInquiryVO [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content="
				+ content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", viewCnt=" + viewCnt
				+ ", ansCnt=" + ansCnt + "]";
	}

    
}
