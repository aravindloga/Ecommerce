package ShoppingApp.ShoppingApp.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmOrderDTO {
    private Long cartId;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}
