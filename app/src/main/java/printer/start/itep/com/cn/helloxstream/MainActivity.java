package printer.start.itep.com.cn.helloxstream;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import printer.start.itep.com.cn.helloxstream.Business.BMXX;
import printer.start.itep.com.cn.helloxstream.Business.Body;
import printer.start.itep.com.cn.helloxstream.Business.Business;
import printer.start.itep.com.cn.helloxstream.Business.YHZC;
import printer.start.itep.com.cn.helloxstream.Business.ZZSYHZC;
import printer.start.itep.com.cn.helloxstream.DataBase.DBManager;
import printer.start.itep.com.cn.helloxstream.DataBase.SPBMDBHelper;
import printer.start.itep.com.cn.helloxstream.Fpmb.FpmbParse;
import printer.start.itep.com.cn.helloxstream.Fpmb.FpmbParse;

public class MainActivity extends AppCompatActivity {

    private DBManager mDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDBManager = new DBManager(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] contents = new String[20];
                for (int i = 0; i < contents.length; ++i){
                    contents[i] = "这是测试内容";
                }

                List<Sfmx> list = new ArrayList<>();
                for (int i = 0; i < 7; ++i){
                    Sfmx sfmx1 = new Sfmx();
                    sfmx1.setDj("11");
                    sfmx1.setXm("这是测试。");
                    sfmx1.setJe("22");
                    sfmx1.setSl("33");

                    list.add(sfmx1);
                }

                Sfmx sfmx = new Sfmx();
                sfmx.setDj("11");
                sfmx.setXm("这是测试。Test,这是测试,这是测试。Test,这是测试");
                sfmx.setJe("22");
                sfmx.setSl("33");

                list.add(sfmx);

                byte[] printData = FpmbParse.GetPrinterData(MainActivity.this, "通用", contents, list);
                File file = new File("/storage/sdcard0/printData.prn");
                try {
                    OutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(printData);
                    outputStream.flush();
                    outputStream.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
//                TestXStream();
            }
        });
    }

    public static class Sfmx{
        private String Xm;
        private String Dj;
        private String Sl;
        private String Je;

        public String getXm() {
            return Xm;
        }

        public void setXm(String xm) {
            Xm = xm;
        }

        public String getDj() {
            return Dj;
        }

        public void setDj(String dj) {
            Dj = dj;
        }

        public String getSl() {
            return Sl;
        }

        public void setSl(String sl) {
            Sl = sl;
        }

        public String getJe() {
            return Je;
        }

        public void setJe(String je) {
            Je = je;
        }
    }

