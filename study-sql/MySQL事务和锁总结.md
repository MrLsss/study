# MySQL事务和锁总结

> 事务是指满足ACID特性的一组操作，可以通过commit提交一个事务，也可以通过rollback回滚一个事务

# 事务的特性

- Atomicity：原子性
  - 事务被视为不可分割的最小单元，事务的所有操作要么全部成功，要么全部失败回滚。
- Consistency：一致性
  - 系统从一个正确的状态迁移到另一个正确的状态，在一致性状态下，所有事务对一个数据的读取结果都是相同的。
- Isolation：隔离性
  - 一个事务所做的修改在最终提交以前，对其他事务是不可见的。
- Durability：持久性
  - 一旦事务提交，则其所做的修改将会永远保存到数据库中。即使系统发生崩溃，事务执行的结果也不能丢失。
## 关于一致性的理解
> 应用系统从一个正确的状态迁移到另一个正确的状态，而ACID就是说事务能够通过AID来保证这个C的过程，C是目的，AID是手段

“正确的状态”：满足数据库约束的状态
举一个转账的例子
```sql
Table：Account
Columns：name(String), Balance(int)
约束条件：无

执行下面的一个事务（a给b转账1200）
1.往表Account插入数据(a, 1000)
2.往表Account插入数据(b, 1000)
3.a给b转账1200，更新a的余额为-200(a, -200)
4.b的余额增加1200，更新b的余额为2200(b, 2200)
```
在这个例子中，事务能够成功执行，因为没有约束余额不能小于0。虽然从应用层来说，这个事务是不正确，因为不符合逻辑余额小于0，但是数据库只关心事务满不满足你的数据库定义的约束，不关心它具有什么业务逻辑，这个业务逻辑应该由应用层理解并处理。如果应该层有余额不能小于0的约束，则在应用层利用事务的回滚保证了我们的约束不被破坏。
修改上面的例子
```sql
Table：Account
Columns：name(String), balance(int)
约束条件：balance>=0

执行下面的一个事务（a给b转账1200）
1.往表Account插入数据(a, 1000)
2.往表Account插入数据(b, 1000)
3.a给b转账1200，更新a的余额为-200(a, -200)
4.b的余额增加1200，更新b的余额为2200(b, 2200)
```
增加了约束条件balance>=0，则这个事务违反了约束条件，那么事务，数据库认为它是非法的，不满足一致性的要求，所以数据库执行这个事务会失败。
## ACID之间的关系

- 只有满足一致性，事务的结果才是正确的。
- 在无并发的情况下，事务串行化执行，隔离性一定能够满足。此时只要满足原子性，就一定能够满足一致性。在并发的情况下，多个事务并行执行，事务不仅要满足原子性，还需要满足隔离性，才能满足一致性。
- 事务满足持久化是为了能应对数据库崩溃的情况。



# 隔离性与隔离级别
> 隔离的越严实，效率就会越低

当数据库上有多个事务并行执行的时候，就可能出现**脏读、不可重复读、幻读**的问题，为了解决这些问题，就有了“隔离级别”的概念。

- 脏读（Dirty read）——读
  - A事务执行过程中，B事务读取了A事务的修改。但是A事务可能没有完成提交，发生了RollBack，则B事务读取到的数据就会是不正确的。这个未提交的数据就是脏读。
- 不可重复读（NonRepeatable read）——读后读
  - B事务读取了两次数据，在这两次的读取过程中A事务修改了数据，B事务的这两次读取出来的数据不一致。B事务这种读取的结果，即为不可重复读。
- 幻读（Phantom read）——读后写
  - B事务读取了两次数据，在这两次的读取过程中A事务添加了数据，B事务的这两次读取出来的结果集不一致，即为幻读。

SQL标准的事务隔离级别包括：未提交读、已提交读、可重复读、可串行化

- 未提交读（Read uncommitted）
  - 事务中的修改，即使没有提交，对其他事务也是可见的
- 已提交读（Read committed）
  - 一个事务只能读取到已经提交的事务所做的修改。
- 可重复读（Repeatable read）
  - 一个事务执行过程中看到的数据，总是跟这个事务在启动时看到的数据是一致的
- 可串行化（Serializable）
  - 对于同一行记录，“写”会加锁，“读”会加锁。当出现读写锁冲突的时候，后访问的事务必须等前一个事务执行完成，才能继续执行。

**MySQL的InnoDB默认使用的是RR级别**

