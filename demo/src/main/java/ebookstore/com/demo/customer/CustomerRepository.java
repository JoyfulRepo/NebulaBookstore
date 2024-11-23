package ebookstore.com.demo.customer;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByName(String name);

    Optional<Customer> findByJoinDate(LocalDate joinDate);

    void deleteByName(String name);

    void deleteByJoinDate(LocalDate joinDate);
}