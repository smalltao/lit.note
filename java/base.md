# ��ķ�ı��ʽ 
    
    /***
     * ������������
     * 1.һ�����������ĸ����ŷָ�����ʽ�����������Ǻ���ʽ�ӿ����淽���Ĳ���
     * 2. һ����ͷ���ţ�->
     * 3.�����壺�����Ǳ��ʽ�ʹ���飬�����庯��ʽ�ӿ����淽����ʵ�֣�����Ǵ���飬�������{} ����������
     * ����Ҫһ��return����Ҳ�����⣬������ʽ�ӿ����淽������ֵ�� void��������{}
     * */
    public static void Lam() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Ч��һ��");
            }
        }).start();
	}

    public static void runableLambdaTest() {
        new Thread(()->{System.out.println("222");}).start();
    }