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
}
