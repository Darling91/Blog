
CREATE TABLE users (
                       user_id UUID PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       CONSTRAINT uk_users_username UNIQUE (username),
                       CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE posts (
                       post_id UUID PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       content TEXT NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       updated_at TIMESTAMP,
                       user_id UUID NOT NULL,
                       CONSTRAINT fk_posts_user FOREIGN KEY (user_id)
                           REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE comments (
                          comment_id UUID PRIMARY KEY,
                          text TEXT NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          user_id UUID NOT NULL,
                          post_id UUID NOT NULL,
                          CONSTRAINT fk_comments_user FOREIGN KEY (user_id)
                              REFERENCES users(user_id) ON DELETE CASCADE,
                          CONSTRAINT fk_comments_post FOREIGN KEY (post_id)
                              REFERENCES posts(post_id) ON DELETE CASCADE
);