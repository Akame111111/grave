package com.cloudfox.grave.dto;

import com.cloudfox.grave.entity.Tribute;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TributeDto extends Tribute {

    private String typeName;

    private String isUseName;
}
