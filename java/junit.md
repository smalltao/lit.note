# 单元测试
## @Rule 
> 利用@Rule我们可以扩展JUnit的功能，在执行case的时候加入测试者特有的操作，而不影响原有的case代码：减小了特有操作和case原逻辑的耦合。

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testDetect_WrongKey() throws Exception {
        Detect.setKey("wrong_key");
        Detect.setClientId(null);
        exception.expect(RuntimeException.class); //期待的异常是
        exception.expectMessage("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
        Detect.execute("? ?? ??? ?????");
    }

## @Before
> 在每个测试方法之前都会运行一次，只需声明成 public

    Properties p;
    @Before
    public void setUp() throws Exception {
        p = new Properties();
        URL url = ClassLoader.getSystemResource("META-INF/config.properties");
        p.load(url.openStream());
        String apiKey = p.getProperty("microsoft.translator.api.key");
        if(System.getProperty("test.api.key")!=null) {
            apiKey = System.getProperty("test.api.key").split(",")[0];
        }
        String clientId = p.getProperty("microsoft.translator.api.clientId");
        if(System.getProperty("test.api.key")!=null) {
            clientId = System.getProperty("test.api.key").split(",")[1];
        }
        String clientSecret = p.getProperty("microsoft.translator.api.clientSecret");
        if(System.getProperty("test.api.key")!=null) {
            clientSecret = System.getProperty("test.api.key").split(",")[2];
        }
        Detect.setClientId(clientId);
        Detect.setClientSecret(clientSecret);
        Detect.setKey(apiKey);
    }

## @BeforeClass
> 在类中只运行一次，必须声明成public static

## @After
> 在每个测试方法之后都会执行一次

    @After
    public void tearDown() throws Exception {
    
    }
    
## @Test
> 单元测试方法 没有返回值

    public void testLargeTooLarge() throws Exception {
        
    }

# 断言

