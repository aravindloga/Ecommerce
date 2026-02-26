package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.DTOs.AddressDTO;

import ShoppingApp.ShoppingApp.DTOs.ReviewDTO;
import ShoppingApp.ShoppingApp.Entity.*;
import ShoppingApp.ShoppingApp.Exception.AddressNotFoundException;
import ShoppingApp.ShoppingApp.Exception.ProductNotFound;
import ShoppingApp.ShoppingApp.Exception.UserNotFound;
import ShoppingApp.ShoppingApp.Repository.*;
import ShoppingApp.ShoppingApp.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService  implements UserServiceImp {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Orders> getMyOrders() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(name);
        List< Orders> orders = orderRepository.findByUser(user);
        return orders;
    }

    @Override
    public void updateAddress(AddressDTO addressDTO) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(name);
        if(user == null) throw new UserNotFound("User not found");
        Address address = new Address();
        address.setCity(addressDTO.getCity());
        address.setName(addressDTO.getName());
        address.setPhone(addressDTO.getPhone());
        address.setPincode(addressDTO.getPincode());
        address.setStreet(addressDTO.getStreet());
        address.setDefault(addressDTO.isDefault());
        address.setState(addressDTO.getState());
        address.setFullName(addressDTO.getFullName());
        address.setUser(user);
        addressRepository.save(address);

    }

    @Override
    public void removeAddress(Long id) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(name);
        Address address =addressRepository.findById(id).orElseThrow(() -> {
            throw new AddressNotFoundException("User address not found");
        });
        addressRepository.delete(address);

    }

    @Override
    public User getUserProfile() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(name);
        return user;
    }

    @Override
    public void addReview(ReviewDTO reviewDTO) {
        Product product = productRepository.findById(reviewDTO.getProductId()).orElseThrow(
                () -> {
                    throw new ProductNotFound("Product not found");
                }
        );
        Review review = new Review();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserName(name);
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setProduct(product);
        reviewRepository.save(review);
    }

}
