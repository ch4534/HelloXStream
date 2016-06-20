package printer.start.itep.com.cn.helloxstream.DataBase;

import java.util.ArrayList;
import java.util.List;

import printer.start.itep.com.cn.helloxstream.Business.BMXX;
import printer.start.itep.com.cn.helloxstream.HelloXStreamApplication;
import printer.start.itep.com.cn.helloxstream.MainActivity;

/**
 * Created by admin on 2016/6/20.
 */
public class SpbmUtil {

    public List<BMXX> getAllBmxxNode(){
        DBManager dbManager = DBManager.getInstance(HelloXStreamApplication.getContext());
        List<BMXX> bmxxList = new ArrayList<>();

        for (int i = 0; i < 10; ++i){
            List<BMXX> childList = dbManager.queryBMXX(new String[]{"PID"}, new String[]{i + "000000000000000000"});
            for (int j = 0; j < childList.size(); ++j){
                BMXX bmxx = childList.get(j);
                if (dbManager.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "PID", bmxx.SPBM)){
                    getAllBmxxNode(dbManager, bmxxList, dbManager.queryBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
                }
                else{
                    bmxxList.add(bmxx);
                }
            }
        }

        return bmxxList;
    }

    private void getAllBmxxNode(DBManager dbManager, List<BMXX> bmxxList, List<BMXX> childList){
        for (int j = 0; j < childList.size(); ++j){
            BMXX bmxx = childList.get(j);
            if (dbManager.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "PID", bmxx.SPBM) && bmxx.SPBM.length() / 2 != 0){
                getAllBmxxNode(dbManager, bmxxList, dbManager.queryBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
            }
            else{
                bmxxList.add(bmxx);
            }
        }
    }
}
