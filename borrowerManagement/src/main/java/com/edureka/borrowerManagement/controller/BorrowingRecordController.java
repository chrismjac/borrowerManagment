package com.edureka.borrowerManagement.controller;

import com.edureka.borrowerManagement.entity.BorrowingRecord;
import com.edureka.borrowerManagement.service.BorrowingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowingRecords")
public class BorrowingRecordController {
    @Autowired
    BorrowingRecordService borrowingRecordService;

    @PostMapping
    @Operation(summary = "Add a new borrowing record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BorrowingRecord added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add BorrowingRecord")})
    private ResponseEntity<String> addNewBorrowingRecord(@RequestBody BorrowingRecord borrowingRecord){
        return borrowingRecordService.addNewBorrowingRecord(borrowingRecord);
    }

    @GetMapping
    @Operation(summary = "Retrieve all BorrowingRecords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BorrowingRecords retrieved successfully"),
            @ApiResponse(responseCode = "204", description = "No BorrowingRecords Added")})
    private ResponseEntity<?> getAllBorrowingRecords(){
        return borrowingRecordService.getAllBorrowingRecords();
    }
    @GetMapping("user/{name}")
    @Operation(summary = "Get a BorrowingRecord by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified BorrowingRecord details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No BorrowingRecord for specified user name found")})
    private ResponseEntity<?> getBorrowingRecordByUser(@PathVariable String name){
        return borrowingRecordService.getBorrowingRecordByUser(name);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a BorrowingRecord by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified BorrowingRecord details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No BorrowingRecord for specified id found")})
    private ResponseEntity<?> getBorrowingRecordById(@PathVariable Long id){
        return borrowingRecordService.getBorrowingRecordById(id);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a BorrowingRecord")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified BorrowingRecord details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified BorrowingRecord details updating failed"),
            @ApiResponse(responseCode = "404", description = "No BorrowingRecord with specified Id found")})
    private ResponseEntity<String> updateBorrowingRecord(@RequestBody BorrowingRecord borrowingRecord,@PathVariable Long id){
        return borrowingRecordService.updateBorrowingRecord(borrowingRecord,id);
    }
    @DeleteMapping ("/{id}")
    @Operation(summary = "Update a BorrowingRecord")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified BorrowingRecord details updated successfully"),
            @ApiResponse(responseCode = "500", description = "Specified BorrowingRecord details updating failed"),
            @ApiResponse(responseCode = "404", description = "No BorrowingRecord with specified Id found")})
    private ResponseEntity<String> deleteBorrowingRecord(@PathVariable Long id) {
        return borrowingRecordService.deleteBorrowingRecord(id);
    }
    @GetMapping ("/return/{id}")
    @Operation(summary = "Return a book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book successfully returned"),
            @ApiResponse(responseCode = "404", description = "No borrowingRecord details with specified Id found")})
    private ResponseEntity<String> returnBookById(@PathVariable Long id) {
        return borrowingRecordService.returnBookById(id);
    }


}
