package com.edureka.borrowerManagement.repository;

import com.edureka.borrowerManagement.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

     Optional<BorrowingRecord> findByBookId(Long id);

    List<BorrowingRecord> findAllByUserName(String name);
}
