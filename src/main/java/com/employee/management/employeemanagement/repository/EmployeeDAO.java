package com.employee.management.employeemanagement.repository;

import com.employee.management.employeemanagement.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee, String> {

    Employee findTopByOrderBySalaryDesc();

    // findByNameOrderByVersionDesc(String)

    // findFirstByNameOrderByVersionDesc(String)

    // findTopByNameOrderByVersionDesc(String)

    // findTop2ByNameOrderByVersionDesc(String)

    // findAllByOrderByIdAsc

    // findTop10ByOrderByLevelDesc

    /*
    @Query(value = GET_ACCESS_TYPES,nativeQuery = true)
    public List<String> getAccessTypeList();
     */

    /*
     @Query(value = "select inv.* from INVOICE inv,CRN_USER crnusr where
     crnusr.cust_ref_number=inv.cust_ref_number and crnusr.ein = :ein and
     inv.invoice_status_code=:status", nativeQuery = true)
    List<InvoiceEntity> getInvoicesForUserAndStatus(@Param("ein") String ein, @Param("status") String status);

     */
}
