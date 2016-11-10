package social;

public class Post {
    private final int postId;
    private final int userId;
    private final String postText;

    public Post(int postId, int userId, String postText) {
        this.postId = postId;
        this.userId = userId;
        this.postText = postText;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPostText() {
        return postText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (userId != post.userId) return false;
        if (postId != post.postId) return false;
        return postText != null ? postText.equals(post.postText) : post.postText == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + postId;
        result = 31 * result + (postText != null ? postText.hashCode() : 0);
        return result;
    }

}
