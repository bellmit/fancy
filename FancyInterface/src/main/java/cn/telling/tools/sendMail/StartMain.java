package cn.telling.tools.sendMail;

public class StartMain {

	/**
	 * toAddr 邮件接收人   若发送多人以;(英文分号)分隔邮箱
	 * subject 邮件标题
	 * content 邮件内容
	 * return true发送成功 false发送失败
	 */
	public static boolean send(String toAddr,String subject,String content){  
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.chinatelling.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);
     mailInfo.setUserName("b2bservice");
     mailInfo.setPassword("TYtelling@1028");
     mailInfo.setFromAddress("b2bservice@chinatelling.com");
     mailInfo.setToAddress(toAddr);
     mailInfo.setSubject(subject);
     mailInfo.setContent(content);
     SimpleMailSender sms = new SimpleMailSender();
     boolean flag = sms.sendTextMail(mailInfo);
     return flag;
   } 
}

