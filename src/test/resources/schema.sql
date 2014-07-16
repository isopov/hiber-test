create sequence HIBERNATE_SEQUENCE;

create table TEST_ENTITY (
	ID INTEGER PRIMARY KEY,
	GUID VARCHAR(36),
	LABEL VARCHAR(100)
);

alter table TEST_ENTITY add constraint GUID_UK unique (guid);