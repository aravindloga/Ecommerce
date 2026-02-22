package ShoppingApp.ShoppingApp.ControllerService;

import ShoppingApp.ShoppingApp.Entity.Cart;
import ShoppingApp.ShoppingApp.Entity.CartItem;
import ShoppingApp.ShoppingApp.Entity.Product;
import ShoppingApp.ShoppingApp.Entity.User;
import ShoppingApp.ShoppingApp.Exception.ProductNotFound;
import ShoppingApp.ShoppingApp.Repository.CartItemRepository;
import ShoppingApp.ShoppingApp.Repository.CartRepository;
import ShoppingApp.ShoppingApp.Repository.ProductRepository;
import ShoppingApp.ShoppingApp.ServiceImp.CartControllerImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartControllerService implements CartControllerImp {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public void addToCart(User user,String guestId,Long productId) {
        Cart cart = getOrCreateCart(user, guestId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    throw new ProductNotFound("Product not found");
                });
        Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(cart,product);
        if(existingItem.isPresent()){
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
            cartItemRepository.save(item);
        }
        else{
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(1);
            item.setPrice(product.getPrice());
            cart.getItems().add(item);
        }
        reCalTotal(cart);
        cartRepository.save(cart);

    }
    private void reCalTotal(Cart cart){
        double total = 0;
        for(CartItem item:cart.getItems()){
            total += item.getPrice() * item.getQuantity();
        }
        cart.setTotalPrice(total);
        cartRepository.save(cart);

    }

    private Cart getOrCreateCart(User user ,String guestId){
        if(user != null){
            return cartRepository.findByUser(user).orElseGet(()->
                    cartRepository.save(new Cart(user)));
        }
        else{
            return cartRepository.findByGuestId(guestId)
                    .orElseGet(()->cartRepository.save(new Cart(guestId)));
        }
    }
    @Override
    public void removeFromCart(long id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()->{
                    throw new ProductNotFound("Product not found");
                });
        Cart cart = cartItem.getCart();
        cartItemRepository.delete(cartItem);
        reCalTotal(cart);

    }

    @Override
    public void updateQuantity(Long id, int quantity) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()-> {throw new ProductNotFound("Product not found");});
        cartItem.setQuantity(quantity);
        reCalTotal(cartItem.getCart());
        cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartItems(User user,String guestId) {
        return  getOrCreateCart(user,guestId);
    }

    @Override
    public void clearCart(User user,String guestId) {
        Cart cart = getOrCreateCart(user,guestId);
        cart.getItems().clear();

        reCalTotal(cart);

    }

    @Override
    @Transactional
    public void mergeGuestCartToUserCart(String guestId, User user) {
        if(guestId == null || user == null) return;
        Optional<Cart> guestCart = cartRepository.findByGuestId(guestId);
        if(guestCart.isEmpty()) return;
        Cart userCart = cartRepository.findByUser(user).orElse(new Cart());
        userCart.setUser(user);
        cartRepository.save(userCart);

        for(CartItem cartItem : guestCart.get().getItems()){
            Optional<CartItem> existingItem = cartItemRepository.findByCartAndProduct(userCart,cartItem.getProduct());
            CartItem newCartItem;
            if(existingItem.isPresent()){
                newCartItem = existingItem.get();

                newCartItem.setQuantity(cartItem.getQuantity() + existingItem.get().getQuantity());
                newCartItem.setPrice(cartItem.getProduct().getPrice());
            }
            else{
                newCartItem = new CartItem();
                newCartItem.setQuantity(existingItem.get().getQuantity());
                newCartItem.setCart(userCart);
                newCartItem.setProduct(cartItem.getProduct());
                newCartItem.setPrice(cartItem.getProduct().getPrice());
            }
            cartItemRepository.save(newCartItem);
        }
        cartRepository.delete(guestCart.get());
        reCalTotal(userCart);




    }
}
