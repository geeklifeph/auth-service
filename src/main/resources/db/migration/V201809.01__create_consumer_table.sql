CREATE TABLE AuthConsumer
(
   ConsumerId 	varchar(255) PRIMARY KEY NOT NULL,
   CreatedAt 	datetime NOT NULL,
   UpdatedAt 	datetime NOT NULL,
   ClientId 	varchar(255),
   ClientSecret varchar(255),
   Username 	varchar(255)
);

CREATE UNIQUE INDEX PK__AuthConsumer_ConsumerId ON AuthConsumer(ConsumerId);

CREATE UNIQUE INDEX UK_AuthConsumer_Username ON AuthConsumer(Username);

CREATE TABLE AuthCompany
(
   id bigint identity PRIMARY KEY NOT NULL,
   CreatedAt datetime NOT NULL,
   UpdatedAt datetime NOT NULL,
   CompanyId varchar(255),
   CompanyType varchar(255) NOT NULL,
   ConsumerId varchar(255) NOT NULL
);

ALTER TABLE AuthCompany
ADD CONSTRAINT FK_Consumer_Company_On_ConsumerId
FOREIGN KEY (ConsumerId)
REFERENCES AuthConsumer(ConsumerId);

CREATE UNIQUE INDEX PK__AuthCompany_Id ON AuthCompany(id);
