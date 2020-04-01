use _055_DB;

delete from  `_055_db`.`OrderItems`;
alter table OrderItems auto_increment=1;

delete from  `_055_db`.`Orders`;
alter table Orders auto_increment=1;

delete from   `_055_db`.`Users`;
alter table Users auto_increment=1;

delete from  `_055_db`.`Products`;
alter table Products auto_increment=1;

delete from  `_055_db`.`CustomerTypes`;
alter table CustomerTypes auto_increment=1;

delete from `_055_db`.`Dimensions`;
alter table Dimensions auto_increment=1;

delete from  `_055_db`.`OrderStatuses`;
alter table OrderStatuses auto_increment=1;

delete from  `_055_db`.`Regions`;
alter table Regions auto_increment=1;

delete from  `_055_db`.`Roles`;
alter table Roles auto_increment=1;