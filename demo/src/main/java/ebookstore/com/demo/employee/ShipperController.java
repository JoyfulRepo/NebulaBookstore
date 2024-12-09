package ebookstore.com.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ebookstore.com.demo.order.Order;

@RestController
@RequestMapping("/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return shipperService.getAllOrders();
    }

    @PostMapping
    public Shipper addShipper(@RequestBody Shipper shipper) {
        return shipperService.save(shipper);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipper> getShipperById(@PathVariable Long id) {
        Shipper shipper = shipperService.findById(id);
        if (shipper != null) {
            return ResponseEntity.ok(shipper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipperById(@PathVariable Long id) {
        shipperService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}