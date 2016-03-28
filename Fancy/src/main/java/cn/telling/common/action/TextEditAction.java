package cn.telling.common.action;
/*package cn.telling.common.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.telling.common.Uitl.FileUploadUtil;
import cn.telling.common.Uitl.UploadImageUtil;
import cn.telling.utils.BaseUtil;
import cn.telling.utils.StringHelperTools;
import cn.telling.utils.UUIDUtil;

*//**
 * 文本编辑器-公用
 * 李振伟
 *//*
@Controller
@RequestMapping("/textedit")
public class TextEditAction {
	@Autowired
	FileUploadUtil fileUpload;
	@Autowired
	IFileUploadService fileUploadService;
	*//**
	 * 文件上传
	 * @param map
	 * @param session
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/uppload", method = { RequestMethod.GET,RequestMethod.POST })
	public @ResponseBody JSONObject upploadPicture(ModelMap map, HttpSession session,HttpServletRequest request) {
		String upploadMsg="";
		String errorMsg="";
		String picUrl="";
		String picuploadurl="/mnt/b2b/textedit/pic/";
		String picId=UUIDUtil.getUUID(); 
        if ("application/octet-stream".equals(request.getContentType())) { //HTML 5 上传 
        	try {
//        		picUrl=UploadImageUtil.uploadPic(request,Global.ZX_PICS_PATH);
    		  String dispoString = request.getHeader("Content-Disposition");
              int iFindStart = dispoString.indexOf("filename=\"") + 10;
              int iFindEnd = dispoString.indexOf("\"", iFindStart);
              String sFileName = dispoString.substring(iFindStart, iFindEnd);
              int i = request.getContentLength();
              byte buffer[] = new byte[i];
              int j = 0;
              while (j < i) { //获取表单的上传文件
                  int k = request.getInputStream().read(buffer, j, i - j);
                  j += k;
              }
              if (buffer.length >= 0) { //文件是否为空
            	  String newfilename=UploadImageUtil.getNewFilename(sFileName);
            	  String serverName=request.getServerName();
            	  String contextRealPath=request.getSession().getServletContext().getRealPath("/");
            	  picUrl= fileUploadService.fileUploadByte(serverName, contextRealPath, newfilename, picuploadurl, buffer);
              }
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }else{
        	MultipartHttpServletRequest req=(MultipartHttpServletRequest) request;
    		Map<String,MultipartFile> files=req.getFileMap();
    		for (MultipartFile file : files.values()) {
//    			String filename = file.getOriginalFilename();
    			try {
//    				picUrl=UploadImageUtil.uploadPic(file, Global.ZX_PICS_PATH, filename);
    				picUrl=fileUpload.getPath(file, request,picuploadurl);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				errorMsg=e.toString();
    			}
    		}
        }
        upploadMsg+="{\"url\":\""+BaseUtil.showStaticFilesPath(request)+picUrl+"\",\"localfile\":\"\",\"id\":\""+picId+"\"}";
		
		String filename = file1.getOriginalFilename();
		String picUrl=UploadImageUtil.uploadPic(file1, Global.INDEX_PICS_PATH, filename);
		String rtnMsg="";
		if(errorMsg!="")
		{
			rtnMsg="{\"err\":\""+errorMsg+"\"";
		}
		else
		{
			rtnMsg="{\"err\":\"\"";
		}
		rtnMsg+=",\"msg\":"+upploadMsg+"}";
		JSONObject tempJson = JSONObject.fromObject(rtnMsg);
		return tempJson;
		
	}
	
	@RequestMapping(value = "/edit", method = { RequestMethod.GET,RequestMethod.POST })
	public String priceCompareTrading(ModelMap map,HttpServletRequest req)throws SQLException {
		String content = StringHelperTools.nvl(req.getParameter("content"));
		String kuandu = StringHelperTools.nvl(req.getParameter("kuandu"));
		String gaodu = StringHelperTools.nvl(req.getParameter("gaodu"));
		map.put("content", content);
		map.put("kuandu", kuandu);
		map.put("gaodu", gaodu);
		return "common/textedit";
	}
	
}
*/