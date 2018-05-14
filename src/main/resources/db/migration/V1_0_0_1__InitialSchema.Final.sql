--------------------------------------------------------------------------------
-- tables/TEXT_MESSAGE
--------------------------------------------------------------------------------
CREATE TABLE GAME
(
	ID					INTEGER NOT NULL, --SERIAL 			NOT NULL,
	HOME_ID             INTEGER NOT NULL,
	HOME_NAME			VARCHAR(100)	NOT NULL,
	AWAY_ID             INTEGER NOT NULL,
	AWAY_NAME			VARCHAR(100)	NOT NULL,
	GAME_DATE           TIMESTAMP WITH TIME ZONE NOT NULL,
	STATUS              VARCHAR(100)	NOT NULL,
	MATCH_DAY           INTEGER NOT NULL,
    GOALS_HOME          INTEGER NOT NULL,
    GOALS_AWAY          INTEGER NOT NULL,
	CONSTRAINT GAME_PK PRIMARY KEY ( ID )
);


CREATE TABLE ODD
(
	ID					SERIAL 			NOT NULL,
	GAME_ID             INTEGER NOT NULL,
	ODDS_HOME             REAL NOT NULL,
	ODDS_AWAY             REAL NOT NULL,
	ODDS_TIE              REAL NOT NULL,
	ODDS_OVER             REAL NOT NULL,
	ODDS_UNDER            REAL NOT NULL,
	MULTIPLIER            INTEGER NOT NULL,
	CONSTRAINT ODD_PK PRIMARY KEY ( ID )
);

ALTER TABLE ODD ADD CONSTRAINT ODD_FK01 FOREIGN KEY ( GAME_ID ) REFERENCES GAME ( ID );


CREATE TABLE ALLOWED_USERS
(
	ID					SERIAL 			NOT NULL,
	NAME              VARCHAR(100)	NOT NULL,
	CONSTRAINT ALLOWED_USERS_PK PRIMARY KEY ( ID )
);

CREATE TABLE BET
(
	ID					SERIAL 			NOT NULL,
	GAME_ID             INTEGER NOT NULL,
	USER_ID             INTEGER NOT NULL,
	RESULT_BET          VARCHAR(100)	NOT NULL,
	RESULT_POINTS       VARCHAR(100)	NOT NULL,
	OVER_BET            VARCHAR(100)	NOT NULL DEFAULT 0,
	OVER_POINTS         VARCHAR(100)	NOT NULL DEFAULT 0,
	CONSTRAINT BET_PK PRIMARY KEY ( ID )
);
ALTER TABLE BET ADD CONSTRAINT BET_FK01 FOREIGN KEY ( GAME_ID ) REFERENCES GAME ( ID );
ALTER TABLE BET ADD CONSTRAINT BET_FK02 FOREIGN KEY ( USER_ID ) REFERENCES ALLOWED_USERS ( ID );

