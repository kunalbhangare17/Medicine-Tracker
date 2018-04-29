package com.medicinetracker.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.medicinetracker.dao.MedicineDao;
import com.medicinetracker.dao.MedicineDaoImpl;
import com.medicinetracker.dao.PatientDao;
import com.medicinetracker.dao.PatientDaoImpl;
import com.medicinetracker.pojo.MedicinePojo;
import com.medicinetracker.pojo.PatientPojo;
import com.medicinetracker.pojo.PatientReportPojo;
import com.medicinetracker.util.EmailUtility;

/**
 * Servlet implementation class PatientServlet
 */
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		PatientPojo patientPojo = new PatientPojo();
		PatientDao patientDao = new PatientDaoImpl();
		MedicineDao medicineDao=new MedicineDaoImpl();
		 JSONObject json = new JSONObject();
		 ObjectMapper mapperObj = new ObjectMapper();
		 
		 Enumeration en=request.getParameterNames();
		 
			while(en.hasMoreElements())
			{
				Object objOri=en.nextElement();
				String param=(String)objOri;
				String value=request.getParameter(param);
				System.out.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");
			}	
		
		if(action!=null && action.equalsIgnoreCase("patientRegister"))
		{
			String patient_name = request.getParameter("patient_name");
			String patient_contact_no = request.getParameter("patient_contact_no");
			String patient_email = request.getParameter("patient_email");
			String patient_password= request.getParameter("patient_password");
			String patient_reference=request.getParameter("patient_reference");
			String patient_ref_name= request.getParameter("patient_ref_name");
			String patient_ref_email=request.getParameter("patient_ref_email");
			String address=request.getParameter("address");

			patientPojo.setPatient_name(patient_name);
			patientPojo.setPatient_contact_no(patient_contact_no);
			patientPojo.setPatient_email(patient_email);
			patientPojo.setPatient_password(patient_password);
			patientPojo.setPatient_reference(patient_reference);
			patientPojo.setPatient_ref_name(patient_ref_name);
			patientPojo.setPatient_ref_email(patient_ref_email);
			patientPojo.setAddress(address);
			
			int flag = patientDao.add(patientPojo);
			if(flag==1)
			{
				System.out.println("Patient Data Inserted Successfully");
			}
			else
			{
				 System.out.println("Please check again");
			}
		}
		
		
		else if(action!=null && action.equalsIgnoreCase("patientLogin"))
		{
			String patient_email = request.getParameter("patient_email");
			String patient_password= request.getParameter("patient_password");
								   				    
			patientPojo = patientDao.login(patient_email, patient_password);

			if(patientPojo!=null)
			    {
			    	System.out.println("Patient Login Successfully");
				    String jsonLoginList = mapperObj.writeValueAsString(patientPojo);	
				    response.setContentType("application/json");
			        response.setCharacterEncoding("UTF-8");
			        System.out.println(jsonLoginList);
			        response.getWriter().write(jsonLoginList);
			    }
			    else
			    {
			    	//System.out.println("Patient Login Successfully");
				    String jsonLoginList = mapperObj.writeValueAsString(patientPojo);	
				    response.setContentType("application/json");
			        response.setCharacterEncoding("UTF-8");
			        System.out.println(jsonLoginList);
			        response.getWriter().write(jsonLoginList);
			    	/*System.out.println("Patient Please try again");
			    	json.put("success","false");
				     json.put("message","NO match found");
				     response.setContentType("application/json");
			         response.setCharacterEncoding("UTF-8");
			         response.getWriter().write(json.toString());*/
			    }
		}
		else if(action!=null && action.equalsIgnoreCase("addPatientReport"))
		{
		String user_email=request.getParameter("user_email");
		String date=request.getParameter("date");
		double systolic=Double.parseDouble(request.getParameter("systolic"));
		double diastolic=Double.parseDouble(request.getParameter("diastolic"));
		double glucose=Double.parseDouble(request.getParameter("glucose"));
		double aic=Double.parseDouble(request.getParameter("aic"));
		double total_cholestrol=Double.parseDouble(request.getParameter("total_cholestrol"));
		double bmi=Double.parseDouble(request.getParameter("bmi"));
		
		PatientReportPojo patientReportPojo=new PatientReportPojo();
		patientReportPojo.setUser_email(user_email);
		patientReportPojo.setDate(date);
		patientReportPojo.setSystolic(systolic);
		patientReportPojo.setDiastolic(diastolic);
		patientReportPojo.setGlucose(glucose);
		patientReportPojo.setAic(aic);
		patientReportPojo.setTotal_cholestrol(total_cholestrol);
		patientReportPojo.setBmi(bmi);
		
		
		patientDao.AddReport(patientReportPojo);
		
		}
		else if(action!=null && action.equalsIgnoreCase("getPatientReport"))
		{
			String user_email=request.getParameter("user_email");	
			List<PatientReportPojo> patientReportPojo=patientDao.getPatientReport(user_email);
			
			 String jsonLoginList = mapperObj.writeValueAsString(patientReportPojo);	
			    response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        System.out.println(jsonLoginList);
		        response.getWriter().write(jsonLoginList);
		}
		else if(action!=null && action.equalsIgnoreCase("sendNotification"))
		{
			String patient_email=request.getParameter("patient_email");	 
			String date=request.getParameter("date");
			
			List<MedicinePojo> list=medicineDao.getNotificationByPatient(patient_email, date);
			
			EmailUtility.notifyPatient(patient_email, list);
		}
		
	}

}
