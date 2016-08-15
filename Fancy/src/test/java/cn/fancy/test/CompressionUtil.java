package cn.fancy.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class CompressionUtil {
	
	static ArrayList<TracksPoint> pGPSArrayInit = new ArrayList<TracksPoint>();//原纪录经纬度坐标数组
    static ArrayList<TracksPoint> pGPSArrayFilter = new ArrayList<TracksPoint>();//过滤后的经纬度坐标数组
    static ArrayList<TracksPoint> pGPSArrayFilterSort = new ArrayList<TracksPoint>();//过滤并排序后的经纬度坐标数组
    static double DMax = 5;//设定最大距离误差阈值
    /**
     * 进行轨迹压缩,结果待定,尚未测试
     * @param list
     */
	public static void convertTracksPoint(List<Map<String,String>> list){
		pGPSArrayInit=covertTrackPointFromMap(list);
        pGPSArrayFilter.add(pGPSArrayInit.get(0));//获取第一个原始经纬度点坐标并添加到过滤后的数组中
        pGPSArrayFilter.add(pGPSArrayInit.get(pGPSArrayInit.size()-1));//获取最后一个原始经纬度点坐标并添加到过滤后的数组中
        TracksPoint[] enpInit = new TracksPoint[pGPSArrayInit.size()];//使用一个点数组接收所有的点坐标，用于后面的压缩
        Iterator<TracksPoint> iInit = pGPSArrayInit.iterator();
        int jj=0;
        while(iInit.hasNext()){
            enpInit[jj] = iInit.next();
            jj++;
        }
        //将ArrayList中的点坐标拷贝到点数组中
        int start = 0;//起始下标
        int end = pGPSArrayInit.size()-1;//结束下标
        TrajCompressC(enpInit,pGPSArrayFilter,start,end,DMax);//DP压缩算法
        System.out.println(pGPSArrayFilter.size());//输出压缩后的点数
        //-------------------------4、对压缩后的经纬度点坐标数据按照ID从小到大排序---------------------------------------------//
        TracksPoint[] enpFilter = new TracksPoint[pGPSArrayFilter.size()];//使用一个点数组接收过滤后的点坐标，用于后面的排序
        Iterator<TracksPoint> iF = pGPSArrayFilter.iterator();
        int i = 0;
        while(iF.hasNext()){
            enpFilter[i] = iF.next();
            i++;
        }//将ArrayList中的点坐标拷贝到点数组中
        Arrays.sort(enpFilter);//进行排序
        for(int j=0;j<enpFilter.length;j++){
            pGPSArrayFilterSort.add(enpFilter[j]);//将排序后的点坐标写到一个新的ArrayList数组中
        }
	}
	
	public static void main(String[] args) {
		Gson gson=new Gson();
		String tracks="[{'latitude':'32.626277','longitude':'116.97969'},{'latitude':'32.626286','longitude':'116.97971'},{'latitude':'32.626295','longitude':'116.979732'},{'latitude':'32.626311','longitude':'116.979745'},{'latitude':'32.626336','longitude':'116.979755'},{'latitude':'32.62635','longitude':'116.979788'},{'latitude':'32.626368','longitude':'116.979803'},{'latitude':'32.62636','longitude':'116.979827'},{'latitude':'32.62635','longitude':'116.979851'},{'latitude':'32.626341','longitude':'116.979885'},{'latitude':'32.626344','longitude':'116.979931'},{'latitude':'32.626311','longitude':'116.979936'},{'latitude':'32.626281','longitude':'116.979942'},{'latitude':'32.626276','longitude':'116.979909'},{'latitude':'32.626367','longitude':'116.979772'},{'latitude':'32.626357','longitude':'116.979739'},{'latitude':'32.626353','longitude':'116.979716'},{'latitude':'32.626347','longitude':'116.979688'},{'latitude':'32.626339','longitude':'116.979656'},{'latitude':'32.626332','longitude':'116.979626'},{'latitude':'32.62632','longitude':'116.979595'},{'latitude':'32.626331','longitude':'116.979601'},{'latitude':'32.626338','longitude':'116.979626'},{'latitude':'32.626341','longitude':'116.979648'},{'latitude':'32.626342','longitude':'116.979671'},{'latitude':'32.626346','longitude':'116.979699'},{'latitude':'32.626484','longitude':'116.98032'},{'latitude':'32.626535','longitude':'116.980408'},{'latitude':'32.626752','longitude':'116.981046'},{'latitude':'32.626869','longitude':'116.981929'},{'latitude':'32.626868','longitude':'116.981967'},{'latitude':'32.62686','longitude':'116.981988'},{'latitude':'32.62684','longitude':'116.982019'},{'latitude':'32.626812','longitude':'116.982053'},{'latitude':'32.626699','longitude':'116.982137'},{'latitude':'32.626669','longitude':'116.982135'},{'latitude':'32.626646','longitude':'116.982136'},{'latitude':'32.626622','longitude':'116.982139'},{'latitude':'32.626599','longitude':'116.982141'},{'latitude':'32.626573','longitude':'116.982141'},{'latitude':'32.626547','longitude':'116.982141'},{'latitude':'32.626516','longitude':'116.982146'},{'latitude':'32.626486','longitude':'116.982154'},{'latitude':'32.62646','longitude':'116.982162'},{'latitude':'32.626434','longitude':'116.982169'},{'latitude':'32.626411','longitude':'116.982176'},{'latitude':'32.626389','longitude':'116.982185'},{'latitude':'32.626365','longitude':'116.982193'},{'latitude':'32.626338','longitude':'116.982202'},{'latitude':'32.626311','longitude':'116.982209'},{'latitude':'32.626122','longitude':'116.982323'},{'latitude':'32.626096','longitude':'116.982355'},{'latitude':'32.626084','longitude':'116.982376'},{'latitude':'32.626074','longitude':'116.982402'},{'latitude':'32.626068','longitude':'116.982434'},{'latitude':'32.626067','longitude':'116.982464'},{'latitude':'32.626068','longitude':'116.982496'},{'latitude':'32.626073','longitude':'116.98253'},{'latitude':'32.626079','longitude':'116.982565'},{'latitude':'32.626086','longitude':'116.982602'},{'latitude':'32.626093','longitude':'116.98264'},{'latitude':'32.626103','longitude':'116.982678'},{'latitude':'32.626114','longitude':'116.982719'},{'latitude':'32.626124','longitude':'116.982769'},{'latitude':'32.626133','longitude':'116.982818'},{'latitude':'32.626145','longitude':'116.982869'},{'latitude':'32.626314','longitude':'116.98347'},{'latitude':'32.626448','longitude':'116.984017'},{'latitude':'32.62645','longitude':'116.984064'},{'latitude':'32.626451','longitude':'116.98411'},{'latitude':'32.626434','longitude':'116.984117'},{'latitude':'32.626416','longitude':'116.98412'},{'latitude':'32.625359','longitude':'116.984372'},{'latitude':'32.624929','longitude':'116.984598'},{'latitude':'32.624894','longitude':'116.984617'},{'latitude':'32.624865','longitude':'116.984635'},{'latitude':'32.624837','longitude':'116.984651'},{'latitude':'32.624809','longitude':'116.984667'},{'latitude':'32.62478','longitude':'116.98468'},{'latitude':'32.624754','longitude':'116.984695'},{'latitude':'32.624726','longitude':'116.984714'},{'latitude':'32.624697','longitude':'116.984733'},{'latitude':'32.624668','longitude':'116.98475'},{'latitude':'32.624637','longitude':'116.984772'},{'latitude':'32.62461','longitude':'116.984805'},{'latitude':'32.624601','longitude':'116.984841'},{'latitude':'32.624596','longitude':'116.984886'},{'latitude':'32.624601','longitude':'116.984937'},{'latitude':'32.624609','longitude':'116.984977'},{'latitude':'32.624609','longitude':'116.985021'},{'latitude':'32.624611','longitude':'116.985065'},{'latitude':'32.624615','longitude':'116.985113'},{'latitude':'32.624625','longitude':'116.985159'},{'latitude':'32.624726','longitude':'116.985829'},{'latitude':'32.624741','longitude':'116.985938'},{'latitude':'32.624749','longitude':'116.985985'},{'latitude':'32.624852','longitude':'116.986314'},{'latitude':'32.624889','longitude':'116.986323'},{'latitude':'32.624928','longitude':'116.98633'},{'latitude':'32.625164','longitude':'116.98638'},{'latitude':'32.625168','longitude':'116.986401'},{'latitude':'32.62517','longitude':'116.986428'},{'latitude':'32.625171','longitude':'116.986453'},{'latitude':'32.625171','longitude':'116.986482'},{'latitude':'32.62517','longitude':'116.986507'},{'latitude':'32.625164','longitude':'116.986539'},{'latitude':'32.625162','longitude':'116.986567'},{'latitude':'32.625163','longitude':'116.986539'},{'latitude':'32.624988','longitude':'116.986498'},{'latitude':'32.625067','longitude':'116.986489'},{'latitude':'32.624984','longitude':'116.986498'},{'latitude':'32.624964','longitude':'116.986481'},{'latitude':'32.625','longitude':'116.986488'},{'latitude':'32.625004','longitude':'116.986507'},{'latitude':'32.624984','longitude':'116.986504'},{'latitude':'32.624984','longitude':'116.986504'},{'latitude':'32.625061','longitude':'116.986446'},{'latitude':'32.625067','longitude':'116.986489'},{'latitude':'32.625061','longitude':'116.986446'},{'latitude':'32.625084','longitude':'116.986458'},{'latitude':'32.62501','longitude':'116.986459'},{'latitude':'32.625036','longitude':'116.986486'},{'latitude':'32.625003','longitude':'116.986496'},{'latitude':'32.625065','longitude':'116.986459'},{'latitude':'32.625083','longitude':'116.986442'},{'latitude':'32.625035','longitude':'116.986486'},{'latitude':'32.625003','longitude':'116.986496'},{'latitude':'32.625036','longitude':'116.986486'},{'latitude':'32.625036','longitude':'116.986486'},{'latitude':'32.625003','longitude':'116.986496'}]";
		List<Map<String,String>> map=gson.fromJson(tracks, new TypeToken<List<Map<String,String>>>() {}.getType());
		convertTracksPoint(map);
		map.clear();
		for (int i = 0; i < pGPSArrayFilterSort.size(); i++) {
			Map<String,String> m=new HashedMap();
			TracksPoint tp=pGPSArrayFilterSort.get(i);
			m.put("latitude", tp.pn+"");
			m.put("longitude", tp.pe+"");
			map.add(m);
		}
		System.out.println(gson.toJson(map));
	}
	
	/**
     *函数功能：从源文件中读出所以记录中的经纬度坐标，并存入到ArrayList数组中，并将其返回
     * @param fGPS：源数据文件
     * @return pGPSArrayInit：返回保存所有点坐标的ArrayList数组
     * @throws Exception
     */
    public static ArrayList<TracksPoint> getTracksPointFromFile(File fGPS)throws Exception{
        ArrayList<TracksPoint> pGPSArray = new ArrayList<TracksPoint>();
        if(fGPS.exists()&&fGPS.isFile()){
            InputStreamReader read = new InputStreamReader(new FileInputStream(fGPS));
            BufferedReader bReader = new BufferedReader(read);
            String str;
            String[] strGPS;
            int i = 0;
            while((str = bReader.readLine())!=null){
                strGPS = str.split(" ");
                TracksPoint p = new TracksPoint();
                p.id = i;
                i++;
                p.pe = (dfTodu(strGPS[3]));
                p.pn = (dfTodu(strGPS[5]));
                pGPSArray.add(p);
            }
            bReader.close();
        }
        return pGPSArray;
    }

    /**
     * 函数功能：将过滤后的点的经纬度坐标、平均距离误差、压缩率写到结果文件中
     * @param outGPSFile：结果文件
     * @param pGPSPointFilter：过滤后的点
     * @param mDerror：平均距离误差
     * @param cRate：压缩率
     * @throws Exception
     */
    public static void writeFilterPointToFile(File outGPSFile,ArrayList<TracksPoint> pGPSPointFilter,
                                              double mDerror,double cRate)throws Exception{
        Iterator<TracksPoint> iFilter = pGPSPointFilter.iterator();
        RandomAccessFile rFilter = new RandomAccessFile(outGPSFile,"rw");
        while(iFilter.hasNext()){
            TracksPoint p = iFilter.next();
            String sFilter = p.getResultString()+"\n";
            byte[] bFilter = sFilter.getBytes();
            rFilter.write(bFilter);
        }
        String strmc = "#"+Integer.toString(pGPSPointFilter.size())+","+
                Double.toString(mDerror)+","+Double.toString(cRate)+"%"+"#"+"\n";
        byte[] bmc = strmc.getBytes();
        rFilter.write(bmc);

        rFilter.close();
    }
    /**
     * 函数功能：将转换后的原始经纬度数据点存到文件中
     * @param outGPSFile
     * @param pGPSPointFilter
     * @throws Exception
     */
    public static void writeInitPointToFile(File outGPSFile,ArrayList<TracksPoint> pGPSPointFilter)throws Exception{
        Iterator<TracksPoint> iFilter = pGPSPointFilter.iterator();
        RandomAccessFile rFilter = new RandomAccessFile(outGPSFile,"rw");
        while(iFilter.hasNext()){
            TracksPoint p = iFilter.next();
            String sFilter = p.toString()+"\n";
            byte[] bFilter = sFilter.getBytes();
            rFilter.write(bFilter);
        }
        rFilter.close();
    }
    /**
     * 函数功能：将数组中的经纬度点坐标数据写入测试文件中，用于可视化测试
     * @param outGPSFile：文件对象
     * @param pGPSPointFilter：点数组
     * @throws Exception
     */
    public static void writeTestPointToFile(File outGPSFile,ArrayList<TracksPoint> pGPSPointFilter)throws Exception{
        Iterator<TracksPoint> iFilter = pGPSPointFilter.iterator();
        RandomAccessFile rFilter = new RandomAccessFile(outGPSFile,"rw");
        while(iFilter.hasNext()){
            TracksPoint p = iFilter.next();
            String sFilter = p.getTestString()+"\n";
            byte[] bFilter = sFilter.getBytes();
            rFilter.write(bFilter);
        }
        rFilter.close();
    }

    /**
     * 函数功能：将原始经纬度坐标数据转换成度
     * @param str：原始经纬度坐标
     * @return ：返回对于的度数据
     */
    public static double dfTodu(String str){
        int indexD = str.indexOf('.');
        String strM = str.substring(0,indexD-2);
        String strN = str.substring(indexD-2);
        double d = Double.parseDouble(strM)+Double.parseDouble(strN)/60;
        return d;
    }
    /**
     * 函数功能：保留一个double数的小数点后六位
     * @param d：原始double数
     * @return 返回转换后的double数
     */
    public static double getPointSix(double d){
        DecimalFormat df = new DecimalFormat("0.000000");
        return Double.parseDouble(df.format(d));
    }
    /**
     * 函数功能：使用三角形面积（使用海伦公式求得）相等方法计算点pX到点pA和pB所确定的直线的距离
     * @param pA：起始点
     * @param pB：结束点
     * @param pX：第三个点
     * @return distance：点pX到pA和pB所在直线的距离
     */
    public static double distToSegment(TracksPoint pA,TracksPoint pB,TracksPoint pX){
        double a = Math.abs(geoDist(pA, pB));
        double b = Math.abs(geoDist(pA, pX));
        double c = Math.abs(geoDist(pB, pX));
        double p = (a+b+c)/2.0;
        double s = Math.sqrt(Math.abs(p*(p-a)*(p-b)*(p-c)));
        double d = s*2.0/a;
        return d;
    }

    /**
     * 函数功能：用老师给的看不懂的方法求两个经纬度点之间的距离
     * @param pA：起始点
     * @param pB：结束点
     * @return distance：距离
     */
    public static double geoDist(TracksPoint pA,TracksPoint pB)
    {
        double radLat1 = Rad(pA.pn);
        double radLat2 = Rad(pB.pn);
        double delta_lon = Rad(pB.pe - pA.pe);
        double top_1 = Math.cos(radLat2) * Math.sin(delta_lon);
        double top_2 = Math.cos(radLat1) * Math.sin(radLat2) - Math.sin(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
        double top = Math.sqrt(top_1 * top_1 + top_2 * top_2);
        double bottom = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
        double delta_sigma = Math.atan2(top, bottom);
        double distance = delta_sigma * 6378137.0;
        return distance;
    }
    /**
     * 函数功能：角度转弧度
     * @param d：角度
     * @return 返回的是弧度
     */
    public static double Rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    
    /**
     * 函数功能：根据最大距离限制，采用DP方法递归的对原始轨迹进行采样，得到压缩后的轨迹
     * @param enpInit：原始经纬度坐标点数组
     * @param enpArrayFilter：保持过滤后的点坐标数组
     * @param start：起始下标
     * @param end：终点下标
     * @param DMax：预先指定好的最大距离误差
     */
    public static void TrajCompressC(TracksPoint[] enpInit,ArrayList<TracksPoint> enpArrayFilter,
                                     int start,int end,double DMax){
        if(start < end){//递归进行的条件
            double maxDist = 0;//最大距离
            int cur_pt = 0;//当前下标
            for(int i=start+1;i<end;i++){
                double curDist = distToSegment(enpInit[start],enpInit[end],enpInit[i]);//当前点到对应线段的距离
                if(curDist > maxDist){  //求出最大距离及最大距离对应点的下标
                    maxDist = curDist;
                    cur_pt = i;
                }
            }
            //若当前最大距离大于最大距离误差
            if(maxDist >= DMax){
                enpArrayFilter.add(enpInit[cur_pt]);//将当前点加入到过滤数组中
                TrajCompressC(enpInit,enpArrayFilter,start,cur_pt,DMax);//将原来的线段以当前点为中心拆成两段，分别进行递归处理
                TrajCompressC(enpInit,enpArrayFilter,cur_pt,end,DMax);
            }
        }
    }
    /**
     * 函数功能：求平均距离误差
     * @param pGPSArrayInit：原始数据点坐标
     * @param pGPSArrayFilterSort：过滤后的数据点坐标
     * @return ：返回平均距离
     */
    public static double getMeanDistError(
            ArrayList<TracksPoint> pGPSArrayInit,ArrayList<TracksPoint> pGPSArrayFilterSort){
        double sumDist = 0.0;
        for(int i=1;i<pGPSArrayFilterSort.size();i++){
            int start = pGPSArrayFilterSort.get(i-1).id;
            int end = pGPSArrayFilterSort.get(i).id;
            for(int j=start+1;j<end;j++){
                sumDist += distToSegment(
                        pGPSArrayInit.get(start),pGPSArrayInit.get(end),pGPSArrayInit.get(j));
            }
        }
        double meanDist = sumDist/(pGPSArrayInit.size());
        return meanDist;
    }
    
    public static ArrayList<TracksPoint> covertTrackPointFromMap(List<Map<String,String>> list){
    	ArrayList<TracksPoint> tracksPointList=new ArrayList<TracksPoint>();
    	for (int i = 0; i < list.size(); i++) {
    		TracksPoint tp=new TracksPoint();
			Map<String,String> map=list.get(i);
			tp.pe=Double.parseDouble(map.get("longitude"));
			tp.pn=Double.parseDouble(map.get("latitude"));
			tp.id=i;
			tracksPointList.add(tp);
		}
    	return tracksPointList;
    }
}


