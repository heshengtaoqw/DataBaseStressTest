package mysql;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import mysql.MySQL_Jmeter_JAR;;

public class MySQL_Jmeter_Interface extends AbstractJavaSamplerClient {
    private String a;
    private String b;
    /** Holds the result data (shown as Response Data in the Tree display). */
    private String resultData;

    // 这个方法是用来自定义java方法入参的。
    // params.addArgument("num1","");表示入参名字叫num1，默认值为空。
    //设置可用参数及的默认值；
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("num1", "");
        params.addArgument("num2", "");
        return params;
    }

    //每个线程测试前执行一次，做一些初始化工作；
    public void setupTest(JavaSamplerContext arg0) {
    }

    //开始测试，从arg0参数可以获得参数值；
    public SampleResult runTest(JavaSamplerContext arg0) {
        a = arg0.getParameter("num1");
        b = arg0.getParameter("num2");
        SampleResult sr = new SampleResult();
        sr.setSampleLabel( "MySQL插入数据");
        try {
            sr.sampleStart();// jmeter 开始统计响应时间标记
            MySQL_Jmeter_JAR test = new MySQL_Jmeter_JAR();
            test.run();
            // 通过下面的操作就可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里面了。
            
            // System.out.println(resultData);
            sr.setSuccessful(true);
        } catch (Throwable e) {
            sr.setSuccessful(false);
            e.printStackTrace();
        } finally {
            sr.sampleEnd();// jmeter 结束统计响应时间标记
        }
        return sr;
    }

    //测试结束时调用；
    public void teardownTest(JavaSamplerContext arg0) {
        // System.out.println(end);
        // System.out.println("The cost is"+(end-start)/1000);
    }
    
    // main只是为了调试用，最后打jar包的时候注释掉。
    /*
      public static void main(String[] args) 
      { // TODO Auto-generated method stub
          Arguments params = new Arguments(); 
          params.addArgument("num1", "1");//设置参数，并赋予默认值1 
          params.addArgument("num2", "2");//设置参数，并赋予默认值2
          JavaSamplerContext arg0 = new JavaSamplerContext(params); 
          perftestbbb test = new perftestbbb(); 
          test.setupTest(arg0); 
          test.runTest(arg0);
          test.teardownTest(arg0); 
      }
     */
}