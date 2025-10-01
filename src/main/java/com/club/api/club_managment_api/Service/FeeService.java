package com.club.api.club_managment_api.Service;

import java.util.List;

import org.springframework.stereotype.Service;

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

	public boolean checkPortalAdminAccess(int studentId, int clubId) {
		boolean haveAccess = false;
		Student s = studentService.getStudentByIdEntity(studentId);
		;
		if (s.getRole().equals(Role_enum.ADMIN) && clubService.getMemberExistanseByStudentId(clubId, studentId)) {
			haveAccess = true;
		} else {
			haveAccess = false;
		}

		return haveAccess;

	}

	public Fee recordFee(Fee fee, int studentId, int clubId) {
		Club c = clubService.getClubByIdEntity(clubId);

		Fee f = new Fee();
		f.setAmount(fee.getAmount());
		f.setClub(c);
		f.setPurpose(fee.getPurpose());
		f.setStudent(studentService.getStudentByIdEntity(studentId));
		f.setStatus(Payment_Status_enum.PENDING);
		return feeRepository.save(f);
	}

	public Fee getFeeById(int id) {
		return feeRepository.findById(id)
				.orElseThrow(() -> new resourceNotFoundException("No payment information found:" + id));
	}

	public List<Fee> getFeesByStudent(int studentId) {
		return feeRepository.findBystudent(studentService.getStudentByIdEntity(studentId));
	}

	public List<Fee> getFeesByClub(int clubId) {
		return feeRepository.findByclub(clubService.getClubByIdEntity(clubId));

	}

	public Fee updateFeeStatus(int feeId, Payment_Status_enum newStatus, int studentId, int clubId) {
		Fee f = getFeeById(feeId);
		if (!checkPortalAdminAccess(studentId, clubId)) {
			throw new notAuthorizedUserException("not authorised:" + studentId);
		} else {
			if (f.getClub().getId() != clubId) {
			    throw new IllegalArgumentException("Fee does not belong to this club!");
			}
			else
			f.setStatus(newStatus);
		}
		return feeRepository.save(f);
	}

	public double getTotalCollectedByClub(int clubId) {
		List<Fee> fees = getFeesByClub(clubId);
		double total = fees.stream().mapToDouble(Fee::getAmount) // extract amount
				.sum();
		return total;

	}

}