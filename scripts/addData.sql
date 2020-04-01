use lv316oms;


insert into Roles(RoleName) values('Administrator')
insert into Roles(RoleName) values('Customer')
insert into Roles(RoleName) values('Merchandiser')
insert into Roles(RoleName) values('Supervisor')


insert into Regions(RegionName) values('East')
insert into Regions(RegionName) values('North')
insert into Regions(RegionName) values('South')
insert into Regions(RegionName) values('West')


insert into OrderStatuses(OrederStatusName) values('Created')
insert into OrderStatuses(OrederStatusName) values('Pending')
insert into OrderStatuses(OrederStatusName) values('Ordered')
insert into OrderStatuses(OrederStatusName) values('Delivered')

insert into Dimensions(DimensionName,NumberOfProducts) values('Item',1)
insert into Dimensions(DimensionName,NumberOfProducts) values('Box',5)
insert into Dimensions(DimensionName,NumberOfProducts) values('Package',10)

insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,1000,0,'Standart')
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,3000,1000,'Silver')
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,10000,3000,'Gold')
insert into CustomerTypes(Discount,MaxRange,MinRange,TypeName) values(0,null,10000,'Platinum')

insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription1','productName1',1,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription2','productName2',2,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription3','productName3',3,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription4','productName4',4,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription5','productName5',5,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription6','productName6',1,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription7','productName7',2,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription8','productName8',3,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription9','productName9',4,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('productDescription10','productNam10',5,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('tasty','tomato',2,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('ugly','brains',3,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('i like it','bear',4,1)
insert into Products(ProductDescription,ProductName,ProductPrice, IsProductActive) values('very nice','sweets',5,1)

insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','ivanka','horoshko','iva','qwerty',1,4,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','myroslav','shram','myroslav','qwerty',2,3,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','marko','bekhta','marko','qwerty',3,2,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','vitalik','nobis','vitalik','qwerty',4,1,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','orest','vovchack','orest','qwerty',1,4,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','roman','nazarevuch','romanN','qwerty',2,3,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','roman','svustyn','romanS','qwerty',3,2,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','firstName1','lastName1','login1','qwerty',1,1,2,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','firstName2','lastName2','login2','qwerty',1,1,3,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','firstName3','lastName3','login3','qwerty',1,1,4,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','firstName4','lastName4','login4','qwerty',1,2,4,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','noneivanka','nonehoroshko','noneiva','qwerty',1,4,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonemyroslav','noneshram','nonemyroslav','qwerty',2,3,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonemarko','nonebekhta','nonemarko','qwerty',3,2,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonevitalik','nonenobis','nonevitalik','qwerty',4,1,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','noneorest','nonevovchack','noneorest','qwerty',1,4,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','noneroman','nonenazarevuch','noneromanN','qwerty',2,3,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','noneroman','nonesvustyn','noneromanS','qwerty',3,2,1,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonefirstName1','nonelastName1','nonelogin1','qwerty',1,1,2,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonefirstName2','nonelastName2','nonelogin2','qwerty',1,1,3,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonefirstName3','nonelastName3','nonelogin3','qwerty',1,1,4,1)
insert into Users(Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef,IsUserActive) values(10,'mail@gmail.com','nonefirstName4','nonelastName4','nonelogin4','qwerty',1,2,4,1)

insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',640,1,10,4,11,'OrderName1',1,1);
insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',120,3,11,3,12,'OrderName2',2,0);
insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',0,7,10,2,13,'OrderName3',3,1);
insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',0,4,10,1,14,'OrderName4',4,0);
insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',0,2,11,4,15,'OrderName5',5,1);
insert into Orders(DeliveryDate,OrderDate,PreferableDeliveryDate,TotalPrice,Assigne,Customer,OrderStatusRef,MaxDiscount,OrderName,OrderNumber,IsGift) values('2012-12-02 00:00:00.000','2010-12-02 00:00:00.000','2011-12-02 00:00:00.000',0,2,10,1,16,'OrderName6',6,0);

insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(20 ,10,2,1,1,1)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(100,10,2,2,1,2)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(200,10,2,3,1,3)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(20 ,10,2,1,1,4)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(100,10,2,2,1,4)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(200,10,2,3,1,3)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(20 ,10,2,1,2,1)
insert into OrderItems(Cost,ItemPrice,Quantity,DimensionRef,OrderRef,ProductRef) values(100,10,2,2,2,2)





