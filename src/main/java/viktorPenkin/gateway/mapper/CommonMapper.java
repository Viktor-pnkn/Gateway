package viktorPenkin.gateway.mapper;

import viktorPenkin.gateway.dto.CommonDTO;
import viktorPenkin.gateway.entity.Common;

public class CommonMapper {

    public Common toEntity(CommonDTO commonDTO){
        return new Common(
                null,
                commonDTO.getType(),
                null,
                commonDTO.getValue()
                );
    }
}
