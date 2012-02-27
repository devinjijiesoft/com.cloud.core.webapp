package com.cloud.nvl.core.service;
import java.util.ArrayList;
import java.util.List;

import com.cloud.nvl.core.service.inter.IRequestService;
import com.cloud.nvl.core.jpa.model.ArrayOperation;
public class RequestService implements IRequestService {

//test String
	public String getRequestPara() {
	
		return new String("Happy spring festival");
	}

 //test ArrayList
	public List getArrayPara() {
		ArrayOperation arrayOperation= new ArrayOperation();
		ArrayList list=(ArrayList) arrayOperation.getOutput();
		return list; 
		
		
	}
}
