use lv316oms;


insert into Roles(RoleName) values('Administrator');
insert into Roles(RoleName) values('Merchandiser');
insert into Roles(RoleName) values('Supervisor');
insert into Roles(RoleName) values('Customer');


insert into Regions(RegionName) values('North');
insert into Regions(RegionName) values('East');
insert into Regions(RegionName) values('South');
insert into Regions(RegionName) values('West');


insert into OrderStatuses(OrederStatusName) values('Created');
insert into OrderStatuses(OrederStatusName) values('Pending');
insert into OrderStatuses(OrederStatusName) values('Ordered');
insert into OrderStatuses(OrederStatusName) values('Delivered');

insert into Dimensions(DimensionName,NumberOfProducts) values('Item',1);
insert into Dimensions(DimensionName,NumberOfProducts) values('Box',5);
insert into Dimensions(DimensionName,NumberOfProducts) values('Package',10);

insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,1000,0,'Standart');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,3000,1000,'Silver');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,10000,3000,'Gold');
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,null,10000,'Platinum');

insert into Products(ProductDescription,ProductName,ProductPrice) values('productDescription1','productName1',1);
insert into Products(ProductDescription,ProductName,ProductPrice) values('productDescription2','productName2',2);
insert into Products(ProductDescription,ProductName,ProductPrice) values('productDescription3','productName3',3);
insert into Products(ProductDescription,ProductName,ProductPrice) values('productDescription4','productName4',4);
insert into Products(ProductDescription,ProductName,ProductPrice) values('productDescription5','productName5',5);

insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','ivanka','horoshko','iva','qwerty',1,4,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','myroslav','shram','myroslav','qwerty',2,3,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','marko','bekhta','marko','qwerty',3,2,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','vitalik','nobis','vitalik','qwerty',4,1,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','orest','vovchack','orest','qwerty',1,4,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','roman','nazarevuch','romanN','qwerty',2,3,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','roman','svustyn','romanS','qwerty',3,2,1);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','firstName1','lastName1','login1','qwerty',1,1,2);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','firstName2','lastName2','login2','qwerty',1,1,3);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','firstName3','lastName3','login3','qwerty',1,1,4);
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) values(10,'mail','firstName4','lastName4','login4','qwerty',1,2,4);

insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,1,10,3,11,'OrderName1',1);
insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,3,11,3,12,'OrderName2',2);
insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,7,10,2,13,'OrderName3',3);
insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,4,10,1,14,'OrderName4',4);
insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,2,11,4,15,'OrderName5',5);
insert into Orders(DeliveryDate,OrderDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber) values(null,null,10,2,10,1,16,'OrderName6',6);

insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,2,1,1);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,1,2,2);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,3,3,3);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,2,4,4);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,3,5,4);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,1,6,3);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,3,2,1);
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(10,10,2,2,4,2);

UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='1';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='2';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='3';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='4';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='5';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='6';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='7';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='8';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='9';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='10';
UPDATE `lv316oms`.`users` SET `IsUserActive`=1 WHERE `ID`='11';