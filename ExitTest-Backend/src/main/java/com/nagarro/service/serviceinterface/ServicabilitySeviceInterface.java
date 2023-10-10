package com.nagarro.service.serviceinterface;

import java.util.List;

import com.nagarro.entity.Servicability;

public interface ServicabilitySeviceInterface {

	public Servicability addPinCode(Servicability s);
	public Servicability isDeliverable(String p1,int p2);
	public List<Servicability> getPinCodes();
}
