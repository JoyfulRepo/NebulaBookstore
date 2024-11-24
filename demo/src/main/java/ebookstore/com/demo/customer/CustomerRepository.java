package ebookstore.com.demo.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByJoinDate(LocalDate joinDate);
}