package com.medicinetracker.util;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.medicinetracker.pojo.MedicinePojo;
/*import javax.servlet.annotation.MultipartConfig;*/
/*import javax.servlet.http.HttpServletRequest;*/
/*import javax.servlet.http.Part;
*/
public class EmailUtility 
{
	public static boolean notifyPatient(String email,List<MedicinePojo> list)
	{
			final String userName="nagargandhi00@gmail.com";
			final String password="navimumbai1";
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			Session session=Session.getInstance(properties,
					new javax.mail.Authenticator(){
				        protected PasswordAuthentication getPasswordAuthentication(){
				        	 return new PasswordAuthentication(userName, password);
				        }
			});

			try
			{
				Message message=new MimeMessage(session);
				message.setFrom(new InternetAddress("nagargandhi00@gmail.com"));
			    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			    message.setSubject("Medicine Reminder");
			    
			    for(int i=0;i<list.size();i++){
			    	MedicinePojo medicinePojo=new MedicinePojo();
			    	medicinePojo=list.get(i);
			    	String messageContent="Medicine Name :"+medicinePojo.getMedicine_name()+" Morning :"+medicinePojo.getMorning_time_medicine()+" Afternoon :"+medicinePojo.getAfternoon_time_medicine()+" Night :"+medicinePojo.getNight_time_medicine()+" Appointment Date :"+medicinePojo.getAppointment_date()+" Days :"+medicinePojo.getDays();
			    	message.setText(messageContent);
			    }
			    
			    
				Transport.send(message);
	            System.out.println("Done");
			}
			catch(MessagingException e)
			{
				throw new RuntimeException(e);
			}
			return true;
		}
	 /*public static void sendEmail( String toAddress, String subject, String message, String attachFiles) throws AddressException, MessagingException {
	 
		    final String userName="nagargandhi00@gmail.com"; //ur dummy email
			final String password="navimumbai1";
	        // sets SMTP server properties
		    Properties properties=new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() 
	        {
	            public PasswordAuthentication getPasswordAuthentication() 
	            {
	                return new PasswordAuthentication(userName, password);
	            }
	        };
	        Session session = Session.getInstance(properties, auth);
	      
	        
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	        
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // adds attachments
	            String filePath = attachFiles;
	            {
	                MimeBodyPart attachPart = new MimeBodyPart();
	                try {
	                    attachPart.attachFile(new File(filePath));
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        
	        
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	        msg.setFrom(new InternetAddress(userName));
	        InternetAddress[] toAddresses = { new InternetAddress(toAddress)};
	        msg.setRecipients(Message.RecipientType.TO, toAddresses);
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 }*/

	//Registered Principal Mail Sent
	 public static boolean sendRegisteredEmail(String principleMailId,String principlePassword)
	{
			final String userName="nagargandhi00@gmail.com";
			final String password="navimumbai1";
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			Session session=Session.getInstance(properties,
					new javax.mail.Authenticator(){
				        protected PasswordAuthentication getPasswordAuthentication(){
				        	 return new PasswordAuthentication(userName, password);
				        }
			});

			try
			{
				Message message=new MimeMessage(session);
				message.setFrom(new InternetAddress("nagargandhi00@gmail.com"));
			    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(principleMailId));
			    message.setSubject("Registration Confirmation Mail");
			    message.setText("You have been successfully registered, your email "+principleMailId+" ,and password is "+principlePassword+" ");
			    //+"' for Rs='"+price+"'"
			    // and Total Amt is='"+total+"'
				Transport.send(message);
	            System.out.println("Done");
			}
			//?emailId='"+emailId+"'
			catch(MessagingException e)
			{
				throw new RuntimeException(e);
		}
			return true;
		}
	 
	 
	 
	 public static boolean notifyParentEmail(String email,String parent_message)
		{
				final String userName="nagargandhi00@gmail.com";
				final String password="navimumbai1";
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				Session session=Session.getInstance(properties,
						new javax.mail.Authenticator(){
					        protected PasswordAuthentication getPasswordAuthentication(){
					        	 return new PasswordAuthentication(userName, password);
					        }
				});

				try
				{
					Message message=new MimeMessage(session);
					message.setFrom(new InternetAddress("nagargandhi00@gmail.com"));
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				    message.setSubject("College Email Regarding your Child");
				    message.setText(" "+parent_message+" ");
					Transport.send(message);
		            System.out.println("Done");
				}
				catch(MessagingException e)
				{
					throw new RuntimeException(e);
				}
				return true;
			}
		
	 
	 //Extended Hours Sent Mail  
	/* public static boolean sendCompanyCriteriaEmail(String tpo_email,String branch,String gender,String sscPercentage,String hscPercentage,String dipPercentage,String degPercentage,String degree,String degPassYear,String company_name)
		{
				final String userName="nagargandhi00@gmail.com";
				final String password="navimumbai1";
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				Session session=Session.getInstance(properties,
						new javax.mail.Authenticator(){
					        protected PasswordAuthentication getPasswordAuthentication(){
					        	 return new PasswordAuthentication(userName, password);
					        }
				});
				
				try
				{
					Message message=new MimeMessage(session);
					message.setFrom(new InternetAddress("nagargandhi00@gmail.com"));
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(tpo_email));
				    message.setSubject(" "+company_name+" criteria");
				    message.setText("'"+company_name+"' criteria is :" +"\n"+
				    		 "Branch : '"+branch+"'" +"\n"+" "+ "Gender : '"+gender+"'" +"\n"+  "SSC % : '"+sscPercentage+"' "+"\n"+
				    		"HSC % : '"+hscPercentage+"' "+"\n"+ "Diploma % : '"+dipPercentage+"' "+"\n"+ "Degree : '"+degree+"' its % : '"+degPercentage+"' and passing year is : '"+degPassYear+"' ");
				    //+"' for Rs='"+price+"'"
				    // and Total Amt is='"+total+"'
					Transport.send(message);
		            System.out.println("Done");
				}
				//?emailId='"+emailId+"'
				catch(MessagingException e)
				{
					throw new RuntimeException(e);
			}
				return true;
			}
	 */
	 
	 
	 /*public static boolean sendOtpEmail(String email,String otp)
		{
				final String userName="nagargandhi00@gmail.com";
				final String password="navimumbai1";
				Properties properties = new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				Session session=Session.getInstance(properties,
						new javax.mail.Authenticator(){
					        protected PasswordAuthentication getPasswordAuthentication(){
					        	 return new PasswordAuthentication(userName, password);
					        }
				});

				try
				{
					Message message=new MimeMessage(session);
					message.setFrom(new InternetAddress("nagargandhi00@gmail.com"));
				    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				    message.setSubject("Registration Confirmation Mail");
				    message.setText(" You are shortlisted and so are eligible to appear apptitude test. "+"\n"+" The exam code is:  '"+otp+"' ");
				    //+"' for Rs='"+price+"'"
				    // and Total Amt is='"+total+"'
					Transport.send(message);
		            System.out.println("Done");
				}
				//?emailId='"+emailId+"'
				catch(MessagingException e)
				{
					throw new RuntimeException(e);
			}
				return true;
			}*/
			
}
