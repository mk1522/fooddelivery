package com.capgemini.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.entities.Bill;
import com.capgemini.service.IBillService;
import com.capgemini.service.ILoginService;

@Controller
@RequestMapping("/bill")
public class IBillController {

	@Autowired
	IBillService service;

	@Autowired
	ILoginService loginService;

	@PostMapping(value = "/addBill")
	public ResponseEntity<Bill> addBill(@RequestBody Bill bill, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Bill saved = null;
		saved = service.addBill(bill);
		return new ResponseEntity<Bill>(saved, HttpStatus.OK);
	}

	@GetMapping(value = "/viewBill")
	public ResponseEntity<Bill> viewBill(@RequestBody Bill bill, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}
		Bill showBill = service.viewBill(bill);
		if (showBill == null) {
			return new ResponseEntity("Bill not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(showBill, HttpStatus.OK);
	}

	@GetMapping(value = "/viewBills/{startDate}/{endDate}")
	public ResponseEntity<List<Bill>> viewBills(
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
			HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		List<Bill> findBills = service.viewBills(startDate, endDate);
		if (findBills == null) {
			return new ResponseEntity("Bills not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Bill>>(findBills, HttpStatus.OK);
	}

	@GetMapping(value = "/viewBills/{custId}")
	public ResponseEntity<List<Bill>> viewBills(@PathVariable String custId, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		List<Bill> findBills = service.viewBills(custId);
		if (findBills == null) {
			return new ResponseEntity("Bills not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Bill>>(findBills, HttpStatus.OK);
	}

	@GetMapping(value = "/calculateTotalCost")
	public ResponseEntity<Double> calculateTotalCost(@RequestBody Bill bill, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		double totalCost = service.calculateTotalCost(bill);
		return new ResponseEntity<Double>(totalCost, HttpStatus.OK);
	}

	@PutMapping(value = "/updateBill")
	public ResponseEntity<Bill> updateBill(@RequestBody Bill bill, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Bill newBill = service.updateBill(bill);
		if (newBill == null) {
			return new ResponseEntity("Bill not available for Update!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(newBill, HttpStatus.OK);
	}

	@DeleteMapping(value = "/removeBill")
	public ResponseEntity<Bill> removeBill(@RequestBody Bill bill, HttpServletRequest request) {
		// session checking
		boolean validLogin = loginService.checkSession(request);
		if (!validLogin) {
			throw new IllegalArgumentException("Not logged in");
		}

		Bill deletedBill = service.removeBill(bill);
		if (deletedBill == null) {
			return new ResponseEntity("Bill is not available!", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bill>(deletedBill, HttpStatus.OK);
	}
}