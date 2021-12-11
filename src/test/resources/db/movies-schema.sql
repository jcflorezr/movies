CREATE SCHEMA IF NOT EXISTS MOVIES;
CREATE SEQUENCE MOVIES.HIBERNATE_SEQUENCE START 1;

CREATE TABLE MOVIES.MOVIEGOER
(
    USER_NAME VARCHAR(100) PRIMARY KEY
);

CREATE TABLE MOVIES.MOVIE
(
    IMDB_ID         VARCHAR(500) PRIMARY KEY,
    TITLE           VARCHAR(500),
    MOVIE_YEAR      VARCHAR(500),
    RATED           VARCHAR(500),
    RELEASED        VARCHAR(500),
    RUNTIME         VARCHAR(500),
    GENRE           VARCHAR(500),
    DIRECTOR        VARCHAR(500),
    WRITER          VARCHAR(500),
    ACTORS          VARCHAR(500),
    PLOT            VARCHAR(500),
    MOVIE_LANGUAGE  VARCHAR(500),
    COUNTRY         VARCHAR(500),
    POSTER          VARCHAR(500),
    IMDB_RATING     VARCHAR(500),
    PRODUCTION      VARCHAR(500),
    WEBSITE         VARCHAR(500)
);

CREATE TABLE MOVIES.RATING
(
    ID              INTEGER PRIMARY KEY,
    SOURCE          VARCHAR(100),
    RATING_VALUE    VARCHAR(100),
    MOVIE_ID        VARCHAR(500),
    CONSTRAINT FK_MOVIE_RATING FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES.MOVIE (IMDB_ID)
);

CREATE TABLE MOVIES.MOVIE_CATALOG_INFO
(
    ID           INTEGER PRIMARY KEY,
    PRICE        VARCHAR(100),
    SHOW_TIME    VARCHAR(100),
    MOVIE_ID     VARCHAR(500),
    CONSTRAINT FK_MOVIE_MOVIE_CATALOG_INFO FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES.MOVIE (IMDB_ID)
);

CREATE TABLE MOVIES.USER_RATING
(
    ID             INTEGER PRIMARY KEY,
    RATING         VARCHAR(100),
    COMMENTS       VARCHAR(100),
    MOVIEGOER_NAME VARCHAR(100),
    MOVIE_ID       VARCHAR(500),
    CONSTRAINT FK_MOVIEGOER_RATING FOREIGN KEY (MOVIEGOER_NAME) REFERENCES MOVIES.MOVIEGOER (USER_NAME),
    CONSTRAINT FK_MOVIE_USER_RATING FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES.MOVIE (IMDB_ID)
);