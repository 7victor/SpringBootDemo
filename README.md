CSDN BLOG->https://blog.csdn.net/qq_15396517/article/details/82419159
目录



构建前期准备

创建工程

第一个RestController

配置文件详解

自定义属性

将配置文件的属性赋给实体类

自定义配置文件

多个环境下的配置文件

Spring Boot 整合JPA

Spring Boot整合Redis

Spring Boot整合Swagger2,搭建Restful API在线文档

构建前期准备
开发前需要确保电脑安装了JDK、MAVEN工具、IDEA。

配置IDEA默认JDK版本、MAVEN路径

configure->Project Defaults->Project Structure->Project SDK 选择1.8

configure->Project Defaults->Settings->搜索maven配置Maven home directory和user settings file

创建工程
打开IDEA->Create New Project->Spring Initializr->填写group和artifact.在Dependencies搜索想要的功能，如web勾选，选择Spring Boot 版本







等待maven下载完依赖内容

可以看到在pom.xml中已经自动将spring-boot-starter-web加入，并且主目录下有SpringBootDemoApplication，@SpringBootDemoApplication注解包含了@SpringBootConfiguration、@ENableAuto-Configuration和@ComponentScan，包扫描、配置和自动配置的功能。

第一个RestController
上一步创建完成后会生成已经是一个简单的web框架了，此时创建一个HelloController代码如下

/**
 * @author Victor
 * @ClassName: HelloController
 * @Description: 第一个Controller
 * @date 2018/9/5 14:26
 */
@RestController
public class HelloController {
    /**
     * rest请求返回hello
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "hello Spring Boot!";
    }
}
运行SpringBootDemoApplication.java的main方法，Spring Boot程序就会被启动，控制台会打印对应信息，默认启动端口是8080。启动完成。

浏览器访问http://localhost:8080/hello则输出hello Spring Boot!   测试成功。

配置文件详解
使用IDEA创建Spring Boot工程时候，会默认在src/main/java/resources目录下创建application.properties文件。Spring Boot也支持yml格式的application.yml

自定义属性
在application.yml中自定义属性如：

my:
  name: victor
  age: 20
新增一个MyController.java

/**
 * @author Victor
 * @ClassName: MyController
 * @Description: 测试自定义属性
 * @date 2018/9/5 14:45
 */
@RestController
public class MyController {
    //@Value从配置文件中读取配置属性
    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private String age;
    
    @RequestMapping("/my")
    public String my(){
        return "name:"+name+"  age:"+age;
    }
}
启动程序，访问http://localhost:8080/my  输出 name:victor age:20  测试成功。

将配置文件的属性赋给实体类
修改上一个配置文件中内容，创建实体类MyBean.java,如下

my:
  name: victor
  age: 20
  number: ${random.int}
  uuid: ${random.uuid}
  max: ${random.int(10)}
  value: ${random.value}
  greeting: hi,i'm ${my.name}
/**
 * @author Victor
 * @ClassName: MyBean
 * @Description: My实体类
 * @date 2018/9/5 14:56
 */
//ConfigurationProperties注解表明该类是配置属性类，加上prefix属性前缀
@ConfigurationProperties(prefix = "my")
//Component注解会在Spring Boot启动的时候通过包臊面将该类作为Bean注入到IOC容器中
@Component
public class MyBean {
    private String name;
    private int age;
    private int number;
    private String uuid;
    private int max;
    private String value;
    private String greeting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                ", uuid='" + uuid + '\'' +
                ", max=" + max +
                ", value='" + value + '\'' +
                ", greeting='" + greeting + '\'' +
                '}';
    }
}
在MyController中加入下面内容

    @Autowired
    private MyBean myBean;

    @RequestMapping("/bean")
    public String bean(){
        return myBean.toString();
    }
访问http://localhost:8080/bean 结果MyBean{name='victor', age=20, number=1175939543, uuid='f72e0dee-58be-4f29-a8fa-a70495895705', max=7, value='57f17e4309b9d373a1980328abcb2722', greeting='hi,i'm victor'}，测试成功

自定义配置文件
如果把配置属性写到application.yml配置文件中如果属性太多会造成文件太长找半天属性，所以可以在src/main/resources目录下自定义创建一个test.properties配置文件。配置信息如下：

you.name=feng
you.age=18
新建UserVo类

/**
 * @author Victor
 * @ClassName: UserVo
 * @Description: 配置文件中获取
 * @date 2018/9/5 15:31
 */
@Configuration
//PropertySource指定扫描的配置文件
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "you")
public class UserVo {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
MyController类中加入

