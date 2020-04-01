use _055_OMS

delete from  OrderItems
DBCC CHECKIDENT (OrderItems,RESEED, 0)

delete from  Orders
DBCC CHECKIDENT (Orders,RESEED, 0)

delete from  Users
DBCC CHECKIDENT (Users,RESEED, 0)

delete from  Products
DBCC CHECKIDENT (Products,RESEED, 0)

delete from  CustomerTypes
DBCC CHECKIDENT (CustomerTypes,RESEED, 0)

delete from  Dimensions
DBCC CHECKIDENT (Dimensions,RESEED, 0)

delete from  OrderStatuses
DBCC CHECKIDENT (OrderStatuses,RESEED, 0)

delete from  Regions
DBCC CHECKIDENT (Regions,RESEED, 0)

delete from  Roles
DBCC CHECKIDENT (Roles,RESEED, 0)

delete from  CardType
DBCC CHECKIDENT (CardType,RESEED, 0)