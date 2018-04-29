package com.medicinetracker.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;


import com.medicinetracker.dao.LocationDao;
import com.medicinetracker.dao.LocationDaoImpl;
import com.medicinetracker.dao.MedicineDao;
import com.medicinetracker.dao.MedicineDaoImpl;
import com.medicinetracker.pojo.LocationMasterPojo;
import com.medicinetracker.pojo.MedicinePojo;
import com.medicinetracker.util.CurrentTimeDateCalendar;
import com.medicinetracker.util.DateTimeDifference;
import com.sun.jmx.snmp.Timestamp;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class MedicineServlet
 */
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		MedicinePojo medicinePojo = new MedicinePojo();
		MedicineDao medicineDao = new MedicineDaoImpl();
		LocationDao locationDao=new LocationDaoImpl();
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
			
		int notify=1;
		List<MedicinePojo> notifyList=medicineDao.viewNotify(notify);
		
		for(int i=0;i<notifyList.size();i++)
		{
			String email=notifyList.get(i).getPatient_email();
			String medicine=notifyList.get(i).getMedicine_name();
			System.out.println("email ::"+email+" "+notifyList.size()+"  "+medicine);
			String rel_email=notifyList.get(i).getPatient_ref_email();
			
			String mor_time=notifyList.get(i).getMorning_time_medicine();
			String aftr_time=notifyList.get(i).getAfternoon_time_medicine();
			String night_time=notifyList.get(i).getNight_time_medicine();
			
			/*String current_time=CurrentTimeDateCalendar.getCurrentTimeUsingCalendar();
			System.out.println("current_time "+current_time);
			int cur_time=Integer.parseInt(current_time);
			System.out.println("cur_time "+cur_time);
			String defined_mor_time="09:00:00";
			int def_mor_time=Integer.parseInt(current_time);*/
			
			/*String morn_difference=(current_time-defined_mor_time);
			int difference=cur_time-def_mor_time;
			System.out.println("difference::: "+difference);*/
			if(mor_time=="yes")
			{
				
			}
			else if(aftr_time=="yes")
			{
				
			}
			else if(night_time=="yes")
			{
				
			}
		}
		
		//long timeDiff=	DateTimeDifference.getTimeDifference(dateStart, dateStop);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		MedicinePojo medicinePojo = new MedicinePojo();
		MedicineDao medicineDao = new MedicineDaoImpl();
		LocationDao locationDao=new LocationDaoImpl();
		
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
		
		if(action!=null && action.equalsIgnoreCase("addMedicine"))
		{
			String doctor_name=request.getParameter("doctor_name");							
			//String medicines[]=request.getParameterValues("medicines");
			String medicine_name=request.getParameter("medicine_name");
			String morning_time_medicine=request.getParameter("morning_time_medicine");
			String afternoon_time_medicine=request.getParameter("afternoon_time_medicine");							
			String night_time_medicine=request.getParameter("night_time_medicine");
			int patient_id=Integer.parseInt(request.getParameter("patient_id"));
			String patient_email=request.getParameter("patient_email");
			String patient_ref_email=request.getParameter("patient_ref_email");
			String role=request.getParameter("role");
			int days=Integer.parseInt(request.getParameter("days"));
			int notify=1;
			/*JSONArray jsonArray;
			try {
				jsonArray = new JSONArray(medicine1);

				
				for (int i = 0; i < jsonArray.length(); i++) {
				    org.json.JSONObject explrObject = jsonArray.getJSONObject(i);
				    System.out.println(explrObject.get("meds1"));
				    System.out.println(explrObject.get("meds2"));
				    System.out.println(explrObject.get("meds3"));
				    System.out.println(explrObject.get("meds4"));
				    System.out.println(explrObject.get("meds5"));
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			
			//String medicine[][]={doctor_name,morning_time_medicine,afternoon_time_medicine,night_time_medicine,syrup,morning_time_syrup,afternoon_time_syrup,night_time_syrup,patient_id,patient_email,patient_ref_email};
		
			medicinePojo.setDoctor_name(doctor_name);
			medicinePojo.setMedicine_name(medicine_name);
			medicinePojo.setMorning_time_medicine(morning_time_medicine);
			medicinePojo.setAfternoon_time_medicine(afternoon_time_medicine);
			medicinePojo.setNight_time_medicine(night_time_medicine);
			medicinePojo.setPatient_id(patient_id);
			medicinePojo.setPatient_email(patient_email);
			medicinePojo.setPatient_ref_email(patient_ref_email);
			medicinePojo.setRole(role);
			medicinePojo.setDays(days);
			medicinePojo.setNotify(notify);
			
			int flag = medicineDao.add(medicinePojo);
			
			if(flag==1)
			{
				System.out.println("Medicine Data Inserted Successfully");
			}
			else
			{
				 System.out.println("Please check again");
			}
		}
		
		else if(action!=null && action.equalsIgnoreCase("addMoreMedicines"))
		{
			String patient_email=request.getParameter("patient_email");
			
			List<MedicinePojo> medicineListByEmail= medicineDao.addMoreMedicines(patient_email);
			
			if(medicineListByEmail!=null)		
			{
				//Ann
				String patternString = "\\b(" + StringUtils.join(medicineListByEmail, "|") + ")\\b";
				System.out.println("patternString"+patternString);
				Pattern pattern = Pattern.compile(patternString);
				Matcher matcher = pattern.matcher(patient_email);
				System.out.println("matcher"+matcher);
				
				mapperObj.enable(SerializationConfig.Feature.INDENT_OUTPUT);
				String arrayToJson=mapperObj.writeValueAsString(medicineListByEmail);
				System.out.println("arrayToJson "+arrayToJson);
				response.getWriter().write(arrayToJson.toString());
			}
			else
			{
				json.put("success", "false");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json.toString());
			}
		}
		else if(action!=null && action.equals("getParkingLocation"))
		{
			String latitude= request.getParameter("latitude"); 
			String longitude= request.getParameter("longitude"); 
			
			Double currentLatitude = new Double(latitude);
			Double currentLongitude = new Double(longitude);

			if(latitude!=null && longitude!=null)
			{
				List<LocationMasterPojo> parkingList= locationDao.getAllNearbyParkingArea(currentLatitude,currentLongitude);
				if(parkingList!=null) 
				{
					
						String jsongroceryList = mapperObj.writeValueAsString(parkingList);
						System.out.println("parkingList size:: "+parkingList.size()+" parkingList "+parkingList);
					  	response.setContentType("application/json");
				        response.setCharacterEncoding("UTF-8");
				        System.out.println(jsongroceryList);
				        response.getWriter().write(jsongroceryList);
				        
				  }
				  else
				  {
				    	json.put("Failure","false");
					    json.put("message","NO match");
					    response.setContentType("application/json");
				        response.setCharacterEncoding("UTF-8");
				        response.getWriter().write(json.toString());
				  }
			}
	   }
		
	}

}
