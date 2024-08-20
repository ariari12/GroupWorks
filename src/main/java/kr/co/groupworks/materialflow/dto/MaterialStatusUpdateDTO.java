package kr.co.groupworks.materialflow.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class MaterialStatusUpdateDTO {
    @NotNull @NotEmpty @Size(min = 1)
    private List<String> itemCodeList;

    @NotNull
    private Item item;

    public MaterialItemDTO convertToDTO(MaterialItemDTO itemDTO) {
        return item.convertToDTO(itemDTO);
    }

}
