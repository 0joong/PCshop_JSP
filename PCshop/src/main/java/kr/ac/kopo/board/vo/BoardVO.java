package kr.ac.kopo.board.vo;

import java.time.LocalDateTime;
import java.util.List;

public class BoardVO {
	private long postId;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; // 타입 변경
    private String status;
    private int viewCnt;
    private int ansCnt;
    private String fileUrl; // camelCase 수정
    private int likes;
    private List<CommentVO> comments;
	
	
	public List<CommentVO> getComments() {
		return comments;
	}
	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileURL) {
		this.fileUrl = fileURL;
	}
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BoardVO() {}
	@Override
	public String toString() {
		return "BoardVO [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", status=" + status + ", viewCnt="
				+ viewCnt + ", ansCnt=" + ansCnt + ", fileUrl=" + fileUrl + ", likes=" + likes + "]";
	}
	
	
}
