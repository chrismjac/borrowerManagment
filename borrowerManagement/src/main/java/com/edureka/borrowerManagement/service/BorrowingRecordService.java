package com.edureka.borrowerManagement.service;

import com.edureka.borrowerManagement.entity.BorrowingRecord;
import com.edureka.borrowerManagement.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    public ResponseEntity<String> addNewBorrowingRecord(BorrowingRecord borrowingRecord) {
        try {
            borrowingRecordRepository.save(borrowingRecord);
            return new ResponseEntity<>("Borrowing Record Details added successfully", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Exception raised while adding new borrowing record" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getAllBorrowingRecords() {
        List<BorrowingRecord> allBorrowingRecords = borrowingRecordRepository.findAll();
        if (!allBorrowingRecords.isEmpty()) {
            return ResponseEntity.ok(allBorrowingRecords);
        }
        return new ResponseEntity<>("No Borrowing Records Available", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getBorrowingRecordByUser(String name) {
        List<BorrowingRecord> allBooks = borrowingRecordRepository.findAllByUserName(name);
        if (!allBooks.isEmpty()) {
            return ResponseEntity.ok(allBooks);
        }
        return new ResponseEntity<>("No Borrowing Records Available", HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<String> updateBorrowingRecord(BorrowingRecord borrowingRecord, Long id) {
        ResponseEntity<?> bookDetails = getBorrowingRecordById(id);
        if (bookDetails.getStatusCode().is2xxSuccessful()) {
            try {
                borrowingRecordRepository.save(borrowingRecord);
                return new ResponseEntity<>("BorrowingRecord Details updated successfully", HttpStatus.OK);
            } catch (Exception exception) {
                return new ResponseEntity<>("Exception raised while updating BorrowingRecord" + exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("No borrowingRecord with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
        return new ResponseEntity<>("BorrowingRecord Details deleted successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> getBorrowingRecordById(Long id) {
        Optional<BorrowingRecord> borrowingRecordDetails = borrowingRecordRepository.findById(id);
        if (borrowingRecordDetails.isPresent()) {
            return ResponseEntity.ok(borrowingRecordDetails.get());
        }
        return new ResponseEntity<>("No borrowingRecord details with specified Id found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> returnBookById(Long id) {
        Optional<BorrowingRecord> borrowingRecordDetails = borrowingRecordRepository.findByBookId(id);
        if (borrowingRecordDetails.isPresent()) {
            BorrowingRecord borrowingRecord = borrowingRecordDetails.get();
            borrowingRecord.setReturnDate(LocalDate.now());
            borrowingRecordRepository.save(borrowingRecord);
            return ResponseEntity.ok("Book successfully returned");
        }
        return new ResponseEntity<>("No borrowingRecord details with specified Id found", HttpStatus.NOT_FOUND);
    }
}
