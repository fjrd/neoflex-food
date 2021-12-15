package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClientOrderDto implements Serializable {

    @NotBlank
    String deliveryAddress;

    @NotBlank
    String cardDetails;

    @NotBlank
    String dishesList;

}