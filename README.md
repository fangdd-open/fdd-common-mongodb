# fdd-common-mongodb

fdd-common-mongodb是对mongodb-java-driver的一层封装。主要扩展了：

1. 实现了自动识别的CodeC，让使用者可以直接通过Pojo类操作mongodb（ORM功能）

2. 支持自增ID，会自动在添加一个collection，名称为counter，用于计数

3. 实现了一些safeUpdate / getEntityById等便捷方法

4. 实现了一些属性注解，用于控制字段是否可写、可读等

除了增加的一些方法外，大部分的官方驱动都直接暴露出去，可直接调用


## 使用

1. 添加依赖
```xml
<dependency>
    <groupId>com.fangdd.traffic</groupId>
    <artifactId>common-mongodb</artifactId>
    <version>2.5.2-SNAPSHOT</version>
</dependency>
```

2. 添加配置
```
########## cp_article 连接参数 ############
#连接参数
mongodb.article.connections=[{"host":"mongodb1.tp.fdd","port":27017,"user":"test_user","password":"N0zncU#B1s8"},{"host":"mongodb2.tp.fdd","port":27017},{"host":"mongodb3.tp.fdd","port":27017}]
#每台服务器连接池连接个数
mongodb.article.connections-per-host=20
#最大等待时间(毫秒)
mongodb.article.max-wait-time=2000
```

3. Spring托管
```java
@Configuration
@ComponentScan
public class MongoDataSourceConfig {
    /**
     * article库
     *
     * @return
     */
    @Bean("articleMongoClient")
    @ConfigurationProperties("mongodb.article")
    public YMongoClient getArticleMongoClient() {
        return new YMongoClient();
    }
}
```

4. 编写库基类（推荐）
```java
@Repository
public class ArticleDao extends ArticleEntityDao<Article> {
    @Override
    protected String getCollectionName() {
        return "article";
    }
}
```

5. 使用
```java
@Autowired
private ArticleDao articleDao;

@Test
public void getById() {
    Article article = articleDao.getEntityById(423584L);
    Assert.assertEquals("测试文章", article.getTitle());
} 

```