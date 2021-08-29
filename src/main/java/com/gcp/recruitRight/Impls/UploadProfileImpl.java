package com.gcp.recruitRight.Impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcp.recruitRight.Repository.UploadProfileRepository;
import com.gcp.recruitRight.Requests.UploadProfileRequest;
import com.gcp.recruitRight.models.UserProfiles;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@Service
public class UploadProfileImpl {
	
	@Autowired
	UploadProfileRepository uploadProfileRepository;
	
	@Autowired
	SessionManagement sessionManagement;
	
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	public Boolean uploadProfile(UploadProfileRequest uploadProfileRequest) throws Exception{
		
		System.out.println("Entering uploadProfileImpl");
		
		try {
		if(loginServiceImpl.validate(SessionManagement.getUserId(uploadProfileRequest.getSessionId()),uploadProfileRequest.getSessionId()))
		{
			System.out.println("Entering after validation");
			String userId;
			String contact;
			String name;
			int status = 0;
				
			List<String> userProfiles = uploadProfileRequest.getUserProfiles();
			
			for(String userProfile: userProfiles)
			{
				// Create text string builder
			    StringBuilder text = new StringBuilder();
			    
				//Create PdfReader instance.
				PdfReader pdfReader = new PdfReader(userProfile);	
			 
				//Get the number of pages in pdf.
				int pages = pdfReader.getNumberOfPages(); 
			
				//Iterate the pdf through pages.
				for(int page=1; page<=pages; page++) 
				{ 
				  //Extract the page content using PdfTextExtractor.
				  String pageContent = PdfTextExtractor.getTextFromPage(pdfReader, page);
				  text.append(pageContent);
			    }
				String resumeData = text.toString();
				
				String Emp_details = resumeData.substring(0, resumeData.indexOf("CAREER OBJECTIVE")-2);
				
				String Emp_details_list[] = Emp_details.split("\n");
				
				name = Emp_details_list[0];
				contact = Emp_details_list[1].split(" ")[1];
				userId = Emp_details_list[2].split(" ")[1];
				
				pdfReader.close();
				
				List<UserProfiles> profiles = uploadProfileRepository.findUserProfiles(userId);
				
				if(profiles.size()!=0) {
					status += uploadProfileRepository.updateUserProfiles(userId,userProfile);
				}
				else
					status += uploadProfileRepository.insertIntoUserProfiles(userId,name,contact,userProfile);
			}
			if(status == userProfiles.size())
				return true;
			return false;	
		}
		else
			throw new Exception("Invalid session.");
	} catch(Exception e) {
		throw new Exception("Invalid session");
	}
}
}
