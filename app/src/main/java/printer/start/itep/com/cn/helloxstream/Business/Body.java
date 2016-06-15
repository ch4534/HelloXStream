package printer.start.itep.com.cn.helloxstream.Business;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by 辉 on 2016/5/24.
 */
public class Body {
    public String returnCode;
    public String returnMessage;
    public String id ="SPBM";
    public String version = "1.0";
    public String BBH="";
    public String MWJY="";
    public String COUNT="";
    public String QYSJ="";
    public String GDQJZSJ="";
    public List<BMXX> BMXX;
    public YHZC YHZC;
}
