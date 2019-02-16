package com.robot;
import java.util.*;
import com.Tick_Tock.ANDROIDQQ.sdk.*;
import java.util.regex.*;
import org.json.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Main implements Plugin
{
    
	public static String get_root_path(){
		File directory = new File("");
		String exact_directory = "";
		try
		{
			exact_directory  = directory.getCanonicalPath();
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return exact_directory;
	}
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	//读取文本到List
	  public static List<String> getFileLineStrList(String path) {
	        List<String> strList = new ArrayList<String>();
	        File file = new File(path);
	        InputStreamReader read = null;
	        BufferedReader reader = null;
	        try {
	            read = new InputStreamReader(new FileInputStream(file),"utf-8");
	            reader = new BufferedReader(read);
	            String line;
	            while ((line = reader.readLine()) != null) {
	                strList.add(line);
	            }
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (read != null) {
	                try {
	                    read.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	 
	        }
	        return strList;
	    }
	    
	private API api;

	@Override
	public String Version()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public String author()
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public String name()
	{
		return "测试机器人";
	}
	
	
	
	
	@Override
	public void onLoad(API p1)
	{
		this.api = p1;
		
		this.api.UpdateGroupList();/*获取一下群列表*/
	}

	
	@Override
	public void onMenu(GroupMessage p1)
	{
		// TODO: Implement this method
	}
	
	public String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }
	
	@Override
	public void onMessageHandler(GroupMessage qqmessage)
	{
		MessageFactory factory = new MessageFactory();
		factory.message_type=0;
		factory.Group_uin=qqmessage.group_uin;
		if(qqmessage.message.matches("测试文本")){
			String lineout = get_root_path();
			factory.Message=lineout;
			this.api.SendGroupMessage(factory);
			
		}
		else if(qqmessage.message.indexOf("笑话")!=-1)
		{
			String pathname = get_root_path() + "/mysrc/qqRotQA/xiaohua/成人笑话.txt"; 
			
			try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) 
	        ) {
	             
				 
			    List<String> lineListFileContent = 
	        		getFileLineStrList(pathname);
	        
	             int iCount = randInt(1,lineListFileContent.size());
	            String line=lineListFileContent.get(iCount);
				 factory.Message=line;
			       this.api.SendGroupMessage(factory);
	          
	        } catch (IOException e) {
	            
	        }

		}
		else if(qqmessage.message.indexOf("鸡汤")!=-1)
		{
			String pathname = get_root_path() + "/mysrc/qqRotQA/xiaohua/鸡汤.txt"; 
			
			try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) 
	        ) {
	             
				 
			    List<String> lineListFileContent = 
	        		getFileLineStrList(pathname);
	        
	            int iCount = randInt(1,lineListFileContent.size());
	            String line=lineListFileContent.get(iCount);
				factory.Message=line;
			    this.api.SendGroupMessage(factory);
	          
	        } catch (IOException e) {
	            
	        }

		}
		
		else if(qqmessage.message.startsWith("评论") )
		{
			String[] infor = qqmessage.message.split(" +");
			if(infor.length>=0)
			{
				String spaName = infor[1].trim();
				String pathname = get_root_path() + "/mysrc/qqRotQA/pinglun/"+ spaName +".txt"; 
			
				try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) 
				) 
				{
	             
				 
			    List<String> lineListFileContent = 
	        		getFileLineStrList(pathname);
	        
	             int iCount = randInt(0,lineListFileContent.size()-1);
	             String line=lineListFileContent.get(iCount);
				 int iMaxChar = 200;
				 for (int j = 0; j < line.length(); j=j+iMaxChar) { 

			             if(j+50< line.length() )
							 {
							  factory.Message=line.substring(j, j+iMaxChar);
							  this.api.SendGroupMessage(factory);}
			            else
				       {
							  factory.Message=line.substring(j);
							  this.api.SendGroupMessage(factory);}
		                   }
				 
	          
				} catch (IOException e) {
	            
				}
				
			}
			

		}
		else if(qqmessage.message.equals("友商"))
		{
			String pathname = get_root_path() + "/mysrc/qqRotQA/在哪.txt"; 
			
			try (FileReader reader = new FileReader(pathname);
	             BufferedReader br = new BufferedReader(reader) 
	        ) {
	            String line = readToString(pathname);
	
	            factory.Message=line;
			    this.api.SendGroupMessage(factory);
	        
	        } catch (IOException e) {
	            
	        }

		}
		else if(qqmessage.message.equals("查看群列表"))
		{
			List<QQGroup> groups = this.api.GetGroupList();
			String message_to_send="...\n";
			for (QQGroup group :groups){
				message_to_send+=Util.ridiculous_uin(String.valueOf(group.group_uin))+" "+group.group_name+" ";
			}
			factory.Message=message_to_send;
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.matches("复读\\s+.*")){
			factory.Message=qqmessage.message.replaceAll("复读\\s+","");
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.matches("测试图片\\s+.*")){
			factory.Message="/sdcard/Pictures/Screenshots/"+qqmessage.message.replaceAll("测试图片\\s+","")+".png";
			this.api.SendGroupImage(factory);
		}else if(qqmessage.message.matches("测试语音\\s+.*")){
			factory.Message="/sdcard/ZZPTT/"+qqmessage.message.replaceAll("测试语音\\s+","")+".amr";
			this.api.SendGroupPtt(factory);
		}else if(qqmessage.message.matches("@.*")&&qqmessage.at_list.contains(qqmessage.self_uin)){
			factory.Message="艾特我做什么?";
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("更新群列表")){
			this.api.UpdateGroupList();
		}else if(qqmessage.message.matches("禁言@.*")&&qqmessage.at_list.size()!=0){
			factory.target_uin=qqmessage.at_list.get(0);
			factory.shuttime=Long.parseLong(qqmessage.message.split(" ")[qqmessage.message.split(" ").length-1]);
			this.api.GroupMemberShutUp(factory);
		}else if(qqmessage.message.equals("开启全体禁言")){
			factory.isshutup=true;
			this.api.GroupShutUp(factory);
		}else if(qqmessage.message.equals("关闭全体禁言")){
			factory.isshutup=false;
			this.api.GroupShutUp(factory);
		}else if(qqmessage.message.matches("踢人@.*")&&qqmessage.at_list.size()!=0){
			factory.target_uin=qqmessage.at_list.get(0);
			this.api.GroupMemberDelete(factory);
		}else if(qqmessage.message.equals("艾特我")){
			AtStore store = new AtStore();
			store.At_Name=qqmessage.sender_name;
			store.Target_uin=qqmessage.sender_uin;
			factory.at_list.add(store);
			factory.Message="艾特了然后呢?";
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("测试卡片")){
			factory.message_type=1;
			factory.Message="<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"2\" templateID=\"1\" action=\"web\" brief=\"[分享]  卡路里\" sourceMsgId=\"0\" url=\"http://t.cn/EbgQ3Dr\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"2\"><audio cover=\"http://t.cn/EGW0JBT\" src=\"http://t.cn/EGWWbXT\" /><title>卡 路里</title><summary>火箭少女101西虹市首富 电影原声</summary></item><source name=\"QQ音乐\" icon=\"https://url.cn/5TAIkR5\" url=\"http://url.cn/5aSZ8Gc\" action=\"app\" a_actionData=\"com.tencent.qqmusic\" i_actionData=\"tencent100497308://\" appid=\"100497308\" /></msg>";
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.matches("转卡片\\s+.*")){
			factory.message_type=1;
			factory.Message=qqmessage.message.replaceAll("转卡片\\s+","");
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("测试json")){
			factory.message_type=2;
			factory.Message="{\"app\":\"com.tencent.music\",\"desc\":\"音乐\",\"view\":\"RichInfomation\",\"ver\":\"0.0.0.1\",\"prompt\":\"[应用]音乐\",\"meta\":{\"Music.Get\":{\"Music.Name\":\"nevada\"}},\"config\":{\"type\":\"card\",\"forward\":1,\"autosize\":1}}";			
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.matches("转json\\s+.*")){
			factory.message_type=2;
			factory.Message=qqmessage.message.replaceAll("转json\\s+","");
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("我的头像")){
			factory.message_type=1;
			factory.Message="<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"6\" templateID=\"1\" action=\"web\" brief=\"酷狗音乐\" sourceMsgId=\"0\" url=\"\" flag=\"0\" adverSign=\"0\"><item layout=\"3\"><picture cover=\"mqqapi://card/show_pslcard?src_type=internal&amp;source=sharecard&amp;version=1&amp;uin="+String.valueOf(qqmessage.sender_uin)+"\"/></item></msg>";
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.equals("我的名片")){
			factory.message_type=1;
			factory.Message="<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"8\" templateID=\"1\" action=\"web\" brief=\"你的名片\" sourceMsgId=\"0\" url=\"\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"3\"><button action=\"plugin\" a_actionData=\"mqqapi://card/show_pslcard?src_type=internal&amp;source=sharecard&amp;version=1&amp;uin="+String.valueOf(qqmessage.sender_uin)+"\">点击查看</button></item></msg>";
			this.api.SendGroupMessage(factory);
		}else if(qqmessage.message.matches("点歌\\s+.*")){
			factory.message_type=2;
			factory.Message="{\"app\":\"com.tencent.music\",\"desc\":\"音乐\",\"view\":\"RichInfomation\",\"ver\":\"0.0.0.1\",\"prompt\":\"[应用]音乐\",\"meta\":{\"Music.Get\":{\"Music.Name\":\""+qqmessage.message.replaceAll("点歌\\s+","")+"\"}},\"config\":{\"type\":\"card\",\"forward\":1,\"autosize\":false}}";			
			this.api.SendGroupMessage(factory);
		}
		else if(qqmessage.message.matches("跑卡片")){
			factory.message_type=1;
			for(int i =10;i<=300;i++){
				factory.Message="<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\""+String.valueOf(i)+"\" templateID=\"1\" action=\"web\" brief=\"啊\" sourceMsgId=\"0\" url=\"\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"3\"><button>1</button></item><item layout=\"3\"><button>1</button></item><item layout=\"3\"><button>1</button></item><item layout=\"3\"><button>1</button></item></msg>";
				this.api.SendGroupMessage(factory);
				try
				{
					Thread.sleep(15000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		
		
		
	}
	
	@Override
	public void onMessageHandler(FriendMessage qqmessage)
	{
		MessageFactory factory = new MessageFactory();
		factory.message_type=0;
		factory.Friend_uin=qqmessage.sender_uin;
		if(qqmessage.message.matches("测试文本")){
			factory.Message="测试成功，文本消息";
			this.api.SendFriendMessage(factory);
		}
	}

	@Override
	public void onMessageHandler(TempolarMessage qqmessage)
	{
		
		MessageFactory factory = new MessageFactory();
		factory.message_type=0;
		factory.target_uin=qqmessage.sender_uin;
		factory.group_code=qqmessage.group_code;
		if(qqmessage.message.matches("测试文本")){
			factory.Message="测试成功，文本消息";
			this.api.SendTemporaryMessage(factory);

		}else if(qqmessage.message.matches("测试图片\\s+.*")){
			factory.Message="/sdcard/Pictures/Screenshots/"+qqmessage.message.replaceAll("测试图片\\s+","")+".png";
			this.api.SendTemporaryImage(factory);
		}else if(qqmessage.message.equals("测试卡片")){
			factory.message_type=1;
			factory.Message="<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><msg serviceID=\"2\" templateID=\"1\" action=\"web\" brief=\"[分享]  卡路里\" sourceMsgId=\"0\" url=\"http://t.cn/EbgQ3Dr\" flag=\"0\" adverSign=\"0\" multiMsgFlag=\"0\"><item layout=\"2\"><audio cover=\"http://t.cn/EGW0JBT\" src=\"http://t.cn/EGWWbXT\" /><title>卡 路里</title><summary>火箭少女101西虹市首富 电影原声</summary></item><source name=\"QQ音乐\" icon=\"https://url.cn/5TAIkR5\" url=\"http://url.cn/5aSZ8Gc\" action=\"app\" a_actionData=\"com.tencent.qqmusic\" i_actionData=\"tencent100497308://\" appid=\"100497308\" /></msg>";
			this.api.SendTemporaryMessage(factory);
		}else if(qqmessage.message.matches("转卡片\\s+.*")){
			factory.message_type=1;
			factory.Message=qqmessage.message.replaceAll("转卡片\\s+","");
			this.api.SendTemporaryMessage(factory);
		}else if(qqmessage.message.equals("测试json")){
			factory.message_type=2;
			factory.Message="{\"app\":\"com.tencent.music\",\"desc\":\"音乐\",\"view\":\"RichInfomation\",\"ver\":\"0.0.0.1\",\"prompt\":\"[应用]音乐\",\"meta\":{\"Music.Get\":{\"Music.Name\":\"nevada\"}},\"config\":{\"type\":\"card\",\"forward\":1,\"autosize\":1}}";			
			this.api.SendTemporaryMessage(factory);
		}
		
		
	}

	@Override
	public void onMessageHandler(RequestMessage qqmessage)
	{
		MessageFactory factory = new MessageFactory();
		factory.requestid=qqmessage.request_id;
		factory.ispass=true;
		factory.Group_uin=qqmessage.group_uin;
		factory.target_uin=qqmessage.requestor;
		this.api.DealGroupRequest(factory);
	}

	
	
	
	
	
	
}