//    /**
//     * @brief 通过模板获取打印数据
//     * @param area 地区
//     * @param contents 打印字符串内容
//     * @param listSfmx 打印项目
//     * @return 返回可打印字符串内容
//     */
//    public byte[] GetPrinterData(String area, String[] contents, List<Object> listSfmx){
//        byte[] printData = null;
//
//        try{
//            AssetManager am = getAssets();
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            //解析XML模板内容
//            Document document = builder.parse(am.open("fpmb.xml"));
//            Element rootElement = document.getDocumentElement();
//            //获取根节点内容
//            NodeList items = rootElement.getElementsByTagName("fpmb");
//            for (int i = 0; i < items.getLength(); ++i){
//                Node fpmb = items.item(i);
//
//                if (fpmb.hasAttributes()){
//                    NamedNodeMap attribute = fpmb.getAttributes();
//                    //获取地区名字
//                    Node invoice = attribute.getNamedItem("InvoiceName");
//                    if (invoice != null){
//                        //寻找相应地区的模板
//                        if (invoice.getNodeValue().equals(area)){
//                            //获取子节点
//                            NodeList childs = fpmb.getChildNodes();
//
//                            List<Byte> listprintData = new ArrayList<>();
//                            //解析模板获取返回可打印字节链表
//                            while (listSfmx.size() > 0) {
//                                listprintData.addAll(ParseFPMB(childs, contents, listSfmx));
//                            }
//
//                            //将链表数据转换为byte字节数组
//                            printData = new byte[listprintData.size()];
//                            for (int j = 0; j < listprintData.size(); ++j){
//                                printData[j] = listprintData.get(j);
//                            }
//
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return printData;
//    }
//
//    protected void ParseType(int type, String XmlValue, String[] contents, Object object, List<Byte> printData) throws Exception{
//        XmlValue = XmlValue.replaceAll("\r|\n|\t", "");
//        switch (type) {
//            case 1:{
//                byte[] byteContent = XmlValue.getBytes("gb18030");
//                for (int j = 0; j < byteContent.length; ++j){
//                    printData.add(byteContent[j]);
//                }
//            }
//            break;
//
//            case 2: {
//                String content = contents[Integer.parseInt(XmlValue)];
//                byte[] byteContent = content.getBytes("gb18030");
//                for (int j = 0; j < byteContent.length; ++j){
//                    printData.add(byteContent[j]);
//                }
//            }
//            break;
//
//            case 3: {
//                String[] commands = XmlValue.split(" ");
//                for (int j = 0; j < commands.length; ++j) {
//                    if (commands[j].trim().length() > 1) {
//                        byte command = (byte)Integer.parseInt(commands[j].trim().toLowerCase(), 16);
//                        printData.add(command);
//                    }
//                }
//            }
//            break;
//
//            case 4: {
//                Object result = callMethod(XmlValue, object, null);
//                if (result != null && result instanceof String){
//                    byte[] byteContent = ((String)result).getBytes("gb18030");
//                    for (int n = 0, m = 0; n < byteContent.length; ++n, ++m){
//                        printData.add(byteContent[n]);
//
////                        if (m >= 13){
////                            printData.add((byte)0x0d);
////                            printData.add((byte)0x0a);
////                            m = 0;
////                        }
//                    }
//                }
//            }
//            break;
//        }
//    }
//
//    /**
//     * @brief 解析对应发票模板
//     * @param nodeList 发票模板的节点链表
//     * @param contents 可打印字符串内容
//     * @param listSfmx 打印项目链表
//     * @return 返回可打印字节链表
//     */
//    public List<Byte> ParseFPMB(NodeList nodeList, String[] contents, List<Object> listSfmx){
//        List<Byte> printData = new ArrayList<>();
//        Object object = null;
//        try {
//            //从链表的第一个节点开始解析
//            for (int i = 0; i < nodeList.getLength(); ++i) {
//                //获取第一个节点（获取的节点并非一定是第一个有效节点，注释节点也会被获取）
//                Node child = nodeList.item(i);
//                //判断当前结点是否有属性
//                if (child.hasAttributes()) {
//                    NamedNodeMap attributes = child.getAttributes();
//                    //获取type属性节点
//                    Node type = attributes.getNamedItem("type");
//
//                    if (type != null) {
//                        //去除制表符以及空格
//                        String value = child.getFirstChild().getNodeValue().replaceAll("\r|\n|\t", "");
//                        ;
//
//                        //使用通用解析函数进行当前结点的解析
//                        ParseType(Integer.parseInt(type.getNodeValue()), value, contents, object, printData);
//
//                        continue;
////                        switch (Integer.parseInt(type.getNodeValue())) {
////                            case 1:{
////                                byte[] byteContent = value.getBytes("gb18030");
////                                for (int j = 0; j < byteContent.length; ++j){
////                                    printData.add(byteContent[j]);
////                                }
////                            }
////                                break;
////
////                            case 2: {
////                                String content = contents[Integer.parseInt(value)];
////                                byte[] byteContent = content.getBytes("gb18030");
////                                for (int j = 0; j < byteContent.length; ++j){
////                                    printData.add(byteContent[j]);
////                                }
////                            }
////                            break;
////
////                            case 3: {
////                                String[] commands = value.split(" ");
////                                for (int j = 0; j < commands.length; ++j) {
////                                    byte command = Byte.parseByte(commands[j].trim(), 16);
////                                    printData.add(command);
////                                }
////                            }
////                            break;
////
////                            case 4: {
////                                Object result = callMethod(value, object, null);
////                                if (result != null && result instanceof String){
////                                    byte[] byteContent = ((String)result).getBytes("gb18030");
////                                    for (int n = 0; n < byteContent.length; ++n){
////                                        printData.add(byteContent[n]);
////                                    }
////                                }
////                            }
////                            break;
////                        }
//                    }
//
//                    //判断是否有属性MAX
//                    Node max = attributes.getNamedItem("max");
//                    if (max != null) {
//                        //获取具备max属性节点的所有子节点
//                        NodeList subChilds = child.getChildNodes();
//
//                        //获取max属性的值并转换为Int类型
//                        int maxValue = Integer.parseInt(max.getNodeValue().trim());
//
//                        //循环判断，条件是不得超过预设值的最大值以及可打印链表的最大尺寸
//                        int enter = 0;
//                        int j = 0;
//                        for (; enter < maxValue && j < listSfmx.size(); ++j) {
//                            //获取第一个可打印链表的对象
//                            object = listSfmx.get(j);
//
//                            //声明存储当前可打印项目行的Pair链表
//                            //Pair first:表示打印的内容类型
//                            //Pair secong:表示打印的内容
//                            List<Pair<Integer, String>> printString = new ArrayList<>();
//                            //开始循环获取项目模板
//                            for (int m = 0; m < subChilds.getLength(); ++m) {
//                                //获取当前结点
//                                Node subChild = subChilds.item(m);
//                                //判断是否具备属性
//                                if (subChild.hasAttributes()) {
//                                    NamedNodeMap subAttributes = subChild.getAttributes();
//                                    //获取type属性，并清楚制表符以及空格换行符
//                                    Node subType = subAttributes.getNamedItem("type");
//                                    String value = subChild.getFirstChild().getNodeValue().replaceAll("\r|\n|\t", "");
//
//                                    //判断是否具备type属性
//                                    if (subType != null) {
////                                        ParseType(Integer.parseInt(subType.getNodeValue()), value, contents, object, printData);
//                                        switch (Integer.parseInt(subType.getNodeValue())) {
//                                            //如果是类型3，当前的模板节点是打印指令，存储在printString中
//                                            case 3: {
////                                                String[] commands = value.split(" ");
////                                                for (int n = 0; n < commands.length; ++n) {
////                                                    byte command = Byte.parseByte(commands[n].trim(), 16);
////                                                    printData.add(command);
////                                                }
//                                                printString.add(new Pair<Integer, String>(3, value));
//                                            }
//                                            break;
//
//                                            //如果是类型4，表示当前的模板节点是从object对应方法中获取可打印数据
//                                            case 4: {
//                                                //调用相应的方法获取可打印结果
//                                                Object result = callMethod(value, object, null);
//                                                //如果获取的结果不为空并且是字符串将其存储在printString中
//                                                if (result != null && result instanceof String) {
////                                                    byte[] byteContent = ((String)result).getBytes("gb18030");
////                                                    for (int n = 0; n < byteContent.length; ++n){
////                                                        printData.add(byteContent[n]);
////                                                    }
//                                                    printString.add(new Pair<Integer, String>(4, (String) result));
//                                                }
//                                            }
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//
//                            //设置当前可打印行是否完成标识并初始化为false
//                            boolean bcomplete = false;
//                            do {
////                                String regex = "[\u4e00-\u9fa5]";
////                                Pattern pattern = Pattern.compile(regex);
//
//                                bcomplete = false;
//
//                                //开始循环提取printString中的内容
//                                for (int m = 0; m < printString.size(); ++m) {
//                                    Pair<Integer, String> pair = printString.get(m);
//
//                                    //从pair first中判断当前的类型
//                                    switch (pair.first) {
//                                        case 3: {
//                                            //切割16进制字符串，解析为Byte类型，添加到printData链表中
//                                            String[] commands = pair.second.split(" ");
//                                            for (int n = 0; n < commands.length; ++n) {
//                                                if (commands[n].trim().length() > 1) {
//                                                    byte command = (byte) Integer.parseInt(commands[n].trim().toLowerCase(), 16);
//                                                    printData.add(command);
//                                                }
//                                            }
//                                        }
//                                        break;
//
//                                        case 4: {
//                                            //将可打印内容存储在content中
//                                            String content = pair.second;
//                                            //声明一个新的StringBuilder类型，并初始化为""
//                                            StringBuilder newContent = new StringBuilder("");
//                                            //声明一个变量k，浮点类型，用来存储当前是否超过7个中文字符（应为以办法处理）
//                                            float k = 0.0f;
//                                            //声明一个变量n，整数类型，用来存储当前循环提取第几个字符
//                                            int n = 0;
//                                            for (; n < pair.second.length(); ++n) {
//                                                //获取当前第n个字符
//                                                String aWord = content.substring(n, n + 1);
//                                                newContent.append(aWord);
//                                                /**
//                                                 * 如果是中文字符串，k加1
//                                                 * if (aWord is Chinese Character){
//                                                 *  ++k;
//                                                 *
//                                                 * }
//                                                 * 如果是ASCII，k+=0.5
//                                                 * aWord is ASCII
//                                                 * else {
//                                                 *  k += 0.5f;
//                                                 * }
//                                                 *
//                                                 * 当K超过7个字符，当前循环结束获取当n超过content长度，当前循环结束
//                                                 * if (k >= 7.0f){
//                                                 *  break;
//                                                 * }
//                                                 * */
//
////                                                Matcher matcher = pattern.matcher(aWord);
////                                                if (matcher.find()){
////                                                    k += 1;
////                                                }
////                                                else{
////                                                    k += 0.5f;
////                                                }
////
//                                                if (aWord.getBytes().length > 1) {
//                                                    k += 1;
//                                                } else {
//                                                    k += 0.5;
//                                                }
//
//                                                if (k >= 7.0f) {
//
//                                                    if (k > 7.0f) {
//                                                        newContent = newContent.delete(newContent.length() - 1, newContent.length());
//                                                        --n;
//                                                    }
//
//                                                    break;
//                                                }
//                                            }
//
//                                            //如果content长度大于N，表示当前还有字符串剩余，那么截取剩下的字符串，将其在当前位置重新存储起来
//                                            if (content.length() > n) {
//                                                printString.remove(pair);
//                                                printString.add(m, new Pair<Integer, String>(4, pair.second.substring(n + 1)));
//                                            }
//                                            //否则表示字符串已经提取完毕，那么移除当前的可打印节点
//                                            else {
//                                                printString.remove(pair);
//                                                --m;
//                                            }
//
//                                            //无论是什么结果，newContent中存储都是可打印字符串，使用gb18030编码方式提取出来存储在printDat链表中
//                                            byte[] byteContent = newContent.toString().getBytes("gb18030");
//                                            for (int z = 0; z < byteContent.length; ++z) {
//                                                printData.add(byteContent[z]);
//                                            }
//                                        }
//
////                                            if (pair.second.length() <= 7) {
////                                                byte[] byteContent = pair.second.getBytes("gb18030");
////                                                for (int n = 0; n < byteContent.length; ++n) {
////                                                    printData.add(byteContent[n]);
////                                                }
////                                                printString.remove(pair);
////                                                --m;
////                                            } else {
////                                                String part = pair.second.substring(0, 7);
////                                                printString.remove(pair);
////                                                printString.add(m, new Pair<Integer, String>(4, pair.second.substring(7)));
////                                                byte[] byteContent = part.getBytes("gb18030");
////                                                for (int z = 0; z < byteContent.length; ++z) {
////                                                    printData.add(byteContent[z]);
////                                                }
////                                            }
//                                        break;
//                                    }
//                                }
//
//                                //当前行循环结束，添加换行符
//                                printData.add((byte) 0x0d);
//                                printData.add((byte) 0x0a);
//
//                                ++enter;
//
//                                //判断是否还剩余类型4，如果还存在表示还剩余可打印内容，需要再次循环截取
//                                for (Pair<Integer, String> pair : printString) {
//                                    if (pair.first == 4) {
//                                        bcomplete = true;
//                                        break;
//                                    }
//                                }
//
//                            } while (bcomplete);
//
//
//                        }
//
//                        //如果存在空白行，需要添加换行符填充
//                        for (int n = 0, count = maxValue - enter; n < count; ++n) {
//                            printData.add((byte) 0x0d);
//                            printData.add((byte) 0x0a);
//                        }
//
//                        int count = j;
//                        while ((count--) > 0) {
//                            listSfmx.remove(0);
//                        }
//
//                        //继续提取下一个节点
//                        continue;
//                    }
//                }
//
//                //如果存在节点不符合上面两个定律，并且存在子节点，而且子节点的长度还大于0，则进行回掉方法
//                if (child.getChildNodes() != null && child.getChildNodes().getLength() > 0) {
//                    printData.addAll(ParseFPMB(child.getChildNodes(), contents, listSfmx));
//                }
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        //返回可打印聊表
//        return printData;
//    }
//
//    /**
//     * @brief 使用反射方法调用对象的成员函数
//     * @param methodName 成员函数方法名称
//     * @param o 需要被调用成员函数的对象
//     * @param params 参数
//     * @return 返回成员函数返回结果，返回null表示调用失败或者没有返回值，否则返回非null
//     */
//    public Object callMethod(String methodName, Object o, Object[] params){
//        Class[] c = null;
//        Object result = null;
//        if (params != null){
//            int len = params.length;
//            c = new Class[len];
//            for (int i = 0; i < len; ++i){
//                c[i] = params[i].getClass();
//            }
//        }
//
//        try{
//            Method method = o.getClass().getMethod(methodName, c);
////            Method method = o.getClass().getDeclaredMethod(methodName, c);
//            result = method.invoke(o, params);
//        }
//        catch (NoSuchMethodException nsm){
//            nsm.printStackTrace();
//            try {
//                Method method = o.getClass().getDeclaredMethod(methodName, c);
//                result = method.invoke(o, params);
//            }
//            catch (NoSuchMethodException snsm){
//                snsm.printStackTrace();
//            }
//            catch (Exception e){
//                e.printStackTrace();;
//            }
//        }
//        catch (InvocationTargetException ite){
//            ite.printStackTrace();
//        }
//        catch (IllegalAccessException iae){
//            iae.printStackTrace();
//        }
//
//        return result;
//    }

    public void TestXStream(){
        try {
            AssetManager am = getAssets();
//                    InputStreamReader inputStreamReader = new InputStreamReader(am.open("777.xml"));
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                    Log.d("CommonXMLParse", "ParseRunnable Read");
//                    StringBuffer xmlContent = new StringBuffer("");
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        xmlContent.append(line);
//                    }

//                    XStream stream = new XStream(new DomDriver());
//                    stream.alias("configp", ConfigParent.class);
//                    stream.alias("config", ConfigReader.class);
//                    stream.addImplicitCollection(ConfigParent.class, "config");
//                    ConfigParent configParent = (ConfigParent)stream.fromXML(am.open("777.xml"));
//                    Log.d("HelloXStream", configParent.config.get(0).datasourcename);

            XStream stream = new XStream(new DomDriver());
//                    stream.alias("business", Business.class);
//                    stream.alias("body", Body.class);
//                    stream.alias("BMXX", BMXX.class);
//                    stream.alias("YHZC", YHZC.class);
//                    stream.alias("ZZSYHZC", ZZSYHZC.class);
//                    stream.addImplicitCollection(Body.class, "BMXX");
//                    stream.addImplicitCollection(YHZC.class, "ZZSYHZC");
//                    stream.aliasAttribute(Business.class, "id", "id");
//                    stream.aliasAttribute(Business.class, "version", "version");
//                    stream.aliasAttribute(Business.class, "comment", "comment");
//                    Business business = (Business)stream.fromXML(am.open("20160501093058609.xml"));
//                    Log.d("HelloStream", business.comment);
//
//                    mDBManager.AddBMXX(business.body.BMXX);
//                    mDBManager.AddYHZC(business.body.YHZC.ZZSYHZC);

            List<BMXX> bmxxes = mDBManager.queryBMXX();
            List<ZZSYHZC> zzsyhzcs = mDBManager.queryZZSYHZC();
            Log.d("HelloStream", "bmxxes size " + bmxxes.size());
            Log.d("HelloStream", "zzsyhzcs size " + zzsyhzcs.size());

            bmxxes = mDBManager.queryBMXX(new String[]{"PID"}, new String[]{"0"});

            Log.d("HelloStream", "bmxxes SPBM = 1000000000000000000 size " + bmxxes.size());

//                    List<BMXX> treeBMXX = new ArrayList<>();
//                    for (BMXX bmxx : bmxxes){
//                        if (bmxx.PID == null || bmxx.PID.length() < 19){
//                            treeBMXX.add(bmxx);
//                        }
//                        else {
//                            boolean isFind = false;
//                            for (BMXX treeChild : treeBMXX){
//                                if (treeChild.SPBM.equals(bmxx.PID)){
//                                    treeChild.childers.add(bmxx);
//                                    isFind = true;
//                                    break;
//                                }
//                            }
//
//                            if (!isFind){
//                                for (BMXX treeChild : treeBMXX){
//                                    if (findParent(treeChild, bmxx)){
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }

//                    Log.d("HelloStream", "TreeBMXX Size " + treeBMXX.size());

            bmxxes = mDBManager.queryBMXX("10");
            Log.d("HelloStream", "10 size " + bmxxes.size());
            bmxxes = mDBManager.queryBMXX("1010");
            Log.d("HelloStream", "1010 size " + bmxxes.size());
            bmxxes = mDBManager.queryBMXX("101020");
            Log.d("HelloStream", "101020 size " + bmxxes.size());

//                    for (int i = 1; i < 9; ++i){
//                        bmxxes = mDBManager.queryBMXX(String.valueOf(i));
//                    }
//                    getBMXX(1);

            Log.d("HelloStream", "Get End");

            if (mDBManager.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "SPBM", "1000000000000000000")){
                Log.d("HelloXStream", "SPBM = 1000000000000 Exist");
            }
            else {
                Log.d("HelloXStream", "SPBM = 10000000000000 Not Exist");
            }

            Log.d("HelloStream", "Get End");

            if (mDBManager.queryBMXX(new String[]{"SPBM"}, new String[]{"1000000000000000000"}).size() > 0){
                Log.d("HelloXStream", "SPBM = 1000000000000 Exist");
            }
            else {
                Log.d("HelloXStream", "SPBM = 10000000000000 Not Exist");
            }

