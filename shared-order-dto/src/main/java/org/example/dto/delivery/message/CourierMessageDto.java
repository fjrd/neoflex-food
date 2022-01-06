package org.example.dto.delivery.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class CourierMessageDto implements Serializable {

    @NotNull
    private UUID courierId;

}
