CREATE TABLE Users (
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE
);

CREATE TABLE Events (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        dateTime TIMESTAMP
);

CREATE TABLE Tickets (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         userId BIGINT,
                         eventId BIGINT,
                         place INT,
                         category VARCHAR(20),
                         FOREIGN KEY (userId) REFERENCES Users(id),
                         FOREIGN KEY (eventId) REFERENCES Events(id)
);
