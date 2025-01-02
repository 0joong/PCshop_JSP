package kr.ac.kopo.board.vo;

import java.sql.Timestamp;

public class CommentVO {
    
    private int commentId; // 댓글 ID
    private int postId; // 게시글 ID
    private Integer parentCommentId; // 부모 댓글 ID (최상위 댓글일 경우 null)
    private String content; // 댓글 내용
    private String userId; // 댓글 작성자 ID
    private Timestamp createdAt; // 댓글 작성 시간
    private Timestamp updatedAt; // 댓글 수정 시간
    private int depth; // 댓글 계층 깊이

    // 기본 생성자
    public CommentVO() {}

    // 모든 필드를 포함한 생성자
    public CommentVO(int commentId, int postId, Integer parentCommentId, String content, String userId, Timestamp createdAt, Timestamp updatedAt, int depth) {
        this.commentId = commentId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.depth = depth;
    }

    // Getter와 Setter
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", parentCommentId=" + parentCommentId +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", depth=" + depth +
                '}';
    }
}
