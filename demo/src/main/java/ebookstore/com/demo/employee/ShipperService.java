package ebookstore.com.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ebookstore.com.demo.order.Order;
import ebookstore.com.demo.order.OrderRepository;

@Service
public class ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Shipper save(Shipper shipper) {
        return shipperRepository.save(shipper);
    }

    public Shipper findById(Long id) {
        return shipperRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        shipperRepository.deleteById(id);
    }
}