//                    bmxxes = mDBManager.queryBMXX();
//                    stream.alias("BMXX", BMXX.class);
//                    stream.addImplicitCollection(Body.class, "BMXX");
//                    File file = new File("/storage/sdcard0/spbm.xml");
//                    file.createNewFile();
//                    OutputStream outputStream = new FileOutputStream(file);
//                    stream.toXML(bmxxes, outputStream);

            Log.d("HelloStream", "Get End");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean findParent(BMXX bmxx, BMXX child){
        boolean isFind = false;
        for (BMXX treeChild : bmxx.childers){
            if (treeChild.SPBM.equals(child.PID)){
                treeChild.childers.add(child);
                isFind = true;
                break;
            }
        }

        if (!isFind){
            for (BMXX treeChild : bmxx.childers){
                if (findParent(treeChild, child)){
                    isFind = true;
                    break;
                }
            }
        }

        return isFind;
    }

//    public void getBMXX(int depth){
//        if (depth > 9){
//            return;
//        }
//
//        String likePath = "1";
//        for (int i = 1; i < depth; ++i){
//            likePath += "01";
//        }
//
//        List<BMXX> bmxxes = null;
//        for (int i = 1; i < 100; ++i){
//            String temp = likePath + (i < 10 ? "0" + i : i);
//            bmxxes = mDBManager.queryBMXX(temp);
//        }
//
//        getBMXX(depth++);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
