# mybatis使用笔记
> mysql 使用动态建表语句

```
<update id="insertNewTable" flushCache="true" parameterType="com.sogou.reventon.bean.TranslateLog" statementType="STATEMENT">
    CREATE TABLE ${tableName} (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `dateId` int(11) NOT NULL,
      `requestId` varchar(255) DEFAULT NULL COMMENT '请求id',
      PRIMARY KEY (`id`,`dateId`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1205174238 DEFAULT CHARSET=utf8;
</update>

```

> 判断表是否存在
```
<select id="existTable" resultType="java.lang.Integer">
    select count(1) from information_schema.tables where TABLE_NAME = #{tableName};
</select>
```