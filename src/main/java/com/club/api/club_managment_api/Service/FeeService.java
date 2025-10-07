package com.club.api.club_managment_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.club.api.club_managment_api.dtos.Fees.RequestFeesDto;
import com.club.api.club_managment_api.dtos.Fees.RequestStatusUpdateDto;
import com.club.api.club_managment_api.exceptions.notAuthorizedUserException;
import com.club.api.club_managment_api.exceptions.resourceNotFoundException;
import com.club.api.club_managment_api.models.Club;
import com.club.api.club_managment_api.models.Fee;
import com.club.api.club_managment_api.models.Student;
import com.club.api.club_managment_api.models.enums.Payment_Status_enum;
import com.club.api.club_managment_api.models.enums.Role_enum;
import com.club.api.club_managment_api.repository.FeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FeeService {

	private final FeeRepository feeRepository;
	private final ClubService clubService;
	private StudentService studentService;

	public FeeService(FeeRepository feeRepository, ClubService clubService, StudentService studentService) {
		super();
		this.feeRepository = feeRepository;
		this.clubService = clubService;
		this.studentService = studentService;
	}

	
	public Fee recordFee(RequestFeesDto dto, long studentId, int clubId) {
		Club c = clubService.getClubByIdEntity(clubId);

		Fee f = new Fee();
		f.setAmount(dto.getAmount());
		f.setClub(c);
		f.setPurpose(dto.getPurpose());
		f.setStudent(studentService.getStudentByIdEntity(studentId));
		f.setStatus(Payment_Status_enum.PENDING);
		return feeRepository.save(f);
	}

	public Fee getFeeById(int id) {
		return feeRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("No payment information found:" + id));
	}

	public List<Fee> getFeesByStudent(long studentId) {
		return feeRepository.findBystudent(studentService.getStudentByIdEntity(studentId));
	}

	public List<Fee> getFeesByClub(int clubId) {
		return feeRepository.findByclub(clubService.getClubByIdEntity(clubId));

	}

	public Fee updateFeeStatus(int feeId, RequestStatusUpdateDto dto) {
		
		Fee f=getFeeById(feeId);
		System.out.println(f.getClub().getId());
		System.out.println(dto.getAdminStudentId());
		System.out.println(f.getClub().getClubAdminId()==dto.getAdminStudentId());
		
	if(f.getClub().getClubAdminId()!=dto.getAdminStudentId()) {
		throw new notAuthorizedUserException("only club admins can approve payment");
	}
	else {
		f.setStatus(dto.getStatus());
		Fee saved=feeRepository.save(f);
		
		return saved;
	}
		
		
	}

	public double getTotalCollectedByClub(int clubId) {
	    List<Fee> fees = getFeesByClub(clubId);
	    
	    double total = fees.stream()
	            .filter(fee -> fee.getStatus() == Payment_Status_enum.PAID) 
	            .mapToDouble(Fee::getAmount)
	            .sum();
	    
	    return total;
	}

}