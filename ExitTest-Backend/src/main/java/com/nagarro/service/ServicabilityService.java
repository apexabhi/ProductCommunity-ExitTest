package com.nagarro.service;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entity.Servicability;
import com.nagarro.exceptions.DeliverNotPossibleException;
import com.nagarro.exceptions.NoPinCodeAvailableException;
import com.nagarro.repository.ServicabilityRepository;
import com.nagarro.service.serviceinterface.ServicabilitySeviceInterface;

@Service
public class ServicabilityService implements ServicabilitySeviceInterface{

	@Autowired
	private ServicabilityRepository repo;
	@Override
	public Servicability addPinCode(Servicability s) {
		// TODO Auto-generated method stub
		return this.repo.save(s);
	}
	@Override
	public Servicability isDeliverable(String p1, int p2) {
		// TODO Auto-generated method stub
		Servicability s=this.repo.findByProductProductCodeAndPincode(p1, p2);
		if(Objects.nonNull(s)) {
			return s;
		}
		else {
			throw new DeliverNotPossibleException("No delivery possible for the respective product");
		}
	}
	@Override
	public List<Servicability> getPinCodes() {
		// TODO Auto-generated method stub
		List<Servicability> s=this.repo.findAll();
		if(s.isEmpty()==false) {
			return s;
		}
		else {
			throw new NoPinCodeAvailableException("No pin codes available in the database");
		}
	}

}
