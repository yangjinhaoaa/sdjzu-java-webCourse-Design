## Javaweb课程设计
## 题目：基于Java的车辆管理系统
### 课    程： Web应用开发技术
# demo2 是当前文件

## 环境 ：idea+mysql数据库
## jsp+servlet +mysql+maven搭建

- 数据库操作：1.建立数据库car_data 2.建立数据表fixed_car 3.添加数据：
  
数据库数据
| car_id    | car_color | car_owner             | car_brand | status    | intime              | outtime             | statusint |
|--|--|--|--|--|--|--|--|
|char|char|char|char|char|datetime|datatime|int|
| 鲁A09836  | 灰色      | 杜姆杜姆              | 标志      | 临时      | 2020-12-25 16:45:11 | 2020-12-25 16:45:29 |         1 |

## 参考操作
```sql
 
CREATE SCHEMA `car_data` DEFAULT CHARACTER SET utf8 ;
```




### fixed car 

```sql
CREATE TABLE `car_data`.`fixed_car` (
  `car_id` VARCHAR(9) NOT NULL,
  `car_color` VARCHAR(10) NULL,
  `id` INT NULL,
  `car_owner` VARCHAR(20) NULL,
  `car_brand` VARCHAR(45) NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE INDEX `car_id_UNIQUE` (`car_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
```

### 添加数据

```sql
INSERT INTO `car_data`.`fixed_car` (`car_id`, `car_color`, `id`, `car_owner`, `car_brand`) VALUES ('A12345', 'black', '1', '张大喵', '老死赖斯');
INSERT INTO `car_data`.`fixed_car` (`car_id`, `car_color`, `id`, `car_owner`, `car_brand`) VALUES ('A12346', 'red', '2', '李小亮', '本子');

```





UPDATE `car_data`.`fixed_car` SET `car_id` = '鲁A12345', `status` = 'old' WHERE (`car_id` = 'A12345');
UPDATE `car_data`.`fixed_car` SET `car_id` = '鲁A12346', `status` = 'new' WHERE (`car_id` = 'A12346');
INSERT INTO `car_data`.`fixed_car` (`car_id`, `car_color`, `id`, `car_owner`, `car_brand`, `status`) VALUES ('鲁A23457', 'white', '3', '王小白', '夏利', 'black');



## 加列

mysql> alter table fixed_car add column intime datetime;
Query OK, 0 rows affected (0.15 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table fixed_car add column outtime datetime;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> alter table fixed_car add column statusint int;
Query OK, 0 rows affected (0.03 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> SELECT * from fixed_car;



### 加数据

UPDATE `car_data`.`fixed_car` SET `intime` = '2020-10-11 20:00:00', `outtime` = '2020-11-12 22:22:11', `statusint` = '1' WHERE (`car_id` = '鲁A12345');
UPDATE `car_data`.`fixed_car` SET `intime` = '2020-11-12 22:20:11', `outtime` = '2020-11-12 22:22:14', `statusint` = '2' WHERE (`car_id` = '鲁A12346');
UPDATE `car_data`.`fixed_car` SET `intime` = '2020-11-12 13:22:11', `outtime` = '2020-11-12 20:22:11', `statusint` = '3' WHERE (`car_id` = '鲁A23457');


```sql
INSERT INTO `car_data`.`fixed_car` (`car_id`, `car_color`, `id`, `car_owner`, `car_brand`, `status`, `intime`, `outtime`, `statusint`) VALUES ('鲁L04029', 'red', '4', '杨', '特斯拉', '固定', '2020-11-10 12:10:10', '2020-11-10 13:10:10', '1');
```
