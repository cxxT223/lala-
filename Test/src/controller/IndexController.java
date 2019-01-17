package controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.URLEncoder;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import entity.Kehu;
import service.KehuService;


@Controller
public class IndexController {
//	ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//	KehuService kehuservice = (KehuService) context.getBean("kehuservice");
//	KehuService kehuservice=null;
	@Resource
	private KehuService kehuservice;

	
	
	/**
     * �ļ�����
     * @throws IOException 
     */
    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void download(@RequestParam(value="filename")String filename,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //ģ���ļ���myfile.txtΪ��Ҫ���ص��ļ�  
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"\\"+filename;  
        //��ȡ������  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));

      
        //�����ļ�����ͷ  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }

	
	
	/**
	 * �ļ��ϴ�
	 * @param myfiles
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/upload")
	public String upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException{
		System.out.println("name="+request.getParameter("name"));
		
		System.out.println("myfiles.length="+myfiles.length);
		
			
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println("�ϴ���·��->"+realPath);
		
		for (MultipartFile multipartFile : myfiles) {
			System.out.println("�ļ�����: " + multipartFile.getSize() + "B");
			System.out.println("�ļ�����: " + multipartFile.getContentType());
			System.out.println("�ļ�ԭ��: " + multipartFile.getOriginalFilename());
			System.out.println("========================================");
			
			FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),new File(realPath, multipartFile.getOriginalFilename()));
		}
		
		
		System.out.println("�ϴ��ɹ�~~~~~~~~");
		
		return "index";
	}

	
	@ResponseBody
	@RequestMapping("/ajax223")
	public String ajax223(){
		System.out.println("ajax223=");
		
		return "ok";
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * ��ѯȫ����Ϣ
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/list",produces="text/html;charset=UTF-8;")
	public String list(HttpServletRequest request, HttpServletResponse response,Model model){
		
		int curr=0;
		String currentPageNo = request.getParameter("currentPageNo");
		int totalCount=kehuservice.count();
		String page_no=request.getParameter("page_no");
		//System.out.println(currentPageNo);
		int one=2;
		if(page_no!=null){
			one=Integer.valueOf(page_no);
		}
		if(currentPageNo!=null){
			curr=Integer.valueOf(currentPageNo);
		}
		if(curr<0){
			curr=0;
		}
		if(curr>totalCount){
			curr=totalCount;
		}
		int totalPageCount=totalCount%one==0?(totalCount/one):(totalCount/one+1);
		List<Kehu> list=kehuservice.getInfo(curr,one);
		
			list.get(0).setCount(totalPageCount);
		
		model.addAttribute("totalPageCount",totalPageCount);
		model.addAttribute("totalCount",totalCount);
		if(curr==0){
			curr=1;
		}
		model.addAttribute("currentPageNo",curr);
		model.addAttribute("list",list);
		model.addAttribute("page_no",page_no);
		//model.addAttribute("url","/list");
		return JSONArray.toJSONString(list);
	}
	
	/**
	 * ɾ��
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/Del")
	public String Del(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException{
		String id=request.getParameter("id");
		//System.out.println(id);
        //List<Kehu> list=kehuservice.getInfo();
		//model.addAttribute("list",list);
		kehuservice.Del(Integer.valueOf(Integer.valueOf(id)));
		int curr=1;
		String currentPageNo = request.getParameter("currentPageNo");
		int totalCount=kehuservice.count();
		System.out.println(totalCount);
		String page_no=request.getParameter("page_no");
		int one=2;
		if(page_no!=null){
			one=Integer.valueOf(page_no);
		}
		if(currentPageNo!=null){
			curr=Integer.valueOf(currentPageNo);
		}
		if(curr<1){
			curr=1;
		}
		if(curr>totalCount){
			curr=totalCount;
		}
		int totalPageCount=totalCount%one==0?(totalCount/one):(totalCount/one+1);
		//System.out.println(totalPageCount);
		List<Kehu> list=kehuservice.getInfo(curr,one);
		model.addAttribute("totalPageCount",totalPageCount);
		model.addAttribute("totalCount",totalCount);
//		if(curr<0){
//			curr=0;
//		}
		model.addAttribute("currentPageNo",curr);
		model.addAttribute("list",list);
		
		return "index";
	}
	
	/**
	 * ����������ѯ
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/Onelist")
	public String getOneInfo(HttpServletRequest request, HttpServletResponse response,Model model){
		String name=request.getParameter("name");
		int curr=0;
		String currentPageNo = request.getParameter("currentPageNo");
		int totalCount=kehuservice.Onecount(name);
		System.out.println(totalCount);
		String page_no=request.getParameter("page_no");
		
		int one=2;
		if(page_no!=null){
			one=Integer.valueOf(page_no);
		}
		if(currentPageNo!=null){
			curr=Integer.valueOf(currentPageNo);
		}
		if(curr<0){
			curr=0;
		}
		if(curr>totalCount){
			curr=totalCount;
		}
		int totalPageCount=totalCount%one==0?(totalCount/one):(totalCount/one+1);
		List<Kehu> list=kehuservice.getOneInfo(name,curr,one);
		if(curr==0){
			curr=1;
		}
		
		model.addAttribute("totalPageCount",totalPageCount);
		model.addAttribute("totalCount",totalCount);
		model.addAttribute("currentPageNo",curr);
		
		System.out.println(list.size());
		model.addAttribute("url","duoge");
		model.addAttribute("list",list);
		return "index";
	}
	
	
}