    @Autowired
    private UserVo userVo;
    @RequestMapping("/you")
    public String you(){
        return "name:"+userVo.getName()+"  age:"+userVo.getAge();
    }
启动后访问http://localhost:8080/you 结果name:feng age:18 测试成功

多个环境下的配置文件
实际开发过程中可能有多个环境的配置文件，比如开发环境，测试环境和生产黄精等。Spring Boot支持程序在启动时在application.yml中指定环境的配置文件，配置文件的格式是application-{profile}.yml 或者 application-{profile}.properties，例如：

application-test.yml（测试环境）、application-dev.yml（开发环境）、application-prod.yml（生产环境）

指定使用哪个配置文件只需要在application.yml中加上

spring:
  profiles:
    active: dev
然后再application-dev.yml文件中配置对应信息，如

server:
  port: 8089
此时启动后启动端口就会被改为8089了。

另外如果用java -jar方式启动程序并指定程序的配置文件也是可以的。如：

java -jar SpringBootDemo.jar --spring.profiles.active=dev

Spring Boot 整合JPA
JPA是一个数据持久化的类和方法的集合。目前在Java项目中提到的JPA一般指Hibernate的实现。下面是JPA的实现

开发前需要确保有mysql数据库

在pom.xml中引入jpa、mysql驱动

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
在application.yml中配置数据源

spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/boot?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8
    username: root
    password: 123456
  jpa:
    hibernate:
      ddl-auto: create  #第一次建表用create ,后面用update
    show-sql: true

创建实体对象

//Entity注解表明该类是一个实体类，与数据库的表名相对应
@Entity
public class UserEntity {
    //Id表示变量对应数据库中的id GeneratedValue配置自增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Column对应数据库字段 nullable非空 unique唯一约束
    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
创建Dao层

public interface IUserDao extends JpaRepository<UserEntity,Long>{
    UserEntity findUserEntitiesByUserName(String userName);
}
创建Service层

@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    public UserEntity getUser(String userName) {
        return userDao.findUserEntitiesByUserName(userName);
    }
}
创建Controller层，修改之前的Controller层新加方法与参数

    @Autowired
    private UserService userService;
    @RequestMapping("/findUser")
    public UserEntity findUser(String userName) {
        return userService.getUser(userName);
    }
启动程序后查看日志有创表内容

Hibernate: drop table if exists user_entity
Hibernate: create table user_entity (id bigint not null auto_increment, pass_word varchar(255), user_name varchar(255) not null, primary key (id)) engine=MyISAM
Hibernate: alter table user_entity add constraint UK_9chx1dmnxuaapik68vwo6gvo7 unique (user_name)
手动到数据库中添加数据，打开浏览器输入http://localhost:8080/findUser?userName=victor 出现{"id":1,"userName":"victor","passWord":"123456"} 测试成功

Spring Boot整合Redis
Redis是一个开源的、先进的key-value的存储系统，是一种NoSQL（菲关系型的数据库），读写快速。

开发前需要确保有redis数据库

在pom.xml中加入redis依赖

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
application.yml中配置redis数据库的配置

spring:
    redis:
        host: localhost  
        port: 6379
        password: 
        database: 1   #选择了序号为1的库
数据操作层RedisDao

//通过Repository注入到IOC容器中
@Repository
public class RedisDao {
    //通过StringRedisTemplate访问数据库的字符串类型
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key,String value){
        ValueOperations<String,String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
    }

    public String getValue(String key){
        ValueOperations<String,String> ops = template.opsForValue();
        return ops.get(key);
    }
}
调用RedisDao就可以操作数据库，如：

redisDao.setKey("name","victor");
redisDao.setKey("age","20");
redisDao.getValue("name");
redisDao.getValue("age");
经过测试，测试通过。

Spring Boot整合Swagger2,搭建Restful API在线文档
首先引入Swagger2依赖

<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.6.1</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.6.1</version>
</dependency>
配置Swagger2，新建Swagger2类

/**
 * @author Victor
 * @ClassName: Swagger2
 * @Description: Swagger2在线API文档框架配置
 * @date 2018/9/5 17:48
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SpringBoot利用Swagger2构建api文档")
                .description("简单优雅的restfun风格,https://blog.csdn.net/qq_15396517")
                .termsOfServiceUrl("https://blog.csdn.net/qq_15396517")
                .version("1.0")
                .build();
    }
}
Service层，Dao层照常编写，controller层在com.example.demo.controller包路径下就会被自动扫描到Swagger2的API中，有需要的话手动配置写信息，比如API方法的名称，notes等只要在方法前加上@ApiOperation注解，如：

    @ApiOperation(value = "通过名称查找用户",notes="用户列表")
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    public UserEntity findUser(String userName) {
        return userService.getUser(userName);
    }
访问http://localhost:8080/swagger-ui.html 出现下面内容



