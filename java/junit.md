# ��Ԫ����
## @Rule 
> ����@Rule���ǿ�����չJUnit�Ĺ��ܣ���ִ��case��ʱ�������������еĲ���������Ӱ��ԭ�е�case���룺��С�����в�����caseԭ�߼�����ϡ�

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void testDetect_WrongKey() throws Exception {
        Detect.setKey("wrong_key");
        Detect.setClientId(null);
        exception.expect(RuntimeException.class); //�ڴ����쳣��
        exception.expectMessage("INVALID_API_KEY - Please set the API Key with your Bing Developer's Key");
        Detect.execute("? ?? ??? ?????");
    }

## @Before
> ��ÿ�����Է���֮ǰ��������һ�Σ�ֻ�������� public

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
> ������ֻ����һ�Σ�����������public static

## @After
> ��ÿ�����Է���֮�󶼻�ִ��һ��

    @After
    public void tearDown() throws Exception {
    
    }
    
## @Test
> ��Ԫ���Է��� û�з���ֵ

    public void testLargeTooLarge() throws Exception {
        
    }


