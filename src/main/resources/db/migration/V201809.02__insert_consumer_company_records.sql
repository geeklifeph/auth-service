/*Populate test data*/

/* Insert 1 consumer record*/
INSERT INTO AuthConsumer (ConsumerId,CreatedAt,UpdatedAt,ClientId,ClientSecret,Username) 
	VALUES ('kong-generated-consumer-id',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'client-id-here','client-secret-here','acayanan@shipserv.com');


/* Insert 3 company records*/

SET IDENTITY_INSERT AuthCompany ON;

INSERT INTO AuthCompany (id,CreatedAt,UpdatedAt,CompanyId,CompanyType,ConsumerId) 
	VALUES (1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,null,'INTERNAL','kong-generated-consumer-id');

INSERT INTO AuthCompany (id,CreatedAt,UpdatedAt,CompanyId,CompanyType,ConsumerId) 
	VALUES (2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'100','BUYER','kong-generated-consumer-id');

INSERT INTO AuthCompany (id,CreatedAt,UpdatedAt,CompanyId,CompanyType,ConsumerId) 
	VALUES (3,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'52323','SUPPLIER','kong-generated-consumer-id');

commit;