java 工具
1、jps

    jps 查找所有的java程序，并且仅查找当前用户的java进程，而不是当前系统的java进程
    位置:JAVA_HOME/bin
    功能：显示当前所有java进程pid的命令
    地址：http://www.hollischuang.com/archives/105
    
    参数：
    -q 只显示pid，不显示class名称，jar文件名和传递的main方法的参数
    -m 输出传递给main方法的参数，在嵌入式jvm上可能是null
    -l 输出应用程序main class的完整package名，或者，应用程序的jar文件完整路径名
    -v 输出传递jvm参数
    
2、jstack

    jstack 是java虚拟机自带的堆栈跟踪工具 
    功能：jstack用于生成java虚拟机当前时刻的线程快照。线程快照是当前java虚拟机内每一条线程正在执行的方法堆栈的集合，生成线程快照的主要目的是定位线程出现长时间停顿的原因，如线程间死锁、死循环、请求外部资源导致的长时间等待等。 线程出现停顿的时候通过jstack来查看各个线程的调用堆栈，就可以知道没有响应的线程到底在后台做什么事情，或者等待什么资源。 如果java程序崩溃生成core文件，jstack工具可以用来获得core文件的java stack和native stack的信息，从而可以轻松地知道java程序是如何崩溃和在程序何处发生问题。另外，jstack工具还可以附属到正在运行的java程序中，看到当时运行的java程序的java stack和native stack的信息, 如果现在运行的java程序呈现hung的状态，jstack是非常有用的。
    地址：http://www.hollischuang.com/archives/110
    
    
    
    