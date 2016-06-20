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

    public void getAllBmxxNode(List<BMXX> spbmList, List<BMXX> zxbmList){
        if (spbmList == null || zxbmList == null){
            throw new NullPointerException("参数不能为空");
        }
        DBManager dbManager = DBManager.getInstance(HelloXStreamApplication.getContext());
        List<BMXX> allBmxxNode = new ArrayList<>();

        for (int i = 0; i < 10; ++i){
            List<BMXX> bmxxList = dbManager.queryBMXX(new String[]{"PID"}, new String[]{i + "000000000000000000"});
            for (int j = 0; j < bmxxList.size(); ++j){
                BMXX bmxx = bmxxList.get(j);
//                BMXX child = dbManager.queryOneBMXX("PID", bmxx.SPBM);
//                if (child == null){
//                    child = dbManager.queryOneCusBMXX("PID", bmxx.ZXBM);
//                }

                if (dbManager.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "PID", bmxx.SPBM)){
//                    if (bmxx.SPBM.length() / 2 != 0 && child.SPBM.length() / 2 == 0){
//                        allBmxxNode.add(bmxx);
//                    }
//                    else if (bmxx.SPBM.length() / 2 == 0 && child.SPBM.length() / 2 == 0){
//                        allBmxxNode.add(bmxx);
//                    }
//                    else {
                        getAllBmxxNode(dbManager, spbmList, zxbmList, dbManager.queryBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
//                    }
                }
                else if (dbManager.IsExist(SPBMDBHelper.CUS_BMXX_TABLE_NAME, "PID", bmxx.SPBM)){
                    getAllCusBmxxNode(dbManager, spbmList, zxbmList, dbManager.queryCusBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
                }
//                else if (dbManager.IsExist(SPBMDBHelper.CUS_BMXX_TABLE_NAME, "PID", bmxx.ZXBM)){
//                    bmxxList.add(bmxx);
//                    getAllCusBmxxNode(dbManager, allBmxxNode, dbManager.queryCusBMXX(new String[]{"PID"}, new String[]{bmxx.ZXBM}));
//                }
                else{
                    spbmList.add(bmxx);
                }
            }
        }

//        return allBmxxNode;
    }

    private void getAllBmxxNode(DBManager dbManager, List<BMXX> spbmList, List<BMXX> zxbmList, List<BMXX> childList) {
        for (int j = 0; j < childList.size(); ++j) {
            BMXX bmxx = childList.get(j);
//            BMXX child = dbManager.queryOneBMXX("PID", bmxx.SPBM);
//            if (child == null) {
//                child = dbManager.queryOneCusBMXX("PID", bmxx.ZXBM);
//            }

            if (dbManager.IsExist(SPBMDBHelper.BMXX_TABLE_NAME, "PID", bmxx.SPBM)) {
                getAllBmxxNode(dbManager, spbmList, zxbmList, dbManager.queryBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
            }
            else if (dbManager.IsExist(SPBMDBHelper.CUS_BMXX_TABLE_NAME, "PID", bmxx.SPBM)) {
                getAllCusBmxxNode(dbManager, spbmList, zxbmList, dbManager.queryCusBMXX(new String[]{"PID"}, new String[]{bmxx.SPBM}));
            }
//            else if (dbManager.IsExist(SPBMDBHelper.CUS_BMXX_TABLE_NAME, "PID", bmxx.ZXBM)) {
//                bmxxList.add(bmxx);
//                getAllCusBmxxNode(dbManager, bmxxList, dbManager.queryCusBMXX(new String[]{"PID"}, new String[]{bmxx.ZXBM}));
//            }
            else {
                spbmList.add(bmxx);
            }
        }
    }

    private void getAllCusBmxxNode(DBManager dbManager, List<BMXX> spbmList, List<BMXX> zxbmList, List<BMXX> childList){
        for (int j = 0; j < childList.size(); ++j){
            BMXX child = childList.get(j);
//            BMXX child = dbManager.queryOneCusBMXX("PID", bmxx.ZXBM);
            if (dbManager.IsExist(SPBMDBHelper.CUS_BMXX_TABLE_NAME, "PID", child.ZXBM)){
//                if (bmxx.SPBM.length() / 2 != 0 && child.SPBM.length() / 2 == 0){
//                    bmxxList.add(bmxx);
//                }
//                else if (bmxx.SPBM.length() / 2 == 0 && child.SPBM.length() / 2 == 0){
//                    bmxxList.add(bmxx);
//                }
//                else {
                    getAllCusBmxxNode(dbManager, spbmList, zxbmList, dbManager.queryCusBMXX(new String[]{"PID"}, new String[]{child.ZXBM}));
//                }
            }

            zxbmList.add(child);
        }
    }
}