| 隔离级别 | 脏读 | 不可重复读 | 幻读 |
| :---: | :---: | :---: | :---: |
| 未提交读 | √ | √ | √ |
| 已提交读 | × | √ | √ |
| 可重复读 | × | × | √ |
| 可串行化 | × | × | × |

## 不可重复读与幻读的区别
不可重复读重点在于update和delete
幻读重点在于insert
如果使用锁机制来实现这两种隔离级别，在可重复读中，该sql第一次读取到数据后，就将这些数据加锁，其它事务无法修改这些数据，就可以实现可重复读了。但这种方法却无法锁住insert的数据，所以当事务A先前读取了数据，或者修改了全部数据，事务B还是可以insert数据提交，这时事务A就会发现莫名其妙多了一条之前没有的数据，这就是幻读，不能通过行锁来避免。需要Serializable隔离级别 ，读用读锁，写用写锁，读锁和写锁互斥，这么做可以有效的避免幻读、不可重复读、脏读等问题，但会极大的降低数据库的并发能力。
所以说不可重复读和幻读最大的区别，就在于如何通过锁机制来解决他们产生的问题。
上文说的，是使用悲观锁机制来处理这两种问题，但是MySQL、ORACLE、PostgreSQL等成熟的数据库，出于性能考虑，都是使用了以乐观锁为理论基础的MVCC（多版本并发控制）来避免这两种问题。
## 四种隔离级别的过程
```sql
mysql> create table T(c int) engine=InnoDB;
insert into T(c) values(1);
```
下面按时间顺序执行两个事务的行为
![image.png](https://cdn.jsdelivr.net/gh/mrlsss/images@main/SQL/MySQL事务和锁总结/MySQL事务总结-1.png)

在不同的隔离级别下，事务A会有哪些返回结果（V1、V2、V3）

- 未提交读
  - V1是2，这个时候虽然B事务还没提交，但是结果已经被A看到了。V2、V3也都是2.
- 已提交读
  - V1是1，V2是2，B事务的更新在提交之后才能被A事务看到，V3是2
- 可重复读
  - V1是1，V2是1，V3是2。因为事务执行期间，看到的数据和事务在启动时看到的数据是一致的，所以在启动的时候看到的V1是1，所以V2也是1
- 串行化
  - B事务执行“将1改成2”的时候，会被锁住，知道A事务提交后，事务B才能继续执行，所以V1、V2是1，V3是2

在实现上，数据库里面会创建一个视图，访问的时候以视图的逻辑结果为准。

- 在“可重复读”隔离级别下，这个视图是在事务启动时创建的，整个事务存在期间都用这个视图。
- 在“已提交读”隔离级别下，这个视图是在每个SQL语句开始执行的时候创建的。
- 在“未提交读”隔离级别下，直接返回记录上的最新值，没有视图的概念。
- 在“串行化”隔离级别下直接用加锁的方式来避免并行访问。
## 事务隔离的实现
在MySQL中，实际上每条记录在更新的时候都会同时记录一条回滚操作。记录上的最新值，通过回滚操作，都可以得到前一个状态的值。
假设一个值从1被按顺序改成了2、3、4，在回滚日志里面就有类似下面的记录
![image.png](https://cdn.jsdelivr.net/gh/mrlsss/images@main/SQL/MySQL事务和锁总结/MySQL事务总结-2.png)
当前值是4，但是在查询这条记录的时候，不同时刻启动的事务会有不同的read-view。在视图A、B、C里面，这一个记录的值分别是1、2、4，同一条记录在系统中可以存在多个版本，就是数据库的**多版本并发控制（MVCC）**，对于read-view C，要得到1，就必须将当前值依次执行图中所有的回滚操作得到。
即使现在又另外一个事务正在将4改成5，这个事务跟read-view A、B、C对应的事务是不会冲突的。
系统会判断，当没有事务再需要用到这些回滚日志的时候，回滚日志会被删除。什么时候才不需要了呢？就是当系统里没有比这个回滚日志更早的read-view的时候。

## 事务的启动方式

1. 显式启动事务语句，begin或start transcation。提交是commit，回滚是rollback。
1. set autocommit=0，这个命令会将这个线程的自动提交关闭。意味着如果只执行一个select语句，这个事务就启动了，并且不会自动提交。这个事务会持续存在到你主动执行commit或rollback语句，或者断开连接
## 长事务
通过事务隔离的实现中，长事务意味着系统里面会存在很老的事务视图，由于这些事务随时可能访问数据里面的任何数据，所以这个事务提交之前，数据库里面它可能用到的回滚记录都必须保留，这就会导致大量占用存储空间。
除了对回滚段的影响，长事务还占用锁资源。
在事务的启动方式中，第二点就会导致长事务。
在autocommit为1的情况下，用begin显式启动的事务，如果执行commit则提交事务。如果执行 `commit work and chain` ，则是提交事务并自动启动下一个事务，这样也省去了再次执行begin语句的开销。同时带来的好处是从程序开发的角度明确的知道了每个语句是否处于事务中。


# 锁
## 分类
### 按工作原理分类

- 共享锁（S锁——Shared Locks）
- 排它锁（X锁——Exclusive Locks）
- 意向共享锁（IS锁——Intention Shared Locks）
- 意向排它锁（IX锁——Intention Exclusive Locks）
### 按锁定范围分类

- 全局锁——锁定整个MySQL的全局执行
- 表级锁——锁定单个表
  - 表锁
  - 元数据锁
- 行级锁——锁定单挑或多条行记录
  - 记录锁——锁定一条记录
  - 间隙锁——锁定一个范围
  - 临键锁——锁定左开右闭的一段区间（记录锁+间隙锁）
## 读写锁与意向锁
### 共享锁/排他锁
> 共享锁/排他锁都只是行锁，与间隙锁无关。

- 共享锁
> 一个事务并发读取某一行记录所需要持有的锁，比如 `select .. in share mode` 

持有同一个共享锁的多个进程可以同时进入保护空间，这就是共享锁命名的来源，因为他们可以共享被锁定的资源，他通常是在读取数据前加锁，以实现多个对数据的读取进程可以相互并发执行不被阻塞，因此也常被成为“读锁”。
虽然共享锁被称为“读锁”，但实际上在可重复读级别下，InnoDB通过MVCC机制实现了无需加锁即可以避免读写冲突，所以在可重复读的级别下，普通的读取是不被加锁的，但 `select ... lock in share mode` 会在行上加共享锁。

- 排他锁
> 一个事务并发更新或删除某一行记录所需要持有的锁，比如 `select ... for update` 

排它锁与共享锁不同，一旦加了排他锁，其他任何加锁请求都会被阻塞，排它锁通常用于写数据前加锁，以便让各个写操作之间保持互斥，因此也被称为“写锁”。
`select .. for update` 会在行上加排它锁。


尽管共享锁/排他锁是行锁，与间隙锁无关，但一个事务在请求共享锁/排他锁时，获取到的结果却可能是行锁，也可能是间隙锁，也可能是临键锁，这取决于数据库的隔离级别以及查询的数据是否存在。
### 意向锁
> 意向共享锁和意向排它锁属于表锁，且取得意向共享锁和意向排它锁是取得共享锁/排它锁的前置条件

意向锁可以分为意向共享锁和意向排它锁
#### 意向锁和普通的读写锁的区别
**问题**：考虑一个事务通过 `select ... lock in share mode` 对某一行加了共享锁，此时另一个事务要对这一行排它锁，那么第二个事务就会进入阻塞等待，但如果一个事务准备给全表加排它锁呢？显然，需要遍历全表中的所有记录，查看每一行记录的加锁状态，才能决定是否能够加锁成功，这样是效率非常低的。
**解决**：只需要在对某一行加锁前，将整个表标记为“某些行已经加了共享锁”的状态，那么另一个事务对应整个表的加锁操作就不需要像我们前面所说的那样去遍历每一行了。
意向锁就是这里的“某些行已经加了锁”的状态标识，所有的共享锁加锁前都要对表加意向共享锁，排它锁加锁前，都要对表加意向排它锁，而意向锁之间不互斥。
#### 意向锁加锁时机

- S锁加锁前，先加IS锁
- X锁加锁前，先加IX锁
#### 意向锁和读写锁的兼容关系
|  | X | IX | S | IS |
| :---: | :---: | :---: | :---: | :---: |
| X | 互斥 | 互斥 | 互斥 | 互斥 |
| IX | 互斥 | 兼容 | 互斥 | 兼容 |
| S | 互斥 | 互斥 | 兼容 | 兼容 |
| IS | 互斥 | 兼容 | 兼容 | 兼容 |

## 全局锁
> 对整个数据库实例加锁

```sql
flush tables with read lock  -- 加锁
unlock tables  -- 解锁
```
如果其他会话对某个表加了表锁，那么另一个会话加全局锁的请求会被阻塞；如果当前会话对某个表加了表锁，或在事务中，那么加全局锁的请求会失败： `Can't execute the given command because you have active locked tables or an active transaction`
一旦全局锁命令执行成功，会关闭当前已打开的所有表，此后，该数据库实例将会变为只读，所有对数据库的update、delete、insert、加排它锁、表结构修改等操作都会被阻塞。
当当前连接断开时，全局锁会自动解锁。
### 使用场景

- 全部备份
- 全库只读与全局锁



## 表级锁
> 对整张表加锁，开销小，加锁快，无死锁，锁粒度大，发生锁冲突的概率高，并发性低

MyIsam引擎的默认锁
读锁会阻塞写操作，不会阻塞读操作；写锁会阻塞读操作和写操作。
MyIsam的读写锁调度是写优先，这也是MyIsam不适合做写为主表的引擎，因为写锁以后，其他线程不能做任何操作，大量的更新使得查询很难得到锁，从而造成永远阻塞。


MySQL中有两种表级锁

- 表锁
- 元数据锁（MDL——meta data lock）
### 表锁
> lock tables ... read/write

- lock tables  read -- 加表级共享锁
- lock tables  write -- 加表级排它锁

通过 `unlock tables` 命令来解锁
InnoDB支持行锁，所以表锁不常被使用
### MDL锁
MDL 锁的存在是为了数据库定义语句（DDL）执行的原子性，因此不需要显式使用，他也同样分为共享锁和排它锁。
所有的增删改查操作都会在执行前加 MDL 共享锁，但如果是在事务中，操作执行后并不会立即释放锁，而是要等到事务执行结束（提交或回滚）后才会释放。
而对表结构的修改，即 alter table 语句，会自动加 MDL 排它锁，mysql 5.6 对这一流程进行了优化，这就是 Online DDL 机制，流程如下：

1. 获取 MDL 排他锁
1. 降级成 MDL 共享锁锁
1. 执行 DDL
1.  获取 MDL 排他锁
1.  释放 MDL 共享锁
1.  释放 MDL 排它锁

上面这些规则意味着，在 alter table 语句执行时，如果已有事务在执行，他将会进入阻塞，但此后，由于这次试图加 X 锁之前加了 IX 锁，所有的增删改查、事务开启操作都会被阻塞，这将会是一个非常严重的问题。因此，在执行 alter table 语句时，一定要检查是否此时表上有事务或慢查询在执行
## 行级锁
> 对一行数据加锁，开销大，加锁慢，会出现死锁，锁粒度最小，发生锁冲突概率最低，并发度最高

InnoDB引擎的默认锁
按锁定范围分类：记录锁、间隙锁、临键锁
这些行级锁的加锁是InnoDB自动进行的，可以通过某些SQL语句触发相应的加锁操作，但不能自由的实现加锁和解锁的动作。如果在事务中某些行或区间被加锁，那么只有到事务结束时（提交或回滚）才会自动进行解锁
_注：InnoDB通过MVCC实现了在可重复读隔离级别下不加锁实现快照读的机制_
以下操作会进行加锁操作：

- select ... lock in share mode
- select ... for update
- insert
- update
- delete

除了第一个是加的共享锁，其他操作都是排它锁
### 记录锁（record lock）
> 记录锁一定是作用在索引上的

记录锁就是对某行进行加锁，防止该行被其他操作修改或删除
对于不存在的记录，InnoDB同样允许对其进行加锁，存储引擎首先创建一个隐藏的聚簇索引，然后将其记录为锁定状态
```sql
select * from test where dix_field = 2;
select * from test where dix_field in (2, 3, 4);
```
如果dix_field是主键或唯一键，就会锁定对应行记录的聚簇索引或隐藏的聚簇索引
在已提交读的隔离级别下，如果通过非主键或唯一键索引，会锁定查询过程中扫描到的每条记录，但在查询完成后，会自动释放未匹配记录的锁。


### 间隙锁（gap lock）
> 间隙锁一定是开区间，比如：(3, 5)

间隙锁在本质上是不区分共享间隙锁或互斥间隙锁的，而且间隙锁是不互斥的，即两个事务可以同时持有包含共同间隙的间隙锁。这里的共同间隙包括两种场景：

1. 两个间隙锁的间隙区间完全一致
1. 一个间隙锁包含的间隙区间是另外一个间隙锁包含间隙区间的子集

间隙锁本质上是用于阻止其他事务在该间隙内插入新纪录，而自身事务是允许在该间隙插入数据的，可以有效的防止幻读。也就是间隙锁的应用场景包括并发读取、并发更新、并发删除和并发插入。


记录锁锁的是若干条纪录，间隙锁锁的是若干个索引间的间隙，每个间隙都是两端开放的区间。
在一个数据表中，以主键、唯一键为间隔存在着很多个区间，这些区间如果被加锁，就被称为“间隙锁”。
正是因为间隙锁存在，所以多个事务可以同时对同一个间隙加锁，即使他们加的都是排他锁。
**在已提交读和未提交读的隔离级别下，InnoDB会自动禁用间隙锁**
#### 特殊的间隙锁——插入意向锁
> 插入意向锁是一种特殊的间隙锁，但不同与间隙锁的是，该锁只用于并发插入操作。

如果说间隙锁是锁住一个区间，那么插入意向锁锁住的就是一个点。从这个角度来说，插入意向锁就是一种特殊的间隙锁。与间隙锁的另外一个差异：尽管插入意向锁也属于间隙锁，但两个事务不能在同一时间内一个拥有间隙锁，另一个拥有该间隙区间的插入意向锁（插入意向锁如果不在间隙锁区间是可以的）。
#### 加锁场景
通过主键或惟一键查询，但对应的记录不存在时，innodb 会创建隐藏索引，并锁定隐藏索引所在的区间。即即使没有该条数据，也会在这个区间加间隙锁。


### 临键锁（next-key lock）
> 临键锁是记录锁+间隙锁，即临键锁是一个左开右闭的区间，比如(3, 5]

InnoDB的默认事务隔离级别是RR，在这种隔离级别下，如果使用 `select ... in share model` 或者 `select ... for update` 语句，那么InnoDB会使用临键锁，因而可以防止幻读；当即使是RR隔离级别，如果使用的普通的select语句，那么InnoDB将是快照读，不会使用任何锁，因而还是无法防止幻读。
ps：假设表有一个id字段，且id是主键，存在id=1，id=2两条记录。当事务A读取id=3的时候，create_version=1，紧接着事务Binsert id = 3，同时create_verion=2，那么事务A再insert id = 3，会报id冲突的错误，因为MVCC快照读，事务A只能读取到create_version<=1版本的快照，所以事务B的操作事务A是读取不到的，所以事务A读后写的操作冲突了，就是幻读。
#### 加锁场景

- 通过对主键或惟一键进行范围查询，会加大于查询范围前开后闭最小范围的临键锁
- 通过非主键或惟一键查询，会锁定对应索引记录及其之前的间隙
- 如果没有建立索引，那么在查询过程中实际上扫描的是全表，所以最终会锁全表，不过对于 select * from test where xxx limit 1 这样的语句来说，实际扫描在首次遇到匹配行即结束，所以会锁此行前所有间隙

在章节“写（当前读）”中有详细介绍
## 死锁
### 一次封锁或两段锁
因为有大量的并发访问，为了预防死锁，一般应用中推荐使用一次封锁法，就是在方法的开始阶段，已经预先知道会用到哪些数据，然后全部锁住，在方法运行之后，再全部解锁。这种方法可以有效的避免循环死锁，但在数据库中却不适用，因为在事务开始阶段，数据库并不知道会用到哪些数据。
数据库遵循的是两段封锁协议，将事务分成两个阶段，加锁阶段和解锁阶段（所以叫两段锁）

- 加锁阶段：在该阶段可以进行加锁操作。在对任何数据进行读操作之前要申请并获得S锁（共享锁，其他事务可以继续加共享锁，但不能加排他锁），在进行写操作之前要申请并获得X锁（排他锁，其他事务不能再获得任何锁）。加锁不成功，则事务进入等待状态，知道加锁成功才继续进行。
- 解锁阶段：当事务释放了一个封锁之后，事务进入解锁阶段，在该阶段只能进行解锁操作不能再进行加锁操作。

虽然无法避免死锁，但两段锁协议可以保证事务的并发调度是串行化
### 概念
在并发系统中，不同线程出现对竞争资源的循环依赖并阻塞相互等待就会发生死锁
### 如何避免死锁

- 设置超时

设置锁等待事是最简单粗暴的办法，InnoDB提供了加锁阻塞超时时间的设置： `innodb_lock_wait_timeout` ，默认值是50，即一个加锁请求在等待50s后会自动返回加锁失败。但是这样会存在几个问题：1、该配置项的单位是秒，因此它的最小粒度是1秒，不同的系统，1秒的超时时间过长，也有1秒的超时时间太短，难以区分是正常的锁还是发生了死锁，从而可能导致误伤。

- 主动死锁检查

innodb 提供了主动死锁检测机制，innodb 在锁冲突发生时，会扫描持有该锁或在竞争该锁的事务，判断他们之间是否有可能产生死锁，一旦发现当前事务的等待会产生死锁，那么就会立即返回错误
可以通过 innodb_deadlock_detect 设置为 on 或 off 来开启或关闭主动死锁检测机制，默认是开启状态
看上去主动死锁检测 + 业务重试可以解决所有的死锁问题了，但是这同样存在一定的问题
由于整个主动死锁检测过程需要循环遍历所有持有或等待锁的事务两两间的持有锁情况，所以这个过程的时间复杂度是 O(n^2)，在高并发的场景下，例如有 1000 个并发的线程同时更新一行，虽然他们之间并不会产生死锁，但主动死锁检测却要进行 100 万次对比，最终造成 CPU 利用率的飙高

- 拆分字段实现单条记录并发度的下降

上述主动死锁检测引起性能问题的原因主要是单条记录加锁的并发度过高，但通常，我们不能靠降低系统的并发度来避免问题的发生，但我们可以通过横向或纵向拆分数据库中的字段来实现对并发加锁的优化
例如，对于单纯用于递增记录的字段，我们可以拆分成多个字段，每次随机选取某个字段进行递增的记录
这样虽然可以有效降低单个字段上的并发度，但依赖于实际的业务，如果业务场景同时存在增减操作，那么拆分成多个字段必须要考虑是否会将某个字段减到负数等问题，在很大程度上提升了业务逻辑的复杂度
### 分析
```sql
mysql> SELECT * FROM test;
+----+------+
| id | name |
+----+------+
|  1 | 1    |
|  5 | 5    |
| 10 | 10   |
| 15 | 15   |
| 20 | 20   |
| 25 | 25   |
+----+------+
6 rows in set (0.00 sec)
```


- 场景一，隔离级别为：RR和串行化时：
  | 会话1 | 会话2 |
  | --- | --- |
  | begin; |  |
  |  | begin; |
  | select * from test where id = 12 for update；<br>先请求意向排他锁并成功获取；<br>再请求排他锁，但是因为记录不存在，所以得到的是间隙锁（10， 15） |                                                              |
  |                                                              | select * from test where id = 13 for update；<br>先请求意向排他锁并成功获取；<br>再请求排他锁，但是因为记录不存在，所以得到的是间隙锁（10， 15） |
  | insert into test(id, name) values(12, "test1")；<br>请求插入意向锁（12），因事务二已有该间隙锁，所以只能等待 |                                                              |
  | 锁等待中                                                     | insert into test(id, name) values(13, "test2")；<br>请求插入意向锁（13），因事务一已有该间隙锁，所以请求只能等待 |
  | 锁等待解除                                                   | 死锁，session2的事务被回滚 |
  上面两个并发事务一定会发生死锁（只有在这两个隔离级别下才会有间隙锁/临键锁，这是导致死锁的根本原因）


- 场景二，
  | 会话1 | 会话2 |
  | :--- | :--- |
  | begin; |  |
  |  | begin; |
  | select * from test where id = 12 for update;<br>先请求IX锁并成功获取；<br>再请求X锁，但因行记录不存在，故得到的是间隙锁（10，15） ||
  |  |select * from test where id = 16 for update；<br>先请求IX锁并成功获取；<br>再请求X锁，但因行记录不存在，故得到的是间隙锁（15，20）|
  | insert into test(id, name) values(12, "test1")；<br>请求插入意向锁（12），获取成功 ||
  | commit; |insert into test(id, name) values(16, "test2")；<br>请求插入意向锁（16），获取成功|
  |  |commit;|
- read committed（读取提交内容）

在RC级别中，数据的读取是不加锁的，但是数据的写入、修改和删除是需要加锁的

| 事务1 | 事务2 |
| --- | --- |
| begin; | begin; |
| update test set name = 2 where id = 1; | update test set name = 3 where id = 1; |
| commit; |  |

为了防止并发过程中的修改冲突，事务1中MySQL给id=1的数据行加锁，并一直不commit，那么事务2也就一直拿不到该行的锁，一直wait到超时。
如果name没有索引，那么 `update test set id = 2 where name = 1` ，MySQL会把整张表的所有数据行加行锁，在运行过程中，MySQL并不知道哪些数据行是 `name = 1` 的（没有索引），如果一个条件无法通过索引快速过滤，存储引擎层面就会将所有记录加锁后返回，再由MySQL Server层进行过滤。
但是在实际使用过程中，MySQL做了一些优化，在MySQL Server过滤条件，发现不满足后，会调用 `unlock_row` 方法，把不满足条件的记录释放锁（违背了二段锁协议）。这样做，保证了最后只会持有满足条件记录上的锁，但是每条记录的加锁操作还是不能省略的。


## 悲观锁和乐观锁

- 悲观锁

正如其名，它指的是对数据被外界（包括本系统当前的其他事务，以及来自外部系统的事务处理）修改持保守态度，因此，在整个数据处理过程中，将数据处于锁定状态。悲观锁的实现，往往依靠数据库提供的锁机制（也只有数据库层提供的锁机制才能真正保证数据访问的排他性，否则，即使在本系统中实现了加锁机制，也无法保证外部系统不会修改数据）。
在悲观锁的情况下，为了保证事务的隔离性，就需要一致性锁定读。读取数据时给加锁，其它事务无法修改这些数据。修改删除数据时也要加锁，其它事务无法读取这些数据。
_个人理解：悲观锁认为在读取数据时，一定会有其他事务来修改数据，所以会在读取的时候就将数据加锁_

- 乐观锁

相对悲观锁而言，乐观锁机制采取了更加宽松的加锁机制。悲观锁大多数情况下依靠数据库的锁机制实现，以保证操作最大程度的独占性。但随之而来的就是数据库性能的大量开销，特别是对长事务而言，这样的开销往往无法承受。
而乐观锁机制在一定程度上解决了这个问题。乐观锁，大多是基于数据版本（ Version ）记录机制实现。何谓数据版本？即为数据增加一个版本标识，在基于数据库表的版本解决方案中，一般是通过为数据库表增加一个 “version” 字段来实现。读取出数据时，将此版本号一同读出，之后更新时，对此版本号加一。此时，将提交数据的版本数据与数据库表对应记录的当前版本信息进行比对，如果提交的数据版本号大于数据库表当前版本号，则予以更新，否则认为是过期数据。
_个人理解：乐观锁认为读取的数据没有其他事务会来修改_
# MVCC
在InnoDB中，会在每行数据后添加两个额外的隐藏的值来实现MVCC，这两个值**一个记录这行数据何时被创建**，另外一个记录**这行数据何时过期（或者被删除）**。 在实际操作中，存储的并不是时间，而是事务的版本号，每开启一个新事务，事务的版本号就会递增。 在可重读Repeatable reads事务隔离级别下：

- SELECT时，读取创建版本号<=当前事务版本号，删除版本号为空或>当前事务版本号。
- INSERT时，保存当前事务版本号为行的创建版本号
- DELETE时，保存当前事务版本号为行的删除版本号
- UPDATE时，插入一条新纪录，保存当前事务版本号为行创建版本号，同时保存当前事务版本号到原来删除的行

通过MVCC，虽然每行记录都需要额外的存储空间，更多的行检查工作以及一些额外的维护工作，但可以减少锁的使用，大多数读操作都不用加锁，读数据操作很简单，性能很好，并且也能保证只会读取到符合标准的行，也只锁住必要行。

我们不管从数据库方面的教课书中学到，还是从网络上看到，大都是上文中事务的四种隔离级别这一模块列出的意思，RR级别是可重复读的，但无法解决幻读，而只有在Serializable级别才能解决幻读。于是我就加了一个事务C来展示效果。在事务C中添加了一条teacher_id=1的数据commit，RR级别中应该会有幻读现象，事务A在查询teacher_id=1的数据时会读到事务C新加的数据。但是测试后发现，在MySQL中是不存在这种情况的，在事务C提交后，事务A还是不会读到这条数据。可见在MySQL的RR级别中，是解决了幻读的读问题的。参见下图
![image.png](https://cdn.jsdelivr.net/gh/mrlsss/images@main/SQL/MySQL事务和锁总结/MySQL事务总结-3.png)

### 快照读和当前读
我们且看，在RR级别中，通过MVCC机制，虽然让数据变得可重复读，但我们读到的数据可能是历史数据，是不及时的数据，不是数据库当前的数据！这在一些对于数据的时效特别敏感的业务中，就很可能出问题。
对于这种读取历史数据的方式，我们叫它**快照读 (snapshot read)**，而读取数据库当前版本数据的方式，叫**当前读 (current read)**。很显然，在MVCC中：

- 快照读：就是select
  - select * from table ….;
- 当前读：特殊的读操作，插入/更新/删除操作，属于当前读，处理的都是当前的数据，需要加锁。
  - select * from table where ? lock in share mode;
  - select * from table where ? for update;
  - insert;
  - update ;
  - delete;

事务的隔离级别实际上都是定义了当前读的级别，MySQL为了减少锁处理（包括等待其它锁）的时间，提升并发能力，引入了快照读的概念，使得select不用加锁。而update、insert这些“当前读”，就需要另外的模块来解决了
### 写（当前读）
事务的隔离级别中虽然只定义了读数据的要求，实际上这也可以说是写数据的要求。上文的“读”，实际是讲的快照读；而这里说的“写”就是当前读了。
为了解决当前读中的幻读问题，MySQL事务使用了临键锁（Next-Key锁）。
#### 示例：
RC级别：

| 事务A | 事务B |
| --- | --- |
| begin; | begin; |
| select id,class_name,teacher_id from class_teacher where teacher_id=30;<br>+--+---------+---------+<br>/ id / class_name / teacher_id /<br>+--+---------+---------+<br>/  2 / 初三二班     / 30            /<br>+--+---------+---------+ |  |
| update class_teacher set class_name='初三四班' where teacher_id=30; |  |
|  | insert into class_teacher values (null,'初三二班',30);<br>commit; |
| select id,class_name,teacher_id from class_teacher where teacher_id=30;<br>+--+---------+---------+<br>/ id / class_name / teacher_id /<br>+--+---------+---------+<br>/  2  / 初三四班    / 30            /<br>/  10 / 初三二班    / 30            /<br>+--+---------+---------+ |                                                              |
RR级别：

| 事务A | 事务B |
| --- | --- |
| begin; | begin; |
| select id,class_name,teacher_id from class_teacher where teacher_id=30;<br>+--+---------+---------+<br>/ id / class_name / teacher_id /<br>+--+---------+---------+<br>/  2 / 初三二班     / 30             /<br>+--+---------+---------+ |  |
| update class_teacher set class_name='初三四班' where teacher_id=30; |  |
|  | insert into class_teacher values (null,'初三二班',30);<br/>waiting.... |
| select id,class_name,teacher_id from class_teacher where teacher_id=30;<br>+--+---------+---------+<br>/ id / class_name / teacher_id /<br>+--+---------+---------+<br>/  2 / 初三四班     / 30             /<br>+--+---------+---------+ |  |
| commit; | 事务Acommit后，事务B的insert执行 |
通过对比我们可以发现，在RC级别中，事务A修改了所有teacher_id=30的数据，但是当事务Binsert进新数据后，事务A发现莫名其妙多了一行teacher_id=30的数据，而且没有被之前的update语句所修改，这就是“当前读”的幻读。
RR级别中，事务A在update后加锁，事务B无法插入新数据，这样事务A在update前后读的数据保持一致，避免了幻读。这个锁，就是Gap锁。
MySQL是这么实现的：
在class_teacher这张表中，teacher_id是个索引，那么它就会维护一套B+树的数据关系，为了简化，我们用链表结构来表达（实际上是个树形结构，但原理相同）
![image.png](https://cdn.jsdelivr.net/gh/mrlsss/images@main/SQL/MySQL事务和锁总结/MySQL事务总结-4.png)
如图所示，InnoDB使用的是聚集索引，teacher_id身为二级索引，就要维护一个索引字段和主键id的树状结构（这里用链表形式表现），并保持顺序排列。
Innodb将这段数据分成几个个区间

- (negative infinity, 5],
- (5,30],
- (30,positive infinity)；

update class_teacher set class_name=‘初三四班’ where teacher_id=30;不仅用行锁，锁住了相应的数据行；同时也在两边的区间，（5,30]和（30，positive infinity），都加入了gap锁。这样事务B就无法在这个两个区间insert进新数据。


行锁防止别的事务修改或删除，间隙锁锁防止别的事务新增，行锁和间隙锁锁结合形成的临键锁共同解决了RR级别在**写数据时的幻读问题**。（写数据时会加锁，但是普通的select是快照读，不会加锁，RR级别下的快照读还是会存在幻读问题）。

