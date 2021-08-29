package com.gcp.recruitRight.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gcp.recruitRight.Impls.UploadProfileImpl;
import com.gcp.recruitRight.Requests.UploadProfileRequest;
import com.gcp.recruitRight.response.BaseResponse;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UploadProfileController {

	@Autowired
	UploadProfileImpl uploadProfileImpl;
	
	@PostMapping("/uploadProfile")
	public ResponseEntity<BaseResponse> uploadProfile(@RequestBody UploadProfileRequest uploadProfileRequest){
		
		BaseResponse baseResponse = new BaseResponse();
		
		try {
			baseResponse.setBooleanMsg(uploadProfileImpl.uploadProfile(uploadProfileRequest));
		} catch (Exception e) {
			baseResponse.setExceptionMessage(e.getMessage());
			baseResponse.setBooleanMsg(false);
		}
		
		return ResponseEntity.ok(baseResponse);
	}
}
