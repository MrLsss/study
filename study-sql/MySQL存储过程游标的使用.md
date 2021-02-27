# MySQL存储过程游标的使用

# 场景
现有两张表，A表：银行交易流水表，B表：账户信息表，其中A表按交易时间升序，ID为自增长。
现要求当A表是进账，并且交易的对手账号卡是B表里面的账户的时候，查询A表该记录的后5条出账记录
# 思路
由于A表的ID是自增长的，所以可以查询出当A表进账是B表账户时的记录id，然后在A表中查询id大于刚刚查询出来的id即可
# 游标的使用

1. 声明一个游标: declare 游标名称 CURSOR for table;(这里的table可以是你查询出来的任意集合)
1. 打开定义的游标:open 游标名称;
1. 获得下一行数据:FETCH  游标名称 into testrangeid,versionid;
1. 需要执行的语句(增删改查):这里视具体情况而定
1. 释放游标:CLOSE 游标名称;
# SQL
```sql
drop procedure if exists fun;  
CREATE PROCEDURE fun()  
BEGIN

	declare id BIGINT;  
	declare done int;  
	declare cur CURSOR for   
  -- 查询id，将结果给游标
		select
				a.id
		from order a
		where a.counter_bank_no in (

				select distinct b.bank_no
				from user b

		) and a.payment_sign = '进'
		GROUP BY a.id;
		
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done=1;   
	open cur;
		idLoop:LOOP
			if done = 1 then
				LEAVE idLoop;
			END if;
			
			fetch cur into id;
			insert into `临时表`
			select b.*
			from order b
			where b.id > id and b.payment_sign = '出'
			limit 5;
			
		end LOOP idLoop;
	CLOSE cur;
END
```


