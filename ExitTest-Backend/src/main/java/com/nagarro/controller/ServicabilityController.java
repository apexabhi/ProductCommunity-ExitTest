//Servicability API
package com.nagarro.controller;
//importing packages
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.entity.Servicability;
import com.nagarro.service.ServicabilityService;

@RestController
@CrossOrigin("*")
@RequestMapping("/delivery")
public class ServicabilityController {
	@Autowired
	private ServicabilityService service;
	//to add deliverable locations with their repspective eligible products
	@PostMapping
	public ResponseEntity<Servicability> add(@RequestBody Servicability delivery) {
		return ResponseEntity.ok().body(this.service.addPinCode(delivery));
	}
	// to get list of all pincodes
	@GetMapping
	public ResponseEntity<List<Servicability>> getAllPinCodes(){
		return ResponseEntity.ok().body(this.service.getPinCodes());
	}
	//to check product can be delivered at particular location
	@GetMapping("/{productcode}/{pincode}")
    public ResponseEntity<Servicability> getDeliverable(@PathVariable("productcode") String productId,
            @PathVariable("pincode") int pincode) {
        System.out.println(productId + pincode);
        return ResponseEntity.ok().body(service.isDeliverable(productId, pincode));
    }
	
	
}
