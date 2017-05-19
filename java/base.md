# 拉姆的表达式 
    
    /***
     * 包含三个部分
     * 1.一个括号内用哪个逗号分隔的形式参数，参数是函数式接口里面方法的参数
     * 2. 一个箭头符号：->
     * 3.方法体：可以是表达式和代码块，方法体函数式接口里面方法的实现，如果是代码块，则必须用{} 包裹起来，
     * 且需要一个return，但也有例外，若函数式接口里面方法返回值是 void，则无需{}
     * */
    public static void Lam() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("效果一样");
            }
        }).start();
	}

    public static void runableLambdaTest() {
        new Thread(()->{System.out.println("222");}).start();
    }