package social;

public class Post {
    private final int userId;
    private final int postId;
    private final String postText;

    public Post(int userId, int postId, String postText) {
        this.userId = userId;
        this.postId = postId;
        this.postText = postText;
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
