package com.ofw.ofw.payload.designer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SerachDesignerResponse {
    private String name;

    private String email;

    @Builder
    public SerachDesignerResponse(SerachDesignerResponse searchDesigner){
        this.name = searchDesigner.getName();
        this.email = searchDesigner.getEmail();
    }
}
